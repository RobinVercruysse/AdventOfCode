package be.robinvercruysse.advent.day3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Day3A {
    public static void main(String[] args) {
        final InputStream inputStream = Day3A.class.getClassLoader().getResourceAsStream("day1.txt");
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
                map[x][y] = line[x] == '.';
            }
        }
        final int trees = trees(map, 3, 1, lines.size() - 1);
    }

    private static int trees(final boolean[][] map, final int slopeX, final int slopeY, final int maxY) {
        int x = 0;
        int y = 0;
        int trees = 0;
        while (y <= maxY) {
            //apply slope and check for tree
        }
        return trees;
    }
}
