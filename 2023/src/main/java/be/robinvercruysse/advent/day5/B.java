package be.robinvercruysse.advent.day5;

import be.robinvercruysse.advent.DaySolver;
import be.robinvercruysse.advent.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Stream;

public class B implements DaySolver<Long> {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println(Utils.solve(B.class, "day5.txt"));
    }

    @Override
    public Long solve(Stream<String> inputLines) {
        final List<String> lines = inputLines.toList();
        final List<SeedRange> seedRanges = parseSeedRanges(lines.getFirst());
        final List<Mapper> mappers = A.parseMappers(lines.subList(1, lines.size()));

        final NavigableMap<Long, Long> modifiers = new TreeMap<>();
        modifiers.put(Long.MIN_VALUE, 0L);
        modifiers.put(Long.MAX_VALUE, 0L);

        for (Mapper mapper : mappers) {
            for (Range range : mapper.getRanges()) {
                final long modifier = range.destination() - range.source();
                // TODO figure out how to collapse multiple mappers
            }
        }

        return 0L;
    }

    private static List<SeedRange> parseSeedRanges(final String line) {
        final String[] parts = line.split(" ");
        final List<SeedRange> seedRanges = new ArrayList<>();
        for (int i = 1; i < parts.length; i += 2) {
            final int start = Integer.parseInt(parts[i]);
            final int length = Integer.parseInt(parts[i + 1]);
            seedRanges.add(new SeedRange(start, length));
        }
        return seedRanges;
    }

    private record SeedRange(long start, long length) {}
}
