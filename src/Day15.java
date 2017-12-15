public class Day15 extends AdventOfCode {
    @Override
    void run() {
        Generator generatorA = new Generator(16807, 699);
        Generator generatorB = new Generator(48271, 124);

        int matchCounter = 0;
        for (int i=0; i<40_000_000; i++) {
            if (generatorA.nextValue16().equals(generatorB.nextValue16()))
                matchCounter++;
        }
        System.out.println(matchCounter);
    }

    private class Generator {
        long factor;
        long value;

        Generator(int factor, int start) {
            this.factor = factor;
            this.value = start;
        }

        String nextValue16() {
            value = value * factor % 2147483647;
            return Integer.toBinaryString((int) value & 0xFFFF);
        }
    }
}
