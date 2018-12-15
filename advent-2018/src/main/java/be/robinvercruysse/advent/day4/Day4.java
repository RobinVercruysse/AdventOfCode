package be.robinvercruysse.advent.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 {
    public static void main(String[] args) throws IOException, ParseException {
        InputStream input = Day4.class.getClassLoader().getResourceAsStream("input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String line;
        Map<Integer, Guard> guardById = new HashMap<Integer, Guard>();
        int currentGuard = -1;

        List<Event> events = new ArrayList<Event>();

        while ((line = reader.readLine()) != null) {
            events.add(new Event(line));
        }

        Collections.sort(events);

        for (Event event : events) {
            if (event.isWake()) {
                if (currentGuard != -1) {
                    guardById.get(currentGuard).wakeUp(event.getTimestamp());
                }
            } else if (event.isSleep()) {
                if (currentGuard != -1) {
                    guardById.get(currentGuard).sleep(event.getTimestamp());
                }
            } else if (event.getGuardId() > 0) {
                if (currentGuard != -1) {
                    guardById.get(currentGuard).wakeUp(event.getTimestamp());
                }
                if (!guardById.containsKey(event.getGuardId())) {
                    guardById.put(event.getGuardId(), new Guard(event.getGuardId()));
                }
                currentGuard = event.getGuardId();
            } else {
                String debug = "";
            }
        }

        int longestSleep = 0;
        Guard lazyGuard = null;

        for (Guard guard : guardById.values()) {
            if (guard.getTotalSleepTime() > longestSleep) {
                lazyGuard = guard;
                longestSleep = guard.getTotalSleepTime();
            }
        }

        System.out.println("laziest guard: " + lazyGuard.getId());
        System.out.println("common minute: " + lazyGuard.getMostCommonMinute());
        System.out.println("hash: " + (lazyGuard.getId() * lazyGuard.getMostCommonMinute()));

        int commonMinuteCount = 0;
        Guard patternedGuard = null;

        for (Guard guard : guardById.values()) {
            if (guard.getMostCommonMinuteValue() > commonMinuteCount) {
                patternedGuard = guard;
                commonMinuteCount = guard.getMostCommonMinuteValue();
            }
        }

        System.out.println("pattern guard: " + patternedGuard.getId());
        System.out.println("common minute: " + patternedGuard.getMostCommonMinute());
        System.out.println("hash: " + (patternedGuard.getId() * patternedGuard.getMostCommonMinute()));
    }
}
