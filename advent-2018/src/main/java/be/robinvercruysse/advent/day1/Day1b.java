package be.robinvercruysse.advent.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day1b {
    public static void main(String[] args) throws IOException {
        boolean firstdone = false;

        List<Integer> seen = new ArrayList<Integer>();
        int freq = 0;

        while (!firstdone) {

            InputStream input = Day1b.class.getClassLoader().getResourceAsStream("be/robinvercruysse/advent/day1/input");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line = null;

            while (!firstdone && (line = reader.readLine()) != null) {
                if (line.startsWith("-")) {
                    int value = Integer.parseInt(line.substring(1));
                    freq -= value;
                } else if (line.startsWith("+")) {
                    int value = Integer.parseInt(line.substring(1));
                    freq += value;
                } else {
                    System.out.println("anomaly: " + line);
                }
                if (seen.contains(freq)) {
                    firstdone = true;
                    System.out.println("first dupe: " + freq);
                } else {
                    seen.add(freq);
                }
            }
        }
    }
}
