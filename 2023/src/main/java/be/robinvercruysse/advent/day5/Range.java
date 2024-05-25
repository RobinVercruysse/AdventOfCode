package be.robinvercruysse.advent.day5;

public record Range(long destination, long source, long length) {
    public static Range fromString(final String line) {
        final String[] parts = line.split(" ");
        return new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1]), Long.parseLong(parts[2]));
    }
}
