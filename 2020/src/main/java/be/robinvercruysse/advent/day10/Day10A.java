package be.robinvercruysse.advent.day10;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day10A {
    public static void main(String[] args) {
        final InputStream inputStream = Day10A.class.getClassLoader().getResourceAsStream("day10.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final List<Integer> adapters = reader.lines().map(Integer::parseInt).sorted().collect(Collectors.toList());
        adapters.add(0, 0);//insert outlet rating 0 at start of list
        final Map<Integer, AtomicInteger> differences = new HashMap<>();
        for (int i = 1; i < adapters.size(); i++) {
            final int diff = adapters.get(i) - adapters.get(i - 1);
            differences.putIfAbsent(diff, new AtomicInteger());
            differences.get(diff).incrementAndGet();
        }
        //diff between highest adapter and device
        differences.putIfAbsent(3, new AtomicInteger());
        differences.get(3).incrementAndGet();

        differences.forEach((diff, count) -> {
            System.out.printf("Difference %d occurs %d times%n", diff, count.get());
        });
        System.out.printf("Product of 1 and 3 diffs is %d%n", differences.get(1).get() * differences.get(3).get());
    }
}
