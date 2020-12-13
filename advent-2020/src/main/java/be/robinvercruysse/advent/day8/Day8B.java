package be.robinvercruysse.advent.day8;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day8B {
    public static void main(String[] args) {
        final InputStream inputStream = Day8B.class.getClassLoader().getResourceAsStream("day8.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final Instruction[] originalInstructions = reader.lines().map(Instruction::new).toArray(Instruction[]::new);
        //final Outcome outcome = runInstructions(instructions);

        int modifiedInstruction = -1;
        Outcome outcome = null;
        while (outcome == null || outcome.isInfinite()) {
            final Instruction[] modifiedInstructions = Arrays.copyOf(originalInstructions, originalInstructions.length);
            boolean modified = false;
            while (!modified) {
                Instruction instruction = modifiedInstructions[++modifiedInstruction];
                if (instruction.getKey().equals("nop")) {
                    modifiedInstructions[modifiedInstruction] = new Instruction("jmp", instruction.getValue());
                    modified = true;
                } else if (instruction.getKey().equals("jmp")) {
                    modifiedInstructions[modifiedInstruction] = new Instruction("nop", instruction.getValue());
                    modified = true;
                }
            }
            outcome = runInstructions(modifiedInstructions);
        }

        System.out.printf("Acc: %d%n", outcome.getAcc());
    }

    private static Outcome runInstructions(final Instruction[] instructions) {
        final Set<Integer> visitedPointers = new HashSet<>();
        int acc = 0;
        int pointer = 0;
        while (!visitedPointers.contains(pointer) && pointer != instructions.length) {
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
        return new Outcome(pointer != instructions.length, acc);
    }

    private static class Instruction {
        private final String key;
        private final int value;

        public Instruction(final String line) {
            final String[] parts = line.split(" ");
            this.key = parts[0];
            this.value = parts[1].startsWith("+") ? Integer.parseInt(parts[1].substring(1)) : Integer.parseInt(parts[1]);
        }

        public Instruction(String key, int value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }

    private static class Outcome {
        private final boolean isInfinite;
        private final int acc;

        public Outcome(boolean isInfinite, int acc) {
            this.isInfinite = isInfinite;
            this.acc = acc;
        }

        public boolean isInfinite() {
            return isInfinite;
        }

        public int getAcc() {
            return acc;
        }
    }
}
