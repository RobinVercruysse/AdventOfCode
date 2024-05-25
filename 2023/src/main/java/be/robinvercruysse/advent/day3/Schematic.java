package be.robinvercruysse.advent.day3;

import java.util.Map;
import java.util.Optional;

public record Schematic(int maxX, int maxY, Map<Coordinates, Part> parts, Map<Coordinates, Symbol> symbols) {
    public Optional<Part> getPartAt(final Coordinates coordinates) {
        return Optional.ofNullable(parts.get(coordinates));
    }
}
