package com.devshawn.coloring;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ColoringResultSpec {

    @Test
    public void getSummary() {
        int[] results = {0};
        ColoringResult result = new ColoringResult(results, ColoringHeuristic.GREEDY);

        String expectedResult = "The graph was colored with the greedy heuristic.";
        expectedResult += "\r\n\tColors used: 1";
        expectedResult += "\r\n\t-----------------------------------";
        expectedResult += "\r\n\tColor usage:";
        expectedResult += "\r\n\tColor 0 => 1";
        expectedResult += "\r\n\t-----------------------------------";
        expectedResult += "\r\n\tVertex to color mapping:";
        expectedResult += "\r\n\tVertex 0 => color 0";

        assertEquals(expectedResult, result.getSummary());
    }

    @Test
    public void printSummary() {
        // test for coverage on this function
        int[] results = {0};
        ColoringResult result = new ColoringResult(results, ColoringHeuristic.GREEDY);

        result.printSummary();
        assertTrue(true);
    }


}

