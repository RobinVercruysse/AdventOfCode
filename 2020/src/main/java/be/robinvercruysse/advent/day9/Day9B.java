package be.robinvercruysse.advent.day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day9B {
    private static final int PREAMBLE_LENGTH = 25;

    public static void main(String[] args) throws IOException {
        final InputStream inputStream = Day9B.class.getClassLoader().getResourceAsStream("day9.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final List<Long> numbers = new ArrayList<>();
        String line;
        long invalidNumber = -1;
        while ((line = reader.readLine()) != null) {
            final long number = Long.parseLong(line);
            numbers.add(number);
            if (numbers.size() <= PREAMBLE_LENGTH || invalidNumber != -1) {
                continue;
            }
            final List<Long> preamble = numbers.subList(numbers.size() - PREAMBLE_LENGTH - 1, numbers.size() - 1);
            boolean found = false;
            for (long x : preamble) {
                final long complement = number - x;
                if (x != complement && preamble.contains(complement)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                invalidNumber = number;
            }
        }

        System.out.printf("Invalid number is %d%n", invalidNumber);

        boolean sumFound = false;
        for (int i = 0; i < numbers.size() && !sumFound; i++) {
            long sum = 0;
            for (int j = i + 1; j <= numbers.size() && !sumFound && sum < invalidNumber; j++) {
                final List<Long> sumNumbers = numbers.subList(i, j);
                sum = sumNumbers.stream().mapToLong(l -> l).sum();
                if (sum == invalidNumber) {
                    System.out.printf("Found sum numbers from index %d to %d%n", i, j);
                    System.out.println("********");
                    sumNumbers.forEach(System.out::println);
                    System.out.println("********");
                    final long min = sumNumbers.stream().mapToLong(l -> l).min().orElseThrow();
                    final long max = sumNumbers.stream().mapToLong(l -> l).max().orElseThrow();
                    System.out.printf("Min is %d, max is %d, sum is %d%n", min, max, min + max);
                    sumFound = true;
                }
            }
        }
    }
}
