package be.robinvercruysse.advent.day8;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day8Test {
    @Test
    public void testMetadataSum() throws IOException {
        final String input = "be/robinvercruysse/advent/day8/input";
        final int expectedResult = 138;

        final int actualResult = new Day8().getMetadataSum(input);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testRootNodeValue() throws IOException {
        final String input = "be/robinvercruysse/advent/day8/input";
        final int expectedResult = 66;

        final int actualResult = new Day8().getRootNodeValue(input);

        assertEquals(expectedResult, actualResult);
    }
}
