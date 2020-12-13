package be.robinvercruysse.advent.day10;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TODO come up with more performant solution
public class Day10B {
    private static int DEVICE_RATING = -1;
    private static long CHAIN_COUNT = 0;

    public static void main(String[] args) {
        final InputStream inputStream = Day10B.class.getClassLoader().getResourceAsStream("day10.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final List<Integer> adapters = reader.lines().map(Integer::parseInt).sorted().collect(Collectors.toList());
        adapters.add(0, 0);//insert outlet rating 0 at start of list
        DEVICE_RATING = adapters.get(adapters.size() - 1) + 3;
        chains(adapters, 0);
        System.out.printf("Chain count: %d%n", CHAIN_COUNT);
    }

    private static void chains(final List<Integer> adapters, final int rating) {
        for (int i = 1; i <= 3 && rating + i <= DEVICE_RATING; i++) {
            if (rating + i == DEVICE_RATING) {
                CHAIN_COUNT++;
            } else if (adapters.contains(rating + i)) {
                chains(adapters, rating + i);
            }
        }
    }
}
