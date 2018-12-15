package be.robinvercruysse.advent.day3;

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

public class Day3 {
    public static void main(String[] args) throws IOException {
        InputStream input = Day3.class.getClassLoader().getResourceAsStream("input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String line;

        Set<Point> claimedPoints = new HashSet<Point>();
        Set<Point> duplicatePoints = new HashSet<Point>();
        Map<Point, List<Claim>> claimByPoint = new HashMap<Point, List<Claim>>();
        List<Claim> claims = new ArrayList<Claim>();

        while((line = reader.readLine()) != null) {
            int id = Integer.parseInt(line.split("@")[0].trim().substring(1));
            String coordsString = line.split("@")[1].split(":")[0].trim();
            String[] coordsParts = coordsString.split(",");
            String sizeString = line.split(":")[1].trim();
            String[] sizeParts = sizeString.split("x");
            int left = Integer.parseInt(coordsParts[0]);
            int top = Integer.parseInt(coordsParts[1]);
            int width = Integer.parseInt(sizeParts[0]);
            int height = Integer.parseInt(sizeParts[1]);
            Claim claim = new Claim(id, left, top, width, height);
            claims.add(claim);
            System.out.println("processing claim " + claim.getId());
            for (Point point : claim.getCoveredPoints()) {
                if (!claimByPoint.containsKey(point)) {
                    claimByPoint.put(point, new ArrayList<Claim>());
                }
                claimByPoint.get(point).add(claim);
            }
        }

        for (Claim claim : claims) {
            //System.out.println("checking claim " + claim.getId());
            boolean overlap = false;
            for (Point point : claim.getCoveredPoints()) {
                if (claimByPoint.get(point).size() > 1) {
                    overlap = true;
                }
            }
            if (!overlap) {
                System.out.println("magic claim: " + claim.getId());
            }
        }
    }
}
