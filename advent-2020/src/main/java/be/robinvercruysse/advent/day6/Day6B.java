package be.robinvercruysse.advent.day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6B {
    public static void main(String[] args) throws IOException {
        final InputStream inputStream = Day6B.class.getClassLoader().getResourceAsStream("day6.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final List<Group> groups = new ArrayList<>();
        String line;
        Group currentGroup = new Group();
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                groups.add(currentGroup);
                currentGroup = new Group();
            } else {
                currentGroup.addLine(line);
            }
        }
        groups.add(currentGroup);
        System.out.printf("Count sum: %d%n", groups.stream().mapToInt(Group::getYesCount).sum());
    }

    private static class Group {
        private Set<Character> characters = new HashSet<>();
        private int people = 0;

        public void addLine(final String line) {
            final Set<Character> buffer = new HashSet<>();
            for (char c : line.toCharArray()) {
                if (people == 0 || characters.contains(c)) {
                    buffer.add(c);
                }
            }
            people++;
            characters = buffer;
        }

        public int getYesCount() {
            return characters.size();
        }
    }
}
