package be.robinvercruysse.advent.day5;

import be.robinvercruysse.advent.Utils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestA {
    @Test
    public void testSolve() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final Long actual = Utils.solve(A.class, "day5a.txt");
        assertEquals(Long.valueOf(35), actual);
    }
}
