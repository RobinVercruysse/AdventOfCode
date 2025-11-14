package be.robinvercruysse.advent.day4;

import be.robinvercruysse.advent.Utils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestB {
    @Test
    public void testSolve() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final Integer actual = Utils.solve(B.class, "day4b.txt");
        assertEquals(Integer.valueOf(30), actual);
    }
}
