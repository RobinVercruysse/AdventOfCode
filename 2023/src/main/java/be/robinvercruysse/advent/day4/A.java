package be.robinvercruysse.advent.day4;

import be.robinvercruysse.advent.DaySolver;
import be.robinvercruysse.advent.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class A implements DaySolver<Integer> {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println(Utils.solve(A.class, "day4.txt"));
    }

    @Override
    public Integer solve(Stream<String> inputLines) {
        return inputLines.map(Card::fromString).mapToInt(card -> {
            int power = -1;
            for (int actualNr : card.actualNrs()) {
                if (card.winningNrs().contains(actualNr)) {
                    power++;
                }
            }
            return Double.valueOf(Math.pow(2, power)).intValue();
        }).sum();
    }
}
