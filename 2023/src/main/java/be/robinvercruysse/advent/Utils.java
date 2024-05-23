package be.robinvercruysse.advent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class Utils {
    public static <T> T solve(Class<? extends DaySolver<T>> solver, String inputFileName) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        DaySolver<T> solverInstance = solver.getConstructor().newInstance();
        return solverInstance.solve(getFileLines(inputFileName));
    }

    public static Stream<String> getFileLines(String fileName) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            return reader.lines();
        } else {
            return Stream.empty();
        }
    }
}
