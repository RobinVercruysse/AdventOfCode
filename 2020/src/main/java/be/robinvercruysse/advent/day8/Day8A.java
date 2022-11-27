package be.robinvercruysse.advent.day8;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Day8A {
    public static void main(String[] args) {
        final InputStream inputStream = Day8A.class.getClassLoader().getResourceAsStream("day8.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final Instruction[] instructions = reader.lines().map(Instruction::new).toArray(Instruction[]::new);
        final Set<Integer> visitedPointers = new HashSet<>();
        int acc = 0;
        int pointer = 0;
        while (!visitedPointers.contains(pointer)) {
            visitedPointers.add(pointer);
            final Instruction currentInstruction = instructions[pointer];
            switch (currentInstruction.getKey()) {
                case "nop":
                    pointer++;
                    break;
                case "acc":
                    acc += currentInstruction.getValue();
                    pointer++;
                    break;
                case "jmp":
                    pointer += currentInstruction.getValue();
                    break;
                default:
                    System.err.printf("Unknown instruction: %s%n", currentInstruction.getKey());
                    break;
            }
        }
        System.out.printf("Acc: %d%n", acc);
    }

    private static class Instruction {
        private final String key;
        private final int value;

        public Instruction(final String line) {
            final String[] parts = line.split(" ");
            this.key = parts[0];
            this.value = parts[1].startsWith("+") ? Integer.parseInt(parts[1].substring(1)) : Integer.parseInt(parts[1]);
        }

        public String getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }
}
