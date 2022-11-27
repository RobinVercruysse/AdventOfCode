package be.robinvercruysse.advent.day7;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day7A {
    public static void main(String[] args) {
        final InputStream inputStream = Day7A.class.getClassLoader().getResourceAsStream("day7.txt");
        if (inputStream == null) {
            System.err.println("File not found");
            return;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Map<String, Bag> bags = new HashMap<>();
        List<StringBag> stringBags = reader.lines().map(Day7A::parse).peek(stringBag -> {
            bags.put(stringBag.getName(), new Bag(stringBag.getName()));
            for (String child : stringBag.getChildren()) {
                bags.putIfAbsent(child, new Bag(child));
            }
        }).collect(Collectors.toList());
        stringBags.forEach(stringBag -> {
            for (String childName : stringBag.getChildren()) {
                bags.get(stringBag.getName()).addChild(bags.get(childName));
                bags.get(childName).addParent(bags.get(stringBag.getName()));
            }
        });

        final long count = parents(bags.get("shiny gold")).parallelStream().map(Bag::getName).distinct().count();
        System.out.printf("Parent count: %d%n", count);
    }

    private static List<Bag> parents(final Bag bag) {
        List<Bag> parents = new ArrayList<>();
        for (Bag parent : bag.getParents()) {
            parents.add(parent);
            parents.addAll(parents(parent));
        }
        return parents;
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
                stringBag.addChild(childName);
            }
        }
        return stringBag;
    }

    private static class StringBag {
        private final String name;
        private final List<String> children = new ArrayList<>();

        public StringBag(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void addChild(final String child) {
            children.add(child);
        }

        public List<String> getChildren() {
            return children;
        }
    }

    private static class Bag {
        private final String name;
        private final List<Bag> parents = new ArrayList<>();
        private final List<Bag> children = new ArrayList<>();

        public Bag(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void addParent(final Bag parent) {
            parents.add(parent);
        }

        public void addChild(final Bag child) {
            children.add(child);
        }

        public List<Bag> getParents() {
            return parents;
        }

        public List<Bag> getChildren() {
            return children;
        }
    }
}
