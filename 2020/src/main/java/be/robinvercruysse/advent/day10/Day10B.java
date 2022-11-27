package be.robinvercruysse.advent.day10;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day10B {
    public static void main(String[] args) {
        final InputStream inputStream = Day10B.class.getClassLoader().getResourceAsStream("day10.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final List<Integer> adapters = reader.lines().map(Integer::parseInt).sorted().collect(Collectors.toList());
        adapters.add(0, 0);//insert outlet rating 0 at start of list
        final int deviceRating = adapters.get(adapters.size() - 1) + 3;
        final Map<Integer, Long> adapterPathCount = new HashMap<>();

        for (int i = adapters.size() - 1; i >= 0; i--) {
            final int rating = adapters.get(i);
            long paths = 0;
            for (int j = 1; j <= 3 && rating + j <= deviceRating; j++) {
                if (rating + j == deviceRating) {
                    paths++;
                } else if (adapterPathCount.containsKey(rating + j)) {
                    paths += adapterPathCount.get(rating +j);
                }
            }
            adapterPathCount.put(rating, paths);
        }

        System.out.printf("Chain count: %d%n", adapterPathCount.get(0));
    }
}
