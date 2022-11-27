package be.robinvercruysse.advent.day10;

public class Point {
    int x;
    int y;
    int velX;
    int velY;

    public Point(final int initialX, final int initialY, final int velX, final int velY) {
        this.x = initialX;
        this.y = initialY;
        this.velX = velX;
        this.velY = velY;
    }

    public void advance() {
        x += velX;
        y += velY;
    }

    public void previous() {
        x -= velX;
        y -= velY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int distanceTo(Point point) {
        return (Math.abs(point.getX() - getX())) + (Math.abs(point.getY() - getY()));
    }
}
