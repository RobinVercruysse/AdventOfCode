package be.robinvercruysse.advent.day2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day2A {
    public static void main(String[] args) {
        final InputStream inputStream = Day2A.class.getClassLoader().getResourceAsStream("day2.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final long validCount = reader.lines().filter(Day2A::isValid).count();
        System.out.printf("%d valid passwords%n", validCount);
    }

    private static boolean isValid(final String line) {
        final String[] parts = line.split(":");
        final String[] policyParts = parts[0].split(" ");
        final String[] policyCountParts = policyParts[0].split("-");

        final int minCount = Integer.parseInt(policyCountParts[0]);
        final int maxCount = Integer.parseInt(policyCountParts[1]);
        final char policyLetter = policyParts[1].toCharArray()[0];
        final String password = parts[1];

        int count = 0;
        for (char c : password.toCharArray()) {
            if (c == policyLetter) {
                count++;
            }
        }
        return count >= minCount && count <= maxCount;
    }
}
