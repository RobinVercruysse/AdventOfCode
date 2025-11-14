package be.robinvercruysse.advent.day3;

import be.robinvercruysse.advent.DaySolver;
import be.robinvercruysse.advent.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class A implements DaySolver<Integer> {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println(Utils.solve(A.class, "day3.txt"));
    }

    @Override
    public Integer solve(Stream<String> inputLines) {
        final Schematic schematic = linesToSchematic(inputLines);
        return schematic.parts().values().stream().distinct().filter(Part::isAdjacent).mapToInt(Part::getNumber).sum();
    }

    public static Schematic linesToSchematic(Stream<String> lines) {
        int maxX = -1;
        final List<String> linesList = lines.toList();
        final Map<Coordinates, Part> parts = new HashMap<>();
        final Map<Coordinates, Symbol> symbols = new HashMap<>();
        int y = -1;
        for (String line : linesList) {
            maxX = line.length() - 1;
            int x = 0;
            y++;
            while (x < line.length()) {
                if (Character.isDigit(line.charAt(x))) {
                    StringBuilder partBuilder = new StringBuilder();
                    final List<Coordinates> partCoordinates = new ArrayList<>();
                    while (x < line.length() && Character.isDigit(line.charAt(x))) {
                        partBuilder.append(line.charAt(x));
                        partCoordinates.add(new Coordinates(x, y));
                        x++;
                    }
                    final Part part = new Part(Integer.parseInt(partBuilder.toString()));
                    for (Coordinates coordinates : partCoordinates) {
                        parts.put(coordinates, part);
                    }
                } else if ('.' == line.charAt(x)) {
                    x++;
                } else {
                    final Coordinates coordinates = new Coordinates(x, y);
                    final Symbol symbol = new Symbol(String.valueOf(line.charAt(x)));
                    symbols.put(coordinates, symbol);
                    x++;
                }
            }
        }
        final Schematic schematic = new Schematic(maxX, y, parts, symbols);
        for (Map.Entry<Coordinates, Symbol> entry : schematic.symbols().entrySet()) {
            final int symbolX = entry.getKey().x();
            final int symbolY = entry.getKey().y();
            final Symbol symbol = entry.getValue();
            schematic.getPartAt(new Coordinates(symbolX - 1, symbolY - 1)).ifPresent(part -> handleAdjacentPart(symbol, part));
            schematic.getPartAt(new Coordinates(symbolX - 1, symbolY)).ifPresent(part -> handleAdjacentPart(symbol, part));
            schematic.getPartAt(new Coordinates(symbolX - 1, symbolY + 1)).ifPresent(part -> handleAdjacentPart(symbol, part));
            schematic.getPartAt(new Coordinates(symbolX, symbolY - 1)).ifPresent(part -> handleAdjacentPart(symbol, part));
            schematic.getPartAt(new Coordinates(symbolX + 1, symbolY - 1)).ifPresent(part -> handleAdjacentPart(symbol, part));
            schematic.getPartAt(new Coordinates(symbolX, symbolY + 1)).ifPresent(part -> handleAdjacentPart(symbol, part));
            schematic.getPartAt(new Coordinates(symbolX + 1, symbolY)).ifPresent(part -> handleAdjacentPart(symbol, part));
            schematic.getPartAt(new Coordinates(symbolX + 1, symbolY + 1)).ifPresent(part -> handleAdjacentPart(symbol, part));
        }
        return schematic;
    }

    private static void handleAdjacentPart(final Symbol symbol, final Part part) {
        part.markAdjacent();
        symbol.addAdjacentPart(part);
    }
}
