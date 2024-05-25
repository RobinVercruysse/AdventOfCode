package be.robinvercruysse.advent.day5;

import be.robinvercruysse.advent.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class TestB {
    @Test
    public void testSolve() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final Long actual = Utils.solve(B.class, "day5b.txt");
        Assert.assertEquals(Long.valueOf(46), actual);
    }
}
