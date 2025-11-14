package be.robinvercruysse.advent.day3;

import java.util.HashSet;
import java.util.Set;

public class Symbol {
    private final String symbol;
    private final Set<Part> adjacentParts = new HashSet<>();

    public Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void addAdjacentPart(final Part part) {
        adjacentParts.add(part);
    }

    public Set<Part> getAdjacentParts() {
        return adjacentParts;
    }
}
