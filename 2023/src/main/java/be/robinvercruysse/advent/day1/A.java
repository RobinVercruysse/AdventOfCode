package be.robinvercruysse.advent.day1;

import be.robinvercruysse.advent.DaySolver;
import be.robinvercruysse.advent.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class A implements DaySolver<Integer> {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println(Utils.solve(A.class, "day1.txt"));
    }

    public Integer solve(Stream<String> inputLines) {
        return inputLines.map(Pair::fromLine).mapToInt(Pair::toInt).sum();
    }
}
