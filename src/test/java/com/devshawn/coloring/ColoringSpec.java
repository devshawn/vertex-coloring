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

}
