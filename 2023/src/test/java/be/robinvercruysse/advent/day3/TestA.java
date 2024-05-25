package be.robinvercruysse.advent.day3;

import be.robinvercruysse.advent.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class TestA {
    @Test
    public void testSolve() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final Integer actual = Utils.solve(A.class,"day3a.txt");
        Assert.assertEquals(Integer.valueOf(4361), actual);
    }
}
