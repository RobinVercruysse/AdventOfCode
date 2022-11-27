package be.robinvercruysse.advent.day7;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Day7B {
    public static void main(String[] args) {
        final InputStream inputStream = Day7B.class.getClassLoader().getResourceAsStream("day7.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Map<String, Bag> bags = new HashMap<>();
        List<StringBag> stringBags = reader.lines().map(Day7B::parse).peek(stringBag -> {
            bags.put(stringBag.getName(), new Bag(stringBag.getName()));
            for (Map.Entry<Integer, String> child : stringBag.getChildren()) {
                bags.putIfAbsent(child.getValue(), new Bag(child.getValue()));
            }
        }).collect(Collectors.toList());
        stringBags.forEach(stringBag -> {
            for (Map.Entry<Integer, String> child : stringBag.getChildren()) {
                bags.get(stringBag.getName()).addChild(child.getKey(), bags.get(child.getValue()));
                bags.get(child.getValue()).addParent(bags.get(stringBag.getName()));
            }
        });

        final int count = children(bags.get("shiny gold"));
        System.out.printf("Children count: %d%n", count);
    }

    private static int children(final Bag bag) {
        int count = 0;
        for (Map.Entry<Integer, Bag> child : bag.getChildren()) {
            final int multiplier = child.getKey();
            final int grandChildren = children(child.getValue());
            count += multiplier + multiplier * grandChildren;
        }
        return count;
    }

    private static StringBag parse(final String line) {
        final String[] lineParts = line.split(" bags contain ");
        final StringBag stringBag = new StringBag(lineParts[0]);
        if (!lineParts[1].equals("no other bags.")) {
            final String[] children = lineParts[1].split(",");
            for (String c : children) {
                final String child = c.split(" bag")[0].trim();
                final String count = child.split(" ")[0];
                final String childName = child.substring(count.length() + 1);
                stringBag.addChild(Integer.parseInt(count), childName);
            }
        }
        return stringBag;
    }

    private static class StringBag {
        private final String name;
        private final List<Map.Entry<Integer, String>> children = new ArrayList<>();

        public StringBag(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void addChild(final int count, final String child) {
            children.add(new AbstractMap.SimpleEntry<>(count, child));
        }

        public List<Map.Entry<Integer, String>> getChildren() {
            return children;
        }
    }

    private static class Bag {
        private final String name;
        private final List<Bag> parents = new ArrayList<>();
        private final List<Map.Entry<Integer, Bag>> children = new ArrayList<>();

        public Bag(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void addParent(final Bag parent) {
            parents.add(parent);
        }

        public void addChild(final int count, final Bag child) {
            children.add(new AbstractMap.SimpleEntry<>(count, child));
        }

        public List<Bag> getParents() {
            return parents;
        }

        public List<Map.Entry<Integer, Bag>> getChildren() {
            return children;
        }
    }
}
