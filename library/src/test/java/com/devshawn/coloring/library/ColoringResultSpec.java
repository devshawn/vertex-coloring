package com.devshawn.coloring.library;

import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ColoringResultSpec {

    @Test
    public void getSummary() {
        int[] results = {0};
        ColoringResult result = new ColoringResult(results, ColoringHeuristic.GREEDY, 0);

        String expectedResult = "The graph was colored with the greedy heuristic.";
        expectedResult += "\r\n\tColor usage:";
        expectedResult += "\r\n\tColor 0 => 1";
        expectedResult += "\r\n\t-----------------------------------";
        expectedResult += "\r\n\tVertex to color mapping:";
        expectedResult += "\r\n\tVertex 0 => color 0";
        expectedResult += "\r\n\t-----------------------------------";
        expectedResult += "\r\n\tColors used: 1";

        assertEquals(expectedResult, result.getSummary());
    }

    @Test
    public void printSummary() {
        int[] results = {0};
        ColoringResult result = new ColoringResult(results, ColoringHeuristic.GREEDY, 0);
        StringWriter writer = new StringWriter();
        result.printSummary(new PrintWriter(writer));
        assertThat(writer.toString(), containsString("Colors used: 1"));
    }

}

