package be.robinvercruysse.advent.day1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Day1A {
    public static void main(String[] args) {
        final InputStream inputStream = Day1A.class.getClassLoader().getResourceAsStream("day1.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final List<Integer> entries = reader.lines().map(Integer::valueOf).collect(Collectors.toList());
        for (int entry : entries) {
            //error risk: list contains single entry of value "1010"
            if (entries.contains(2020 - entry)) {
                final int complement = 2020 - entry;
                System.out.printf("Entries are %d and %d%n", entry, complement);
                System.out.printf("Product is %d%n", entry * complement);
                break;
            }
        }
    }
}
