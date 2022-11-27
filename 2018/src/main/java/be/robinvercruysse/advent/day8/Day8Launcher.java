package be.robinvercruysse.advent.day8;

import java.io.IOException;

public class Day8Launcher {
    public static void main(String[] args) {
        final String input = "be/robinvercruysse/advent/day8/input";
        try {
            System.out.println(new Day8().getMetadataSum(input));
            System.out.println(new Day8().getRootNodeValue(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
