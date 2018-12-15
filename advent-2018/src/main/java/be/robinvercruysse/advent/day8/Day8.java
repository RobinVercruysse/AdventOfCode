package be.robinvercruysse.advent.day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day8 {
    private int index = 0;
    private String[] parts;
    private List<Node> allNodes = new ArrayList<>();

    public int getMetadataSum(final String input) throws IOException {
        parts = readInput(input);
        readNode();
        int sum = 0;
        for (Node node : allNodes) {
            sum += node.getMetadataSum();
        }

        return sum;
    }

    public int getRootNodeValue(final String input) throws IOException {
        parts = readInput(input);
        Node rootNode = readNode();

        return rootNode.getValue();
    }

    private Node readNode() {
        //read header
        int childCount = Integer.parseInt(parts[index++]);
        int metadataCount = Integer.parseInt(parts[index++]);

        Node node = new Node();

        for (int i = 0; i < childCount; i++) {
            Node child = readNode();
            node.getChildren().add(child);
        }

        for (int i = 0; i < metadataCount; i++) {
            node.getMetadata().add(Integer.parseInt(parts[index++]));
        }

        allNodes.add(node);

        return node;
    }

    private String[] readInput(final String input) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(input);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.readLine().split(" ");
    }

    private static class Node {
        private List<Node> children = new ArrayList<>();
        private List<Integer> metadata = new ArrayList<>();

        private List<Node> getChildren() {
            return children;
        }

        private List<Integer> getMetadata() {
            return metadata;
        }

        private int getMetadataSum() {
            int sum = 0;
            for (int meta : getMetadata()) {
                sum += meta;
            }
            return sum;
        }

        private int getValue() {
            if (getChildren().isEmpty()) {
                return getMetadataSum();
            } else {
                int value = 0;
                for (int meta : getMetadata()) {
                    int index = meta - 1;
                    if (index >= 0 && index < getChildren().size()) {
                        value += getChildren().get(index).getValue();
                    }
                }
                return value;
            }
        }
    }
}