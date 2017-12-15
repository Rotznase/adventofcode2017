import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Day14 extends AdventOfCode {
    @Override
    void run() {
        String input = "nbysizxe";
        Day10 day10 = new Day10();

        char[][] disk = new char[128][128];
        for (int i=0; i<128; i++) {
            String hashInput = input+"-"+i;
            String hash = day10.knotHash(hashInput);

            char[] bits = new BigInteger(hash, 16).toString(2).toCharArray();
            Arrays.fill(disk[i], '0');
            System.arraycopy(bits, 0, disk[i], 128-bits.length, bits.length);
        }

        // Teil 1
        int count = 0;
        for (char[] row : disk) {
            for (char bit : row)
                count += bit - '0';
        }
        System.out.println(count);


        // Teil 2
//        Collection<P> m = new ArrayList<>();
//        for (int i=0; i<128; i++) {
//            for (int j=0; j<128; j++) {
//                m.add(new P(i+1, j+1, disk));
//            }
//        }
//
//
//        int[] cnt=new int[1];
//        final List<Set<P>> regions = new ArrayList<>();
//        m.stream().filter(P::used).forEach(p -> {
//            cnt[0]++;
//            Collection<Set<P>> found = regions.stream().filter(region -> !Collections.disjoint(region, p.adjacent(true))).collect(Collectors.toList());
//
//            if (!found.isEmpty()) {
//                final Set<P> first = found.stream().findFirst().orElse(null);
//                assert first != null;
//                found.stream().skip(1).forEach(set -> {
//                    first.addAll(set);
//                    regions.remove(set);
//                });
//
//                first.addAll(p.adjacent(true));
//            } else {
//                regions.add(p.adjacent(true));
//            }
//        });
//
//        if (regions.size() == 1242)
//            System.out.println("Richtig");
//        else
//            System.out.println("Falsch");
//
//        int sum=0;
//        for (Set<P> aaa: regions)
//            sum += aaa.size();
//        System.out.println(sum);
//
//        count = 0;
//        for (Set<P> aaa: regions)
//            if (aaa.size()==1)
//                count++;
//        System.out.println(count);
//
//
//
//        for (int i=0; i<128; i++) {
//            for (int j=0; j<128; j++) {
//                final P p = new P(i+1,j+1,disk);
//                final Set<P> found = regions.stream().filter(region -> !Collections.disjoint(region, p.adjacent(true))).findFirst().orElse(null);
//                final int index = regions.indexOf(found);
//
//                if (index == 203) {
//                    System.out.print(!p.used() ? "." : "x");
//                } else {
//                    System.out.print(!p.used() ? "." : "#");
//                }
//            }
//            System.out.println();
//        }
//
    }

//    private class P {
//        char[][] matrix;
//        int i, j;
//
//        P(int i, int j, char[][] matrix) {
//            this.i = i;
//            this.j = j;
//            this.matrix = matrix;
//        }
//
//        boolean used() {
//            return matrix[j-1][i-1] == '1';
//        }
//
//        Set<P> adjacent(boolean includeSelf) {
//            Set<P> set = new HashSet<>();
//            P p;
//
//            p = new P((i -1 + 127) % 128 + 1, j, matrix);
//            if (p.used())
//                set.add(p);
//            p = new P((i -1 + 1) % 128 + 1, j, matrix);
//            if (p.used())
//                set.add(p);
//            p = new P(i, (j -1 + 127) % 128 + 1, matrix);
//            if (p.used())
//                set.add(p);
//            p = new P(i, (j -1 + 1) % 128 + 1, matrix);
//            if (p.used())
//                set.add(p);
//            if (includeSelf && used())
//                set.add(this);
//            return set;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//
//            P p = (P) o;
//
//            if (i != p.i) return false;
//            return j == p.j;
//        }
//
//        @Override
//        public int hashCode() {
//            int result = i;
//            result = 31 * result + j;
//            return result;
//        }
//
//        @Override
//        public String toString() {
//            return "(" + i + ", " + j + ")";
//        }
//    }
}
