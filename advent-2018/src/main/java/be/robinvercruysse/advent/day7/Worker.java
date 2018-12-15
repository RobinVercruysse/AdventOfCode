package be.robinvercruysse.advent.day7;

public class Worker {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Step currentTask;
    private int timeToCompletion = -1;

    public void assignTask(Step step) {
        this.currentTask = step;
        this.timeToCompletion = 60 + (ALPHABET.indexOf(currentTask.getName())) + 1;
    }

    public void advanceTime() {
        timeToCompletion--;
    }

    public boolean finishTask() {
        return timeToCompletion <= 0 && currentTask != null;
    }

    public Step getCurrentTask() {
        return currentTask;
    }

    public void clearCurrentTask() {
        currentTask = null;
    }

    public boolean isIdle() {
        return currentTask == null;
    }
}
