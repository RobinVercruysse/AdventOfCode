package be.robinvercruysse.advent.day5;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Day5B {
    public static void main(String[] args) {
        final InputStream inputStream = Day5B.class.getClassLoader().getResourceAsStream("day5.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        final List<Integer> orderedSeats = reader.lines().map(Day5B::calculateSeatId).sorted().collect(Collectors.toList());
        int previousId = -1;
        for (int id : orderedSeats) {
            if (previousId != -1 && id != previousId + 1) {
                System.out.printf("Gap: %d-%d%n", previousId, id);
            }
            previousId = id;
        }
    }

    private static int calculateSeatId(final String selection) {
        final int row = calculateValue(selection.substring(0, 7).replaceAll("F", "0").replaceAll("B", "1"));
        final int column = calculateValue(selection.substring(7, 10).replaceAll("L", "0").replaceAll("R", "1"));
        return row * 8 + column;
    }

    private static int calculateValue(final String selection) {
        if (selection.length() == 1) {
            return Integer.parseInt(selection);
        } else {
            final int baseValue = (int) Math.pow(2, selection.length());
            final int addValue = selection.charAt(0) == '1' ? baseValue / 2 : 0;
            final int addValue2 = calculateValue(selection.substring(1));
            return addValue + addValue2;
        }
    }
}
