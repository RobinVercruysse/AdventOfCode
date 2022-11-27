package be.robinvercruysse.advent.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

public class Day4B {
    public static void main(String[] args) throws IOException {
        final InputStream inputStream = Day4B.class.getClassLoader().getResourceAsStream("day4.txt");
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
        private final Map<String, String> data = new HashMap<>();

        public void addLine(final String line) {
            for (final String field : line.split(" ")) {
                final String[] parts = field.split(":");
                data.put(parts[0], parts[1]);
            }
        }

        public boolean isValid() {
            if (!data.containsKey("byr") ||
                    !data.containsKey("iyr") ||
                    !data.containsKey("eyr") ||
                    !data.containsKey("hgt") ||
                    !data.containsKey("hcl") ||
                    !data.containsKey("ecl") ||
                    !data.containsKey("pid")) {
                return false;
            }

            final int byr = Integer.parseInt(data.get("byr"));
            if (byr < 1920 || byr > 2002) {
                return false;
            }

            final int iyr = Integer.parseInt(data.get("iyr"));
            if (iyr < 2010 || iyr > 2020) {
                return false;
            }

            final int eyr = Integer.parseInt(data.get("eyr"));
            if (eyr < 2020 || eyr > 2030) {
                return false;
            }

            final String hgtString = data.get("hgt");
            if (hgtString.endsWith("cm")) {
                final int hgt = Integer.parseInt(hgtString.substring(0, hgtString.indexOf("cm")));
                if (hgt < 150 || hgt > 193) {
                    return false;
                }
            } else if (hgtString.endsWith("in")) {
                final int hgt = Integer.parseInt(hgtString.substring(0, hgtString.indexOf("in")));
                if (hgt < 59 || hgt > 76) {
                    return false;
                }
            } else {
                return false;
            }

            if (!Pattern.matches("#[0-9,a-f]{6}", data.get("hcl"))) {
                return false;
            }

            if (!Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(data.get("ecl"))) {
                return false;
            }

            if (!Pattern.matches("[0-9]{9}", data.get("pid"))) {
                return false;
            }

            return true;
        }
    }
}
