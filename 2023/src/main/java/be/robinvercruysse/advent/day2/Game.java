package be.robinvercruysse.advent.day2;


import java.util.List;

public class Game {
    private final int id;
    private final int minRed;
    private final int minGreen;
    private final int minBlue;

    public Game(int id, List<Pull> pulls) {
        this.id = id;
        int minRed = 0;
        int minGreen = 0;
        int minBlue = 0;
        for (Pull pull : pulls) {
            if (minRed < pull.red()) {
                minRed = pull.red();
            }
            if (minGreen < pull.green()) {
                minGreen = pull.green();
            }
            if (minBlue < pull.blue()) {
                minBlue = pull.blue();
            }
        }
        this.minRed = minRed;
        this.minGreen = minGreen;
        this.minBlue = minBlue;
    }

    public int getId() {
        return id;
    }

    public int getMinRed() {
        return minRed;
    }

    public int getMinGreen() {
        return minGreen;
    }

    public int getMinBlue() {
        return minBlue;
    }
}
