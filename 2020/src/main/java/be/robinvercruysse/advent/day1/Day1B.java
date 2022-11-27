package be.robinvercruysse.advent.day1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Day1B {
    public static void main(String[] args) {
        final InputStream inputStream = Day1B.class.getClassLoader().getResourceAsStream("day1.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final List<Integer> entries = reader.lines().map(Integer::valueOf).collect(Collectors.toList());
        boolean found = false;
        for (int i = 0; i < entries.size() && !found; i++) {
            for (int j = 0; j < entries.size() && !found; j++) {
                if (i == j) {
                    continue;
                }
                final int complement = 2020 - entries.get(i) - entries.get(j);
                if (entries.contains(complement)) {
                    System.out.printf("Entries are %d, %d and %d%n", entries.get(i), entries.get(j), complement);
                    final long product = (long) entries.get(i) * (long) entries.get(j) * (long) complement;
                    System.out.printf("Product is %d%n", product);
                    found = true;
                }
            }
        }
    }
}
