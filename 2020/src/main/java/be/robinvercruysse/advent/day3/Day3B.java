package be.robinvercruysse.advent.day3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day3B {
    public static void main(String[] args) {
        final InputStream inputStream = Day3B.class.getClassLoader().getResourceAsStream("day3.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final List<String> lines = reader.lines().collect(Collectors.toList());
        final boolean[][] map = new boolean[lines.get(0).length()][lines.size()];
        for (int y = 0; y < lines.size(); y++) {
            final char[] line = lines.get(y).toCharArray();
            for (int x = 0; x < line.length; x++) {
                map[x][y] = line[x] == '#';
            }
        }
        final List<Map.Entry<Integer, Integer>> slopes = Arrays.asList(
                new AbstractMap.SimpleEntry<>(1, 1),
                new AbstractMap.SimpleEntry<>(3, 1),
                new AbstractMap.SimpleEntry<>(5, 1),
                new AbstractMap.SimpleEntry<>(7, 1),
                new AbstractMap.SimpleEntry<>(1, 2)
        );
        long product = 1;
        final int maxY = lines.size() - 1;
        for (Map.Entry<Integer, Integer> slope : slopes) {
            final int trees = trees(map, slope.getKey(), slope.getValue(), maxY);
            product *= trees;
        }
        System.out.printf("Product: %d%n", product);
    }

    private static int trees(final boolean[][] map, final int slopeX, final int slopeY, final int maxY) {
        int x = 0;
        int y = 0;
        int trees = 0;
        while (y + slopeY <= maxY) {
            x += slopeX;
            if (x >= map.length) {
                x = x % map.length;
            }
            y += slopeY;
            if (map[x][y]) {
                trees++;
            }
        }
        return trees;
    }
}
