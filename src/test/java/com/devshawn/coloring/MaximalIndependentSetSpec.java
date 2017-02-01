package com.devshawn.coloring;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaximalIndependentSetSpec {

    @Test
    public void testMIS() {

        // a 5-vertex graph

        /*
         * (0) ----- (2)
         *  | \     / |
         *  |   (4)   |
         *  | /     \ |
         * (1) ----- (3)
         *
         */

        int[][] graph = {
                {0, 1, 1, 0, 1},
                {1, 0, 0, 1, 1},
                {1, 0, 0, 1, 1},
                {0, 1, 1, 0, 1},
                {1, 1, 1, 1, 0}
        };

        Coloring coloring = new Coloring();
        coloring.setGraph(graph);
        ColoringResult result = coloring.applyHeuristic(ColoringHeuristic.MAXIMAL_INDEPENDENT_SET);

        assertEquals(3, result.getColoringNumber());
        int[] expectedColoring = {0, 1, 1, 0, 2};
        assertArrayEquals(expectedColoring, result.getColoring());
    }

}
