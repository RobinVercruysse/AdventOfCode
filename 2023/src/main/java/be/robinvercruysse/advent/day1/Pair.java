package be.robinvercruysse.advent.day1;

public record Pair(char firstDigit, char lastDigit) {
    static Pair fromLine(String line) {
        char first = Character.MIN_VALUE;
        char last = Character.MIN_VALUE;
        for (final char value : line.toCharArray()) {
            if (Character.isDigit(value)) {
                if (first == Character.MIN_VALUE) {
                    first = value;
                }
                last = value;
            }
        }
        return new Pair(first, last);
    }

    static int toInt(Pair pair) {
        final String value = String.valueOf(new char[]{pair.firstDigit(), pair.lastDigit()});
        return Integer.parseInt(value);
    }
}
