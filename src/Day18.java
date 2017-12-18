import java.util.HashMap;
import java.util.Map;

public class Day18 extends AdventOfCode {
    @Override
    void run() {

        // Teil 1

        char register;
        long value;
        long lastSound = 0;

        Map<Character, Long> registerValues = new HashMap<>();
        int i=0;
        boolean stop = false;
        while (!stop) {
            String instruction = input[i];
            String cmd = instruction.substring(0, 3);
            switch (cmd) {
                case "snd":
                    register = instruction.charAt(4);
                    lastSound = registerValues.get(register);
                    i++;
                    break;
                case "set":
                    long[] args = getOpArgs(instruction, registerValues);
                    register = instruction.charAt(4);
                    registerValues.put(register, args[1]);
                    i++;
                    break;
                case "add":
                    args = getOpArgs(instruction, registerValues);
                    register = instruction.charAt(4);
                    registerValues.put(register, args[0] + args[1]);
                    i++;
                    break;
                case "mul":
                    args = getOpArgs(instruction, registerValues);
                    register = instruction.charAt(4);
                    registerValues.put(register, args[0] * args[1]);
                    i++;
                    break;
                case "mod":
                    args = getOpArgs(instruction, registerValues);
                    register = instruction.charAt(4);
                    registerValues.put(register, args[0] % args[1]);
                    i++;
                    break;
                case "rcv":
                    register = instruction.charAt(4);
                    value = registerValues.getOrDefault(register, 0L);
                    if (value != 0) {
                        registerValues.put(register, lastSound);
                        stop = true;
                    }
                    i++;
                    break;
                case "jgz":
                    register = instruction.charAt(4);
                    value = registerValues.getOrDefault(register, 0L);
                    if (value > 0) {
                        args = getOpArgs(instruction, registerValues);
                        i += args[1];
                    } else {
                        i++;
                    }
                    break;
            }
        }

        System.out.println(registerValues);
        System.out.println(lastSound);
    }

    private long[] getOpArgs(String instruction, Map<Character, Long> registerValues) {
        char register = instruction.charAt(4);
        long [] args = new long[2];
        args[0]   = registerValues.getOrDefault(register, 0L);
        if (Character.isAlphabetic(instruction.charAt(6))) {
            args[1] = registerValues.getOrDefault(instruction.charAt(6), 0L);
        } else {
            args[1] = Integer.parseInt(instruction.substring(6));
        }
        return args;
    }

    private String[] input = {
//            "set a 1",
//            "add a 2",
//            "mul a a",
//            "mod a 5",
//            "snd a",
//            "set a 0",
//            "rcv a",
//            "jgz a -1",
//            "set a 1",
//            "jgz a -2",

            "set i 31",
            "set a 1",
            "mul p 17",
            "jgz p p",
            "mul a 2",
            "add i -1",
            "jgz i -2",
            "add a -1",
            "set i 127",
            "set p 622",
            "mul p 8505",
            "mod p a",
            "mul p 129749",
            "add p 12345",
            "mod p a",
            "set b p",
            "mod b 10000",
            "snd b",
            "add i -1",
            "jgz i -9",
            "jgz a 3",
            "rcv b",
            "jgz b -1",
            "set f 0",
            "set i 126",
            "rcv a",
            "rcv b",
            "set p a",
            "mul p -1",
            "add p b",
            "jgz p 4",
            "snd a",
            "set a b",
            "jgz 1 3",
            "snd b",
            "set f 1",
            "add i -1",
            "jgz i -11",
            "snd a",
            "jgz f -16",
            "jgz a -19",
    };
 }
