package be.robinvercruysse.advent.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day5 {
    private static final char[] alphabet = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) throws IOException {
        InputStream input = Day5.class.getClassLoader().getResourceAsStream("input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        char[] chars = reader.readLine().toCharArray();

        int shortest = Integer.MAX_VALUE;
        char problem = (char)-1;

        for (char letter : alphabet) {
            List<Character> polymer = new ArrayList<Character>(chars.length);

            for (char character : chars) {
                if (Character.toLowerCase(character) != letter) {
                    polymer.add(character);
                }
            }

            int length = getReactedPolymerLength(polymer);
            System.out.println("polymer for " + letter + ": " + length);

            if (length < shortest) {
                shortest = length;
                problem = letter;
            }
        }

        System.out.println("shortest is " + problem);

    }

    private static int getReactedPolymerLength(List<Character> polymer) {
        int index = 0;
        while (index + 1 < polymer.size()) {
            char a = polymer.get(index);
            char b = polymer.get(index + 1);
            if (a != b && Character.toLowerCase(a) == Character.toLowerCase(b)) {
                polymer.remove(index);
                polymer.remove(index);
                if (index > 0) {
                    index--;
                }
            } else {
                index++;
            }
        }

        return polymer.size();
    }
}
