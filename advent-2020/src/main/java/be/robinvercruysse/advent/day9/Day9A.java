package be.robinvercruysse.advent.day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day9A {
    private static final int PREAMBLE_LENGTH = 25;

    public static void main(String[] args) throws IOException {
        final InputStream inputStream = Day9A.class.getClassLoader().getResourceAsStream("day9.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final List<Long> numbers = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            final long number = Long.parseLong(line);
            numbers.add(number);
            if (numbers.size() <= PREAMBLE_LENGTH) {
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
                System.out.printf("Rule-breaker at index %d with value %d%n", numbers.size() - 1, number);
            }
        }
    }
}
