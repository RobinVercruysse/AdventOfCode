package be.robinvercruysse.advent.day6;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day6 {
    public static void main(String[] args) throws IOException {
        InputStream input = Day6.class.getClassLoader().getResourceAsStream("input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        Map<Point, List<Point>> closestPointMap = new HashMap<Point, List<Point>>();


        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = -1, maxY = -1;

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            Point point = new Point(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()));
            if (point.x < minX) {
                minX = point.x;
            }
            if (point.y < minY) {
                minY = point.y;
            }
            if (point.x > maxX) {
                maxX = point.x;
            }
            if (point.y > maxY) {
                maxY = point.y;
            }
            closestPointMap.put(point, new ArrayList<Point>());
        }

        List<Point> safeRegion = new ArrayList<Point>();

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <=  maxY; y++) {
                int closest = Integer.MAX_VALUE;
                Point closestPoint = null;
                boolean dupe = false;
                int areaSum = 0;
                for (Point point : closestPointMap.keySet()) {
                    int distance = getDistance(point, x, y);
                    areaSum += distance;
                    if (distance < closest) {
                        closest = distance;
                        closestPoint = point;
                        dupe = false;
                    } else if (distance == closest) {
                        dupe = true;
                    }
                }
                if (areaSum < 10000) {
                    safeRegion.add(new Point(x, y));
                }
                if (!dupe) {
                    closestPointMap.get(closestPoint).add(new Point(x, y));
                }
            }
        }

        System.out.println("safe region: " + safeRegion.size());

        Set<Point> points = new HashSet<Point>(closestPointMap.keySet());

        int maxArea = -1;
        Point maxAreaPoint = null;

        for (Point point : points) {
            List<Point> pointArea = closestPointMap.get(point);
            boolean edge = false;
            for (Point p : pointArea) {
                if (p.x == minX || p.x == maxX || p.y == minY || p.y == maxY) {
                    edge = true;
                    break;
                }
            }
            if (!edge && pointArea.size() > maxArea) {
                maxArea = pointArea.size();
                maxAreaPoint = point;
            }
        }

        if (maxAreaPoint != null) {
            System.out.println("max area: " + maxAreaPoint.toString() + " - " + maxArea);
        }
    }

    private static int getDistance(Point point, int x, int y) {
        return Math.abs(point.x - x) + Math.abs(point.y - y);
    }
}
