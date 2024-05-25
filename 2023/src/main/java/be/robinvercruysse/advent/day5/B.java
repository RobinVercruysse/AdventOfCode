package be.robinvercruysse.advent.day5;

import be.robinvercruysse.advent.DaySolver;
import be.robinvercruysse.advent.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class B implements DaySolver<Long> {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println(Utils.solve(B.class, "day5.txt"));
    }

    @Override
    public Long solve(Stream<String> inputLines) {


        return 0L;
    }

}
