package be.robinvercruysse.advent.day12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {
    private Map<Integer, PlantPot> plantPotByNr = new HashMap<>();
    private EvolutionRules evolutionRules;

    private List<PlantPot> currentState;

    public Day12(List<PlantPot> initialState, EvolutionRules evolutionRules) {
        this.currentState = initialState;
        this.evolutionRules = evolutionRules;
    }

    public int getPlantPotSum() {
        //TODO implement
        return -1;
    }
}
