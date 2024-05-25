package be.robinvercruysse.advent.day4;

import be.robinvercruysse.advent.DaySolver;
import be.robinvercruysse.advent.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class B implements DaySolver<Integer> {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println(Utils.solve(B.class, "day4.txt"));
    }

    @Override
    public Integer solve(Stream<String> inputLines) {
        final Card[] originalCards = inputLines.map(Card::fromString).toArray(Card[]::new);
        int cardCount = originalCards.length;
        for (int i = 0; i < originalCards.length; i++) {
            cardCount += countCards(originalCards, i);
        }
        return cardCount;
    }

    private static int countCards(final Card[] originalCards, final int currentCardIndex) {
        final Card currentCard = originalCards[currentCardIndex];
        int cardsWon = 0;
        for (int actualNr : currentCard.actualNrs()) {
            if (currentCard.winningNrs().contains(actualNr)) {
                cardsWon++;
            }
        }
        int count = cardsWon;
        for (int i = currentCardIndex + 1; i < originalCards.length && i < currentCardIndex + cardsWon + 1; i++) {
            count += countCards(originalCards, i);
        }
        return count;
    }
}
