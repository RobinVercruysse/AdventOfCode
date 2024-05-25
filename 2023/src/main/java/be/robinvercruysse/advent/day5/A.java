package be.robinvercruysse.advent.day5;

import be.robinvercruysse.advent.DaySolver;
import be.robinvercruysse.advent.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class A implements DaySolver<Long> {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println(Utils.solve(A.class, "day5.txt"));
    }

    @Override
    public Long solve(Stream<String> inputLines) {
        final List<String> lines = inputLines.toList();
        final List<Long> seeds = parseSeeds(lines.getFirst());
        final List<Mapper> mappers = parseMappers(lines.subList(1, lines.size()));
        final Map<Long, Long> seedToLocation = new HashMap<>();
        for (long seed : seeds) {
            long result = seed;
            for (Mapper mapper : mappers) {
                result = mapper.map(result);
            }
            seedToLocation.put(seed, result);
        }
        return seedToLocation.values().stream().mapToLong(Long::valueOf).min().orElse(-1);
    }

    public static List<Mapper> parseMappers(final List<String> lines) {
        int i = 0;
        final List<Mapper> mappers = new ArrayList<>();
        Mapper currentMapper = null;
        while (i < lines.size()) {
            if (lines.get(i).isEmpty()) {
                if (currentMapper != null) {
                    mappers.add(currentMapper);
                }
                currentMapper = new Mapper();
                i += 2;
            } else {
                if (currentMapper == null) {
                    System.err.println("Index shifting must've fucked up somehow");
                    break;
                }
                currentMapper.addRange(Range.fromString(lines.get(i)));
                i++;
            }
        }
        mappers.add(currentMapper);
        return mappers;
    }

    private static List<Long> parseSeeds(final String line) {
        final String[] parts = line.split(" ");
        final List<Long> seeds = new ArrayList<>();
        for (int i = 1; i < parts.length; i++) {
            seeds.add(Long.parseLong(parts[i]));
        }
        return seeds;
    }
}
