package be.robinvercruysse.advent;

import java.util.stream.Stream;

public interface DaySolver<T> {
    T solve(Stream<String> inputLines);
}
