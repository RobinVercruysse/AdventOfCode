package be.robinvercruysse.advent.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day1 {
    public static void main(String[] args) throws IOException {
        InputStream input = Day1.class.getClassLoader().getResourceAsStream("be/robinvercruysse/advent/day1/input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        int freq = 0;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("-")) {
                int value = Integer.parseInt(line.substring(1));
                freq -= value;
            } else if (line.startsWith("+")) {
                int value = Integer.parseInt(line.substring(1));
                freq += value;
            } else {
                System.out.println("anomaly: " + line);
            }
        }
        System.out.println("result: " + freq);
    }
}
