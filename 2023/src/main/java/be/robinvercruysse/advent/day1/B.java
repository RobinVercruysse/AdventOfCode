package be.robinvercruysse.advent.day1;

import be.robinvercruysse.advent.DaySolver;
import be.robinvercruysse.advent.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class B implements DaySolver<Integer> {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println(Utils.solve(B.class, "day1.txt"));
    }

    @Override
    public Integer solve(Stream<String> inputLines) {
        return inputLines.map(B::lineToPair).mapToInt(Pair::toInt).sum();
    }

    private static Pair lineToPair(String line) {
        Pattern pattern = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine|[1-9])");
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
            new Exception("Something went wrong in pattern matching").printStackTrace();
            return new Pair(Character.MIN_VALUE, Character.MIN_VALUE);
        }
        String value = matcher.group();
        Pattern reversePattern = Pattern.compile("(eno|owt|eerht|ruof|evif|xis|neves|thgie|enin|[1-9])");
        Matcher reverseMatcher = reversePattern.matcher(new StringBuilder(line).reverse().toString());
        if (!reverseMatcher.find()) {
            new Exception("Something went wrong in pattern matching").printStackTrace();
            return new Pair(Character.MIN_VALUE, Character.MIN_VALUE);
        }
        String reverseValue = reverseMatcher.group();
        try {
            char first = B.lettersToDigit(value);
            char last = B.lettersToDigit(new StringBuilder(reverseValue).reverse().toString());
            return new Pair(first, last);
        } catch (Exception e) {
            e.printStackTrace();
            return new Pair(Character.MIN_VALUE, Character.MIN_VALUE);
        }
    }

    private static char lettersToDigit(String letters) throws Exception {
        if (letters.length() == 1) {
            return letters.toCharArray()[0];
        } else {
            return switch (letters) {
                case "one" -> '0' + 1;
                case "two" -> '0' + 2;
                case "three" -> '0' + 3;
                case "four" -> '0' + 4;
                case "five" -> '0' + 5;
                case "six" -> '0' + 6;
                case "seven" -> '0' + 7;
                case "eight" -> '0' + 8;
                case "nine" -> '0' + 9;
                default -> throw new Exception("wtf is " + letters);
            };
        }
    }
}
