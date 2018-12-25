package be.robinvercruysse.advent.day12;

import java.io.IOException;
import java.util.List;

public class Day12Launcher {
    public static void main(String[] args) throws IOException {
        List<PlantPot> initialState = Day12Util.readInitialState("be/robinvercruysse/advent/day12/initial_state");
        EvolutionRules evolutionRules = Day12Util.readEvolutionRules("be/robinvercruysse/advent/day12/evolutions");

        Day12 day12 = new Day12(initialState, evolutionRules);

        for (int i = 0; i < 20; i++) {
            day12.evolve();
        }

        System.out.println("sum: " + day12.getPlantPotSum());
    }
}
