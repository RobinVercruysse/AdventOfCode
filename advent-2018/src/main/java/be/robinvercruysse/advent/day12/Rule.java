package be.robinvercruysse.advent.day12;

public class Rule {
    private int value = 0;

    public Rule(boolean... input) {
        for (int i = input.length - 1; i >= 0; i--) {
            if (input[i]) {
                value += 10 * (i + 1);
            }
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
