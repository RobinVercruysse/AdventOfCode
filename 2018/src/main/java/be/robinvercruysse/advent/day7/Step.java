package be.robinvercruysse.advent.day7;

import java.util.ArrayList;
import java.util.List;

public class Step {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String name;
    private List<Step> ancestors = new ArrayList<Step>();
    private List<Step> descendants = new ArrayList<Step>();
    private boolean done = false;
    private int timeToCompletion;

    public Step(final String name) {
        this.name = name;
        this.timeToCompletion = 60 + (ALPHABET.indexOf(name) + 1);
    }

    public String getName() {
        return name;
    }

    public List<Step> getAncestors() {
        return ancestors;
    }

    public List<Step> getDescendants() {
        return descendants;
    }

    public boolean isDone() {
        return done;
    }

    public void decreaseTTC() {
        timeToCompletion--;
    }

    public boolean canFinish() {
        return timeToCompletion <= 0;
    }

    public void execute() {
        done = true;
    }

    public boolean canExecute() {
        for (Step ancestor : ancestors) {
            if (!ancestor.isDone()) {
                return false;
            }
        }
        return true;
    }
}
