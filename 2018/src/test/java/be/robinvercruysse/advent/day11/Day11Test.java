package be.robinvercruysse.advent.day11;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {
    @Test
    public void testCell_example1() {
        Cell cell = new Cell(57, 122, 79);
        int expectedValue = -5;
        int actualValue = cell.getPowerLevel();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCell_example2() {
        Cell cell = new Cell(39, 217, 196);
        int expectedValue = 0;
        int actualValue = cell.getPowerLevel();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCell_example3() {
        Cell cell = new Cell(71, 101, 153);
        int expectedValue = 4;
        int actualValue = cell.getPowerLevel();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testDay11() {
        Day11.clearCache();
        int highestPowerLevel = Integer.MIN_VALUE;
        int highestPowerLevelSquareSize = 0;
        Cell highestPowerLevelTopLeftCell = null;

        for (int squareSize = 1; squareSize < 301; squareSize++) {
            Map.Entry<Cell, Integer> result = new Day11().getLargestPowerSquare(18, squareSize);
            if (result.getValue() > highestPowerLevel) {
                highestPowerLevel = result.getValue();
                highestPowerLevelSquareSize = squareSize;
                highestPowerLevelTopLeftCell = result.getKey();
            }
            System.out.println("square size " + squareSize + " has highest powerlevel " + result.getValue());
        }

        System.out.println("highest power level " + highestPowerLevel + " with square size " + highestPowerLevelSquareSize + " with top left cell " + highestPowerLevelTopLeftCell);

        assertEquals(113, highestPowerLevel);
        assertEquals(16, highestPowerLevelSquareSize);
        assertEquals(90, highestPowerLevelTopLeftCell.getX());
        assertEquals(269, highestPowerLevelTopLeftCell.getY());
    }
}
