package com.devshawn.coloring;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ColoringSpec {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // Tests for setting up

    @Test
    public void validGraphSetup() {
        int[][] graph = {
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}
        };

        Coloring coloring = new Coloring();
        coloring.setGraph(graph);
        assertTrue(graph == coloring.getGraph());
        assertEquals(3, coloring.getVertexCount());
        assertEquals(3, coloring.getEdgeCount());

    }

    @Test
    public void invalidGraphSet() throws ColoringException {
        thrown.expect(ColoringException.class);
        thrown.expectMessage(ColoringException.INVALID_MATRIX);

        int[][] graph = {
                {1, 1, 0}
        };

        Coloring coloring = new Coloring();
        coloring.setGraph(graph);
    }

    @Test
    public void nonSimpleGraph() throws ColoringException {
        thrown.expect(ColoringException.class);
        thrown.expectMessage(ColoringException.SELF_LOOPS);

        int[][] graph = {
                {0, 1, 1},
                {1, 1, 1},
                {1, 1, 0}
        };

        Coloring coloring = new Coloring();
        coloring.setGraph(graph);
    }

    // Test greedy heuristic with some known greedy colorings

    @Test
    public void smallGreedyCompleteGraph() {

        // a complete 3-vertex graph
        int[][] graph = {
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}
        };

        Coloring coloring = new Coloring();
        coloring.setGraph(graph);
        ColoringResult result = coloring.applyHeuristic(ColoringHeuristic.GREEDY);

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

        Coloring coloring = new Coloring();
        coloring.setGraph(graph);
        ColoringResult result = coloring.applyHeuristic(ColoringHeuristic.GREEDY);

        assertEquals(3, result.getColoringNumber());
        int[] expectedColoring = {0, 1, 1, 0, 2};
        assertArrayEquals(expectedColoring, result.getColoring());
    }

}
