package be.robinvercruysse.advent.day5;

import be.robinvercruysse.advent.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class TestA {
    @Test
    public void testSolve() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final Long actual = Utils.solve(A.class, "day5a.txt");
        Assert.assertEquals(Long.valueOf(35), actual);
    }
}
