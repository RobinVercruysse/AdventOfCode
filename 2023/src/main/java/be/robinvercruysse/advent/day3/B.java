package be.robinvercruysse.advent.day3;

import be.robinvercruysse.advent.DaySolver;
import be.robinvercruysse.advent.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class B implements DaySolver<Integer> {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println(Utils.solve(B.class, "day3.txt"));
    }

    @Override
    public Integer solve(Stream<String> inputLines) {
        final Schematic schematic = A.linesToSchematic(inputLines);
        return schematic.symbols().values().stream()
                .filter(symbol -> symbol.getSymbol().equals("*") && symbol.getAdjacentParts().size() == 2)
                .mapToInt(symbol -> {
                    int result = 1;
                    for (Part part : symbol.getAdjacentParts()) {
                        result = result * part.getNumber();
                    }
                    return result;
                })
                .sum();
    }
}
