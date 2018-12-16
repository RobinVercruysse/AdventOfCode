package be.robinvercruysse.advent.day11;

import javafx.util.Pair;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Day11 {
    private static final int GRID_SIZE = 300;

    private static final Map<Point, Integer> squareCache = new HashMap<>();

    public static void clearCache() {
        squareCache.clear();
    }

    public Pair<Cell, Integer> getLargestPowerSquare(int gridSerialNr, int squareSize) {
        int highestPowerLevel = Integer.MIN_VALUE;
        Cell highestPowerLevelCell = null;

        if (squareCache.isEmpty()) {
            for (int topLeftX = 1; topLeftX <= GRID_SIZE - squareSize + 1; topLeftX++) {
                for (int topLeftY = 1; topLeftY <= GRID_SIZE - squareSize + 1; topLeftY++) {
                    int powerLevel = getPowerLevelOfSquare(gridSerialNr, squareSize, topLeftX, topLeftY);
                    if (powerLevel > highestPowerLevel) {
                        highestPowerLevel = powerLevel;
                        highestPowerLevelCell = new Cell(gridSerialNr, topLeftX, topLeftY);
                    }
                    squareCache.put(new Point(topLeftX, topLeftY), powerLevel);
                }
            }
        } else {
            for (int topLeftX = 1; topLeftX <= GRID_SIZE - squareSize + 1; topLeftX++) {
                for (int topLeftY = 1; topLeftY <= GRID_SIZE - squareSize + 1; topLeftY++) {

                    int powerLevel = squareCache.get(new Point(topLeftX, topLeftY));
                    powerLevel += getAdditionalPowerLevelOfSquare(gridSerialNr, squareSize, topLeftX, topLeftY);
                    if (powerLevel > highestPowerLevel) {
                        highestPowerLevel = powerLevel;
                        highestPowerLevelCell = new Cell(gridSerialNr, topLeftX, topLeftY);
                    }
                    squareCache.put(new Point(topLeftX, topLeftY), powerLevel);
                }
            }
        }

        return new Pair<>(highestPowerLevelCell, highestPowerLevel);
    }

    private int getPowerLevelOfSquare(int gridSerialNr, int squareSize, int topLeftX, int topLeftY) {
        int powerLevel = 0;
        for (int x = topLeftX; x < topLeftX + squareSize; x++) {
            for (int y = topLeftY; y < topLeftY + squareSize; y++) {
                powerLevel += new Cell(gridSerialNr, x, y).getPowerLevel();
            }
        }
        return powerLevel;
    }

    private int getAdditionalPowerLevelOfSquare(int gridSerialNr, int squareSize, int topLeftX, int topLeftY) {
        int powerLevel = 0;
        for (int x = topLeftX; x < topLeftX + squareSize - 1; x++) {
            int y = topLeftY + squareSize - 1;
            powerLevel += new Cell(gridSerialNr, x, y).getPowerLevel();
        }
        for (int y = topLeftY; y < topLeftY + squareSize - 1; y++) {
            int x = topLeftX + squareSize - 1;
            powerLevel += new Cell(gridSerialNr, x, y).getPowerLevel();
        }
        int x = topLeftX + squareSize - 1;
        int y = topLeftY + squareSize - 1;
        powerLevel += new Cell(gridSerialNr, x, y).getPowerLevel();
        return powerLevel;
    }
}
