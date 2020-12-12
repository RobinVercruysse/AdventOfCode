package be.robinvercruysse.advent.day4;

import be.robinvercruysse.advent.day3.Day3A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4A {
    public static void main(String[] args) throws IOException {
        final InputStream inputStream = Day4A.class.getClassLoader().getResourceAsStream("day4.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final List<Passport> passports = new ArrayList<>();
        String line;
        Passport currentPassport = new Passport();
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                passports.add(currentPassport);
                currentPassport = new Passport();
            } else {
                currentPassport.addLine(line);
            }
        }
        passports.add(currentPassport);
        int valid = 0;
        for (Passport passport : passports) {
            System.out.printf("Passport has %d fields and is %b%n", passport.data.size(), passport.isValid());
            if (passport.isValid()) {
                valid++;
            }
        }
        System.out.printf("%d valid passports%n", valid);
    }

    private static class Passport {
        private Map<String, String> data = new HashMap<>();

        public void addLine(final String line) {
            for (final String field : line.split(" ")) {
                final String[] parts = field.split(":");
                data.put(parts[0], parts[1]);
            }
        }

        public boolean isValid() {
            return data.containsKey("byr") && data.containsKey("iyr") && data.containsKey("eyr") && data.containsKey("hgt") && data.containsKey("hcl") && data.containsKey("ecl") && data.containsKey("pid");
        }
    }
}
