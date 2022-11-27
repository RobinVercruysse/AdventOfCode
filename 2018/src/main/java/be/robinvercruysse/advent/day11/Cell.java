package be.robinvercruysse.advent.day11;

public class Cell {
    private final int gridSerialNr;
    private final int x;
    private final int y;

    public Cell(int gridSerialNr, int x, int y) {
        this.gridSerialNr = gridSerialNr;
        this.x = x;
        this.y = y;
    }

    public int getPowerLevel() {
        final int rackId = x + 10;
        int powerLevel = rackId * y;
        powerLevel += gridSerialNr;
        powerLevel = powerLevel * rackId;

        if (powerLevel < 100) {
            powerLevel = 0;
        } else {
            final String powerLevelString = String.valueOf(powerLevel);
            int hundredIndex = powerLevelString.length() - 3;
            powerLevel = Integer.parseInt(powerLevelString.substring(hundredIndex, hundredIndex + 1));
        }
        return powerLevel - 5;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
