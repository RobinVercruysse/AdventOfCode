package be.robinvercruysse.advent.day12;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day12Test {
    @Test
    public void testEvolutionRules() {
        EvolutionRules evolutionRules = new EvolutionRules();
        evolutionRules.when(true, false, true, false, true).then(true);
        evolutionRules.when(false, false, true, false, false).then(false);

        assertTrue(evolutionRules.evolves(true, false, true, false, true));
        assertFalse(evolutionRules.evolves(false, false, true, false, false));
    }

    @Test
    public void testExample() throws IOException {
        List<PlantPot> initialState = Day12Util.readInitialState("be/robinvercruysse/advent/day12/initial_state");
        EvolutionRules evolutionRules = Day12Util.readEvolutionRules("be/robinvercruysse/advent/day12/evolutions");

        Day12 day12 = new Day12(initialState, evolutionRules);
        System.out.println(day12.getStateString());

        for (int i = 0; i < 20; i++) {
            day12.evolve();
            System.out.println(day12.getStateString());
            System.out.println(day12.getPlantPotSum());
        }

        //System.out.println("sum: " + day12.getPlantPotSum());
        assertEquals(325, day12.getPlantPotSum());
    }
}
