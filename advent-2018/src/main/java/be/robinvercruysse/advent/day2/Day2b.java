package be.robinvercruysse.advent.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day2b {
    public static void main(String[] args) throws IOException {
        InputStream input = Day2b.class.getClassLoader().getResourceAsStream("be/robinvercruysse/advent/day2/input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String line = null;
        List<char[]> lines = new ArrayList<char[]>();

        while ((line = reader.readLine()) != null) {
            char[] current = line.toCharArray();
            for (char[] seenLine : lines) {
                int diff = 0;
                for (int i = 0; i < 26; i++) {
                    if (seenLine[i] != current[i]) {
                        diff++;
                    }
                }
                if (diff <= 1) {
                    System.out.println("line1: " + new String(seenLine));
                    System.out.println("line2: " + new String(current));
                }
            }
            lines.add(current);
        }
    }
}
