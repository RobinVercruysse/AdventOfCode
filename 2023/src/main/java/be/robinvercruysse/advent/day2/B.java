package be.robinvercruysse.advent.day2;

import be.robinvercruysse.advent.DaySolver;
import be.robinvercruysse.advent.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class B implements DaySolver<Integer> {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println(Utils.solve(B.class, "day2.txt"));
    }

    @Override
    public Integer solve(Stream<String> inputLines) {
        return inputLines
                .map(A::lineToGame)
                .mapToInt(game -> game.getMinRed() * game.getMinGreen() * game.getMinBlue())
                .sum();
    }
}
