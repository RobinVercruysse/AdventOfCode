package be.robinvercruysse.advent.day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day12Util {
    public static List<PlantPot> readInitialState(final String filename) throws IOException {
        InputStream input = Day12Launcher.class.getClassLoader().getResourceAsStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String initialStateString = reader.readLine();

        List<PlantPot> initialState = new ArrayList<>(initialStateString.length());

        for (int i = 0; i < initialStateString.length(); i++) {
            PlantPot plantPot = new PlantPot(i);
            plantPot.setHasPlant(initialStateString.charAt(i) == '#');
            initialState.add(plantPot);
        }

        return initialState;
    }

    public static EvolutionRules readEvolutionRules(final String filename) throws IOException {
        InputStream input = Day12Launcher.class.getClassLoader().getResourceAsStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        EvolutionRules evolutionRules = new EvolutionRules();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            char[] inputParts = parts[0].toCharArray();
            boolean evolvesToPlant = parts[2].equals("#");
            boolean[] inputState = new boolean[inputParts.length];
            for (int i = 0; i < inputParts.length; i++) {
                inputState[i] = inputParts[i] == '#';
            }
            evolutionRules.when(inputState).then(evolvesToPlant);
        }

        return evolutionRules;
    }
}
