package be.robinvercruysse.advent.day12;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day12Test {
    @Test
    public void testEvolutionRules() {
        EvolutionRules evolutionRules = new EvolutionRules();
        evolutionRules.when(true, false, true, false, true).then(true);
        evolutionRules.when(false, false, true, false, false).then(false);

        assertTrue(evolutionRules.evolves(true, false, true, false, true));
        assertFalse(evolutionRules.evolves(false, false, true, false, false));
    }
}
