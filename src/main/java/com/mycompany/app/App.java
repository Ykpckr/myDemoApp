package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{

    public static double computeAverageDistance(List<Integer> points1, List<Integer> points2, int threshold1, int threshold2) {

        if (points1.size() != points2.size()) {
            throw new IllegalArgumentException("Arrays must have the same length");
        }

        int totalDistance = 0;
        int count = 0;
        for (int i = 0; i < points1.size(); i++) {
            if (points1.get(i) >= threshold1 && points2.get(i) >= threshold2) {
                totalDistance += Math.abs(points1.get(i) - points2.get(i));
                count++;
            }
        }

        if (count == 0) {
            // Handle the case where no elements meet the thresholds
            return 0.0;
        }

        return (double) totalDistance / count;
    }

    // Example usage
    public static void main(String[] args) {
        List<Integer> points1 = new ArrayList<Integer>();
        points1.add(1);
        points1.add(2);
        points1.add(3);
        points1.add(4);
        List<Integer> points2 = new ArrayList<Integer>();
        points2.add(4);
        points2.add(5);
        points2.add(6);
        points2.add(1);
        int threshold1 = 2;
        int threshold2 = 3;
        double averageDistance = computeAverageDistance(points1, points2, threshold1, threshold2);
        System.out.println("Average distance: " + averageDistance);
    }
}