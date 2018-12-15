package be.robinvercruysse.advent.day4;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Guard {
    private int id;

    private Date sleepTimestamp = null;
    private Map<Integer, Integer> countByMinute = new HashMap<Integer, Integer>();

    public Guard(final int id) {
        this.id = id;
    }

    public void wakeUp(final Date timestamp) {
        if (sleepTimestamp != null) {
            for (int i = sleepTimestamp.getMinutes(); i < timestamp.getMinutes(); i++) {
                if (!countByMinute.containsKey(i)) {
                    countByMinute.put(i, 0);
                }
                countByMinute.put(i, countByMinute.get(i) + 1);
            }
        }
        sleepTimestamp = null;
    }

    public int getId() {
        return id;
    }

    public void sleep(final Date timestamp) {
        if (this.sleepTimestamp == null) {
            this.sleepTimestamp = timestamp;
        }
    }

    public int getTotalSleepTime() {
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : countByMinute.entrySet()) {
            total += entry.getValue();
        }
        return total;
    }

    public int getMostCommonMinute() {
        int minute = -1;
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : countByMinute.entrySet()) {
            if (entry.getValue() > count) {
                minute = entry.getKey();
                count = entry.getValue();
            }
        }
        return minute;
    }

    public int getMostCommonMinuteValue() {
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : countByMinute.entrySet()) {
            if (entry.getValue() > count) {
                count = entry.getValue();
            }
        }
        return count;
    }
}
