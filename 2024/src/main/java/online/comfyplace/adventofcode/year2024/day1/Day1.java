package online.comfyplace.adventofcode.year2024.day1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {
    static void main() throws Exception {
        try(final InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("day1a.txt")) {
            System.out.println(solve(input));
        }
    }

    public static int solve(final InputStream input) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        final List<Integer> locationIds1 = new ArrayList<>();
        final List<Integer> locationIds2 = new ArrayList<>();
        reader.lines()
                .map(line -> {
                    final String[] parts = line.split(" {3}");
                    return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
                })
                .forEach(locationIdPair -> {
                    locationIds1.add(locationIdPair[0]);
                    locationIds2.add(locationIdPair[1]);
                });
        Collections.sort(locationIds1);
        Collections.sort(locationIds2);
        int distance = 0;
        for (int i = 0; i < locationIds1.size(); i++) {
            final int locationId1 = locationIds1.get(i);
            final int locationId2 = locationIds2.get(i);
            distance += Math.abs(locationId1 - locationId2);
        }
        return distance;
    }
}
