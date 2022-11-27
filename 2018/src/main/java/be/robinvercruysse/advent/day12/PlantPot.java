package be.robinvercruysse.advent.day12;

public class PlantPot {
    private int number;
    private boolean hasPlant;

    public PlantPot(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean hasPlant() {
        return hasPlant;
    }

    public void setHasPlant(boolean hasPlant) {
        this.hasPlant = hasPlant;
    }
}
