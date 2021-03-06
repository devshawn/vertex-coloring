package com.devshawn.coloring.library;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GreedySpec {

    @Test
    public void smallGreedyCompleteGraph() {

        // a complete 3-vertex graph
        int[][] graph = {
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}
        };

        ColoringModule coloringModule = new ColoringModule();
        coloringModule.setGraph(graph);
        ColoringResult result = coloringModule.applyHeuristic(ColoringHeuristic.GREEDY);

        assertEquals(3, result.getColoringNumber());
        int[] expectedColoring = {0, 1, 2};
        assertArrayEquals(expectedColoring, result.getColoring());
    }

    @Test
    public void smallGreedyGraph() {

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

        ColoringModule coloringModule = new ColoringModule();
        coloringModule.setGraph(graph);
        ColoringResult result = coloringModule.applyHeuristic(ColoringHeuristic.GREEDY);

        assertEquals(3, result.getColoringNumber());
        int[] expectedColoring = {0, 1, 1, 0, 2};
        assertArrayEquals(expectedColoring, result.getColoring());
    }

}
