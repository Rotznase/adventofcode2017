import java.util.HashSet;
import java.util.Set;

public class Day22 extends AdventOfCode {
    @Override
    void run() {
        Set<Node> infectedNodes = parseInput(gridComputingCluster);

        Carrier carrier = new Carrier(gridComputingCluster[0].length()/2, gridComputingCluster.length/2, Direction.NORTH);

        for (int i=0; i<10000; i++) {
            carrier.burst(infectedNodes);
        }

        System.out.println(carrier.infectionCounter);
    }

    class Carrier {
        int x;
        int y;
        Direction dir;

        int infectionCounter;

        public Carrier(int x, int y, Direction dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.infectionCounter = 0;
        }

        void burst(Set<Node> infectedNodes) {
            final Node node = infectedNodes.stream()
                    .filter(n -> x==n.x && y==n.y)
                    .findFirst()
                    .orElse(null);

            if (node != null) {
                dir = dir.rotateRight();
                infectedNodes.remove(node);
            } else {
                dir = dir.rotateLeft();
                infectionCounter++;
                infectedNodes.add(new Node(x,y));
            }
//            if (node.isInfected()) {
//                node.clean();
//                dir = dir.rotateRight();
//                infectedNodes.remove(node);
//            } else if (node.isClean()) {
//                node.infect();
//                dir = dir.rotateLeft();
//                infectionCounter++;
//                infectedNodes.add(node);
//            }
            move();
        }

        void move() {
            switch (dir) {
                case NORTH: y--;break;
                case SOUTH: y++;break;
                case WEST:  x--;break;
                case EAST:  x++;break;
            }
//            x = (x+infectionGrid[0].length) % infectionGrid[0].length;
//            y = (y+infectionGrid.length) % infectionGrid.length;
        }
    }

    class Node {
        int x;
        int y;
//        boolean infectionStatus;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

//        boolean isInfected() {
//            return infectionStatus == true;
//        }
//
//        boolean isClean() {
//            return infectionStatus == false;
//        }
//
//        void infect() {
//            infectionStatus = true;
//        }
//
//        void clean() {
//            infectionStatus = false;
//        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (x != node.x) return false;
            return y == node.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    enum Direction {
        NORTH, SOUTH, WEST, EAST;

        Direction rotateLeft() {
            switch (this) {
                case NORTH: return WEST;
                case WEST:  return SOUTH;
                case SOUTH: return EAST;
                case EAST:  return NORTH;
            }
            return null;
        }

        Direction rotateRight() {
            switch (this) {
                case NORTH: return EAST;
                case WEST:  return NORTH;
                case SOUTH: return WEST;
                case EAST:  return SOUTH;
            }
            return null;
        }
    }


    Set<Node> parseInput(String[] input) {
        Set<Node> infectedNodes = new HashSet<>();
        for (int y=0; y<input.length; y++) {
            for (int x=0; x<input[y].length(); x++) {
                if (input[y].charAt(x) == '#') {
                    infectedNodes.add(new Node(x, y));
                }
            }
        }

        return infectedNodes;
    }

    private String[] gridComputingCluster = {
//        ".........",
//        ".........",
//        ".........",
//        ".....#...",
//        "...#.....",
//        ".........",
//        ".........",
//        ".........",
        "#.#.#.##.#.##.###.#.###.#",
        ".#..#.....#..#######.##.#",
        "......###..##..###..#...#",
        "##....#.#.#....#..#..#..#",
        "#..#....#.##.#.#..#..#.#.",
        "..##..##.##..##...#...###",
        "..#.#....#..####.##.##...",
        "###...#.#...#.######...#.",
        "..#####...###..#####.#.##",
        "...#..#......####.##..#.#",
        "#...##..#.#####...#.##...",
        "..#.#.###.##.##....##.###",
        "##.##...###....#######.#.",
        "#.#...#.#..#.##..##..##.#",
        ".#...###...#..#..####....",
        "####...#...##.####..#.#..",
        "......#.....##.#.##....##",
        "###.......####..##.#.##..",
        "....###.....##.##..###.#.",
        ".##..##.#.###.###..#.###.",
        "..#..##.######.##........",
        "#..#.#..#.###....##.##..#",
        ".##.#.#...######...##.##.",
        "##..#..#..##.#.#..#..####",
        "#######.#.######.#.....##",
    };
}
