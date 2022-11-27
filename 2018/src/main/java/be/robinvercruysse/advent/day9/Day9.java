package be.robinvercruysse.advent.day9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day9 {
    public static void main(String[] args) {
        final int lastMarble = 71032 * 100;
        final int players = 441;
        /*final int lastMarble = 1618;
        final int players = 10;*/

        final long start = System.currentTimeMillis();

        List<Integer> marbles = new ArrayList<Integer>();
        Map<Integer, Long> playerScores = new HashMap<Integer, Long>();
        int currentPlayer = 1;
        int currentMarble = 0;
        marbles.add(0);
        for (int i = 1; i <= players; i++) {
            playerScores.put(i, 0L);
        }

        long timesince = System.currentTimeMillis();
        for (int marble = 1; marble <= lastMarble; marble++) {
            if (marble % 23 != 0) {
                currentMarble += 2;
                if (currentMarble > marbles.size()) {
                    currentMarble = 1;
                }
                marbles.add(currentMarble, marble);
            } else {
                //score points
                int score = marble;
                int takeMarble = currentMarble - 7;
                while (takeMarble < 0) {
                    takeMarble = marbles.size() + takeMarble;
                }
                score += marbles.get(takeMarble);
                marbles.remove(takeMarble);
                currentMarble = takeMarble;
                playerScores.put(currentPlayer, playerScores.get(currentPlayer) + score);
            }
            if (currentPlayer >= players) {
                currentPlayer = 1;
            } else {
                currentPlayer++;
            }
            if (marble % 100000 == 0) {
                System.out.println(String.format("marble %s after another %s ms", marble, (System.currentTimeMillis() - timesince)));
                timesince = System.currentTimeMillis();
            }
        }

        long highScore = 0;
        for (Map.Entry<Integer, Long> playerScore : playerScores.entrySet()) {
            if (playerScore.getValue() > highScore) {
                highScore = playerScore.getValue();
            }
        }
        System.out.println("high score: " + highScore);
        final long end = System.currentTimeMillis();

        System.out.println("took " + (end - start) + "ms");
    }
}
