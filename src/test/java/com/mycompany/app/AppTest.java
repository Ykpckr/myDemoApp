package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Test if the computeAverageDistance method returns correct result.
     */
    public void testComputeAverageDistance() {
        List<Integer> points1 = List.of(1, 2, 3, 4);
        List<Integer> points2 = List.of(4, 5, 6, 1);
        int threshold1 = 2;
        int threshold2 = 3;

        double expected = 3.0;
        double result = App.computeAverageDistance(points1, points2, threshold1, threshold2);

        assertEquals(expected, result);
    }

    /**
     * Test IllegalArgumentException for unequal length lists.
     */
    public void testUnequalLengthLists() {
        List<Integer> points1 = List.of(1, 2, 3);
        List<Integer> points2 = List.of(4, 5, 6, 1);
        int threshold1 = 2;
        int threshold2 = 3;

        try {
            App.computeAverageDistance(points1, points2, threshold1, threshold2);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Arrays must have the same length", e.getMessage());
        }
    }

    /**
     * Test when no elements meet the thresholds.
     */
    public void testNoElementsMeetThresholds() {
        List<Integer> points1 = List.of(1, 1, 1, 1);
        List<Integer> points2 = List.of(1, 1, 1, 1);
        int threshold1 = 10;
        int threshold2 = 10;

        double expected = 0.0;
        double result = App.computeAverageDistance(points1, points2, threshold1, threshold2);

        assertEquals(expected, result);
    }
}
