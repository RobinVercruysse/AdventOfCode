package be.robinvercruysse.advent.day3;

import java.awt.*;

public class Claim {
    private int id;
    private int left;
    private int top;
    private int width;
    private int height;

    public Claim(final int id, final int left, final int top, final int width, final int height) {
        this.id = id;
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point[] getCoveredPoints() {
        Point[] coveredPoints = new Point[width * height];
        int index = 0;
        for (int x = left + 1; x < left + width + 1; x++) {
            for (int y = top + 1; y < top + height + 1; y++) {
                coveredPoints[index++] = new Point(x, y);
            }
        }
        return coveredPoints;
    }
}
