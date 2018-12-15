package be.robinvercruysse.advent.day4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event implements Comparable<Event> {
    private Date timestamp;
    private int guardId;
    private boolean isSleep;
    private boolean isWake;

    public Event(String event) throws ParseException {
        String[] parts = event.split("]");
        String timeString = parts[0].split("\\[")[1];
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(timeString);
        String eventString = parts[1];
        if (eventString.trim().startsWith("Guard")) {
            String[] eventParts = eventString.split(" ");
            this.guardId = Integer.parseInt(eventParts[2].substring(1));
        } else if (eventString.trim().equals("wakes up")) {
            this.isWake = true;
        } else if (eventString.trim().equals("falls asleep")) {
            this.isSleep = true;
        }
    }

    public int getGuardId() {
        return guardId;
    }

    public boolean isSleep() {
        return isSleep;
    }

    public boolean isWake() {
        return isWake;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int compareTo(final Event o) {
        return this.timestamp.compareTo(o.timestamp);
    }
}
