package be.robinvercruysse.advent.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day10 {
    public static void main(String[] args) throws IOException {
        InputStream input = Day10.class.getClassLoader().getResourceAsStream("input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        List<Point> points = new ArrayList<Point>();

        String line;

        while((line = reader.readLine()) != null) {
            int x = Integer.parseInt(line.substring(10, 16).trim());
            int y = Integer.parseInt(line.substring(18, 24).trim());
            int velX = Integer.parseInt(line.substring(36, 38).trim());
            int velY = Integer.parseInt(line.substring(40, 42).trim());
            points.add(new Point(x, y, velX, velY));
        }


        long previous = Long.MAX_VALUE;
        long current = calculateRectangle(points);
        System.out.println(current);

        int time = 0;

        while (current < previous) {
            time++;
            previous = current;
            for (Point point : points) {
                point.advance();
            }
            current = calculateRectangle(points);
            System.out.println(current);
        }

        for (Point point : points) {
            point.previous();
        }
        time--;

        System.out.println(calculateRectangle(points));
        System.out.println("time: " + time);


        visualize(points);
    }

    private static long calculateRectangle(List<Point> points) {
        long minX = Long.MAX_VALUE;
        long minY = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long maxY = Long.MIN_VALUE;

        for (Point p : points) {
            if (p.getX() < minX) {
                minX = p.getX();
            }
            if (p.getX() > maxX) {
                maxX = p.getX();
            }
            if (p.getY() < minY) {
                minY = p.getY();
            }
            if (p.getY() > maxY) {
                maxY = p.getY();
            }
        }

        return (maxX - minX) * (maxY - minY);
    }

    private static void visualize(List<Point> points) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        List<String> dots = new ArrayList<String>(points.size());

        for (Point p : points) {
            if (p.getX() < minX) {
                minX = p.getX();
            }
            if (p.getX() > maxX) {
                maxX = p.getX();
            }
            if (p.getY() < minY) {
                minY = p.getY();
            }
            if (p.getY() > maxY) {
                maxY = p.getY();
            }
            dots.add(String.valueOf(p.getX()) + "," + String.valueOf(p.getY()));
        }

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                String dot = String.valueOf(x) + "," + String.valueOf(y);
                if (dots.contains(dot)) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
