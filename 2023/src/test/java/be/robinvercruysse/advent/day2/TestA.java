package be.robinvercruysse.advent.day2;

import be.robinvercruysse.advent.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class TestA {
    @Test
    public void testSolve() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final int actual = Utils.solve(A.class, "day2a.txt");
        Assert.assertEquals(8, actual);
    }
}
