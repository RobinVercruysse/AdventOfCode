package be.robinvercruysse.advent.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {
    private final Map<Integer, PlantPot> plantPotByNr = new HashMap<>();
    private final EvolutionRules evolutionRules;

    private List<PlantPot> currentState;

    public Day12(List<PlantPot> initialState, EvolutionRules evolutionRules) {
        this.currentState = initialState;
        this.evolutionRules = evolutionRules;
        mapState();
    }

    private void mapState() {
        plantPotByNr.clear();
        for (PlantPot plantPot : currentState) {
            plantPotByNr.put(plantPot.getNumber(), plantPot);
        }
    }

    public int getPlantPotSum() {
        int sum = 0;
        for (PlantPot plantPot : currentState) {
            if (plantPot.hasPlant()) {
                sum += plantPot.getNumber();
            }
        }
        return sum;
    }

    public void evolve() {
        List<PlantPot> newState = new ArrayList<>();

        boolean evolves;
        PlantPot plantPot;

        //check first - 2
        evolves = evolutionRules.evolves(false, false, false, false, currentState.get(0).hasPlant());
        if (evolves) {
            plantPot = new PlantPot(currentState.get(0).getNumber() - 2);
            plantPot.setHasPlant(true);
            newState.add(plantPot);
        }

        //check first - 1
        evolves = evolutionRules.evolves(false, false, false, currentState.get(0).hasPlant(), currentState.get(1).hasPlant());
        if (evolves || !newState.isEmpty()) {
            plantPot = new PlantPot(currentState.get(0).getNumber() - 1);
            plantPot.setHasPlant(evolves);
            newState.add(plantPot);
        }

        //evolve existing plants
        for (int i = 0; i < currentState.size(); i++) {
            boolean previous2 = i - 2 < 0 ? false : currentState.get(i - 2).hasPlant();
            boolean previous1 = i - 1 < 0 ? false : currentState.get(i - 1).hasPlant();
            boolean current = currentState.get(i).hasPlant();
            boolean next1 = i + 1 >= currentState.size() ? false : currentState.get(i + 1).hasPlant();
            boolean next2 = i + 2 >= currentState.size() ? false : currentState.get(i + 2).hasPlant();

            plantPot = new PlantPot(currentState.get(i).getNumber());
            plantPot.setHasPlant(evolutionRules.evolves(previous2, previous1, current, next1, next2));
            newState.add(plantPot);
        }

        //check if append 2
        boolean evolvesLastPlusTwo = evolutionRules.evolves(currentState.get(currentState.size() - 1).hasPlant(), false, false, false, false);
        evolves = evolutionRules.evolves(currentState.get(currentState.size() - 2).hasPlant(), currentState.get(currentState.size() - 1).hasPlant(), false, false, false);
        if (evolves || evolvesLastPlusTwo) {
            plantPot = new PlantPot(currentState.get(currentState.size() - 1).getNumber() + 1);
            plantPot.setHasPlant(evolves);
            newState.add(plantPot);
        }
        if (evolvesLastPlusTwo) {
            plantPot = new PlantPot(currentState.get(currentState.size() - 1).getNumber() + 2);
            plantPot.setHasPlant(true);
            newState.add(plantPot);
        }

        currentState = newState;
    }

    public String getStateString() {
        StringBuilder builder = new StringBuilder();
        for (PlantPot plantPot : currentState) {
            builder.append(plantPot.hasPlant() ? "#" : ".");
        }
        return builder.toString();
    }
}
