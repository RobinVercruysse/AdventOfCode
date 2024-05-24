package be.robinvercruysse.advent.day2;

import be.robinvercruysse.advent.DaySolver;
import be.robinvercruysse.advent.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class A implements DaySolver<Integer> {
    private static final int MAX_RED = 12;
    private static final int MAX_GREEN = 13;
    private static final int MAX_BLUE = 14;

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println(Utils.solve(A.class, "day2.txt"));
    }

    @Override
    public Integer solve(Stream<String> inputLines) {
        return inputLines.map(A::lineToGame)
                .filter(game -> MAX_RED >= game.getMinRed() && MAX_GREEN >= game.getMinGreen() && MAX_BLUE >= game.getMinBlue())
                .mapToInt(Game::getId)
                .sum();
    }

    public static Game lineToGame(String line) {
        final String[] lineSplit = line.split(":");
        final int id = Integer.parseInt(lineSplit[0].split(" ")[1]);
        final String[] pullStrings = lineSplit[1].split(";");
        final List<Pull> pulls = new ArrayList<>();
        for (String pullString : pullStrings) {
            final String[] cubes = pullString.split(",");
            int red = 0;
            int green = 0;
            int blue = 0;
            for (String cube : cubes) {
                final String[] cubeParts = cube.trim().split(" ");
                final int count = Integer.parseInt(cubeParts[0]);
                switch (cubeParts[1]) {
                    case "red" -> red = count;
                    case "green" -> green = count;
                    case "blue" -> blue = count;
                }
            }
            pulls.add(new Pull(red, green, blue));
        }
        return new Game(id, pulls);
    }
}
