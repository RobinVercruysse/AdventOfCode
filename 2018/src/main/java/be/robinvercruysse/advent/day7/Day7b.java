package be.robinvercruysse.advent.day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7b {
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

        List<Step> workingSteps = new ArrayList<Step>();
        List<Step> waitingSteps = new ArrayList<Step>();

        for (Step step : stepByName.values()) {
            if (step.getAncestors().isEmpty()) {
                waitingSteps.add(step);
            }
        }

        int time = 0;

        while (!workingSteps.isEmpty() || !waitingSteps.isEmpty()) {
            Collections.sort(waitingSteps, new Comparator<Step>() {
                public int compare(final Step o1, final Step o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            List<Step> removeWaitingSteps = new ArrayList<Step>();
            for (Step step : waitingSteps) {
                if (step.canExecute() && workingSteps.size() < 5) {
                    workingSteps.add(step);
                    removeWaitingSteps.add(step);
                }
            }
            for (Step step : removeWaitingSteps) {
                waitingSteps.remove(step);
            }

            time++;

            List<Step> finishedSteps = new ArrayList<Step>();
            for (Step step : workingSteps) {
                step.decreaseTTC();
                if (step.canFinish()) {
                    step.execute();
                    finishedSteps.add(step);
                    for (Step s : step.getDescendants()) {
                        if (!waitingSteps.contains(s)) {
                            waitingSteps.add(s);
                        }
                    }
                }
            }
            for (Step step : finishedSteps) {
                workingSteps.remove(step);
            }
        }

        System.out.println("time: " + time);
    }
}
