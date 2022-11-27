package be.robinvercruysse.advent.day11;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day11Launcher {
    public static void main(String[] args) throws IOException {
        InputStream input = Day11Launcher.class.getClassLoader().getResourceAsStream("be/robinvercruysse/advent/day11/input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        int gridSerialNr = Integer.parseInt(reader.readLine());

        System.out.println("Top-left cell of largest power 3x3 square: " + new Day11().getLargestPowerSquare(gridSerialNr, 3).getKey());

        Day11.clearCache();

        int highestPowerLevel = Integer.MIN_VALUE;
        int highestPowerLevelSquareSize = 0;
        Cell highestPowerLevelTopLeftCell = null;

        for (int squareSize = 1; squareSize < 301; squareSize++) {
            Pair<Cell, Integer> result = new Day11().getLargestPowerSquare(gridSerialNr, squareSize);
            if (result.getValue() > highestPowerLevel) {
                highestPowerLevel = result.getValue();
                highestPowerLevelSquareSize = squareSize;
                highestPowerLevelTopLeftCell = result.getKey();
            }
            System.out.println("square size " + squareSize + " has highest powerlevel " + result.getValue());
        }

        System.out.println("highest power level " + highestPowerLevel + " with square size " + highestPowerLevelSquareSize + " with top left cell " + highestPowerLevelTopLeftCell);
    }
}
