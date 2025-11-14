package be.robinvercruysse.advent.day2;

import be.robinvercruysse.advent.Utils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestA {
    @Test
    public void testSolve() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final int actual = Utils.solve(A.class, "day2a.txt");
        assertEquals(8, actual);
    }
}
