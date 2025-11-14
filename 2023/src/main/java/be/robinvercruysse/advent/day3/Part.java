package be.robinvercruysse.advent.day3;

public class Part {
    private final int number;
    private boolean isAdjacent = false;

    public Part(final int number) {
        this.number = number;
    }

    public void markAdjacent() {
        this.isAdjacent = true;
    }

    public int getNumber() {
        return number;
    }

    public boolean isAdjacent() {
        return isAdjacent;
    }
}
