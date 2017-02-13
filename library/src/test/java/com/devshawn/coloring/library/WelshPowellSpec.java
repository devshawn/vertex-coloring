package com.devshawn.coloring.library;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WelshPowellSpec {

    @Test
    public void testWelshPowell() {

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
        ColoringResult result = coloring.applyHeuristic(ColoringHeuristic.WELSH_POWELL);

        assertEquals(3, result.getColoringNumber());
        int[] expectedColoring = {1, 2, 2, 1, 0};
        assertArrayEquals(expectedColoring, result.getColoring());
    }

    @Test
    public void producesDifferentThanGreedy() {

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
        ColoringResult greedyResult = coloring.applyHeuristic(ColoringHeuristic.GREEDY);
        ColoringResult welshPowellResult = coloring.applyHeuristic(ColoringHeuristic.WELSH_POWELL);
        for(int i = 0; i < greedyResult.getColoring().length; i++) {
            assertNotEquals(greedyResult.getColoring()[i], welshPowellResult.getColoring()[i]);
        }
    }
}
