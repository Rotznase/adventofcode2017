public abstract class AdventOfCode {

    public static void main(String[] args) throws Exception {
        final int dayNr = Integer.parseInt(args[0]);

        final String className = String.format("Day%02d", dayNr);
        final Class<?> clazz = Class.forName(className);
        AdventOfCode runner = (AdventOfCode) clazz.newInstance();
        runner.run();
    }

    abstract void run();
}
