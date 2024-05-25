package be.robinvercruysse.advent.day5;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private final List<Range> ranges = new ArrayList<>();

    public void addRange(final Range range) {
        ranges.add(range);
    }

    public long map(final long source) {
        long result = source;
        for (Range range : ranges) {
            if (source >= range.source() && source < range.source() + range.length()) {
                result = source + range.destination() - range.source();
                break;
            }
        }
        return result;
    }
}
