package online.comfyplace.adventofcode.year2024.day1;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {
    @Test
    void testPart1() throws Exception {
        try(final InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("day1.txt")) {
            assertEquals(11, Day1.solve(input));
        }
    }
}
