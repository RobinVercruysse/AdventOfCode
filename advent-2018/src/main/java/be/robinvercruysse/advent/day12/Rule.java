package be.robinvercruysse.advent.day12;

public class Rule {
    private int value = 0;

    public Rule(boolean... input) {
        for (boolean b : input) {
            value = (value << 1) + (b ? 1 : 0);
        }
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Rule)) {
            return false;
        }
        return hashCode() == obj.hashCode();
    }
}
