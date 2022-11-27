package be.robinvercruysse.advent.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Day2 {
    public static void main(String[] args) throws IOException {
        InputStream input = Day2.class.getClassLoader().getResourceAsStream("be/robinvercruysse/advent/day2/input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String line = null;

        int twice = 0;
        int thrice = 0;

        while ((line = reader.readLine()) != null) {
            Map<Character, Integer> charCounts = new HashMap<Character, Integer>();
            for (char character : line.toCharArray()) {
                if (!charCounts.containsKey(character)) {
                    charCounts.put(character, 0);
                }
                charCounts.put(character, charCounts.get(character) + 1);
            }

            if (charCounts.values().contains(2)) {
                twice++;
            }
            if (charCounts.values().contains(3)) {
                thrice++;
            }
        }

        System.out.println("twice: " + twice);
        System.out.println("thrice: " + thrice);
        System.out.println("checksum: " + (twice * thrice));
    }
}
