package be.robinvercruysse.advent.day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {
    public static void main(String[] args) throws IOException {
        InputStream input = Day7.class.getClassLoader().getResourceAsStream("input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        Map<String, Step> stepByName = new HashMap<String, Step>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            String a = parts[1];
            String b = parts[7];
            Step stepA;
            Step stepB;
            if (!stepByName.containsKey(a)) {
                stepA = new Step(a);
                stepByName.put(a, stepA);
            } else {
                stepA = stepByName.get(a);
            }
            if (!stepByName.containsKey(b)) {
                stepB = new Step(b);
                stepByName.put(b, stepB);
            } else {
                stepB = stepByName.get(b);
            }

            stepA.getDescendants().add(stepB);
            stepB.getAncestors().add(stepA);
        }

        List<Step> buffer = new ArrayList<Step>();
        List<Step> finalStep = new ArrayList<Step>();

        for (Step step : stepByName.values()) {
            if (step.getAncestors().isEmpty()) {
                buffer.add(step);
            }
            if (step.getDescendants().isEmpty()) {
                finalStep.add(step);
            }
        }

        List<Step> history = new ArrayList<Step>();

        while (!buffer.isEmpty()) {
            Step currentStep = null;

            for (Step bufferStep : buffer) {
                if (currentStep == null || bufferStep.getName().compareTo(currentStep.getName()) < 0) {
                    currentStep = bufferStep;
                }
            }

            currentStep.execute();
            history.add(currentStep);
            buffer.remove(currentStep);

            for (Step step : currentStep.getDescendants()) {
                if (step.canExecute()) {
                    buffer.add(step);
                }
            }
        }

        for (Step step : history) {
            System.out.print(step.getName());
        }
    }
}
