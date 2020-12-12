package be.robinvercruysse.advent.day5;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day5A {
    public static void main(String[] args) {
        final InputStream inputStream = Day5A.class.getClassLoader().getResourceAsStream("day5.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        reader.lines().mapToInt(Day5A::calculateSeatId).max().ifPresent(System.out::println);
    }

    private static int calculateSeatId(final String selection) {
        final int row = calculateValue(selection.substring(0, 7).replaceAll("F", "0").replaceAll("B", "1"));
        final int column = calculateValue(selection.substring(7, 10).replaceAll("L", "0").replaceAll("R", "1"));
        System.out.printf("Row: %d, Column: %d%n", row, column);
        final int seatId = row * 8 + column;
        System.out.printf("Seat id: %d%n", seatId);
        return seatId;
    }

    private static int calculateValue(final String selection) {
        if (selection.length() == 1) {
            return Integer.parseInt(selection);
        } else {
            final int baseValue = (int) Math.pow(2, selection.length());
            final int addValue = selection.charAt(0) == '1' ? baseValue / 2 : 0;
            final int addValue2 = calculateValue(selection.substring(1));
            System.out.printf("Adding %d and %d%n", addValue, addValue2);
            return addValue + addValue2;
        }
    }
}
