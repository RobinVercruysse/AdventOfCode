package be.robinvercruysse.advent.day4;

import java.util.HashSet;
import java.util.Set;

public record Card(int id, Set<Integer> winningNrs, Set<Integer> actualNrs) {
    public static Card fromString(String cardString) {
        final String[] cardParts = cardString.split(":");
        final String[] gameIdParts = cardParts[0].split(" ");
        final int id = Integer.parseInt(gameIdParts[gameIdParts.length - 1]);
        final String[] numberParts = cardParts[1].split("\\|");
        final Set<Integer> winningNrs = new HashSet<>();
        final Set<Integer> actualNrs = new HashSet<>();
        for (String winningNr : numberParts[0].trim().split(" ")) {
            if (winningNr.isEmpty()) {
                continue;
            }
            winningNrs.add(Integer.parseInt(winningNr));
        }
        for (String actualNr : numberParts[1].trim().split(" ")) {
            if (actualNr.isEmpty()) {
                continue;
            }
            actualNrs.add(Integer.parseInt(actualNr));
        }
        return new Card(id, winningNrs, actualNrs);
    }
}
