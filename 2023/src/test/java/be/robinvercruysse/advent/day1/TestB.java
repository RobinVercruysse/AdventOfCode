package be.robinvercruysse.advent.day1;

import be.robinvercruysse.advent.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class TestB {
    @Test
    public void testSolve() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final Integer actual = Utils.solve(B.class, "day1b.txt");
        Assert.assertEquals(Integer.valueOf(281), actual);
    }
}
