package com.devshawn.coloring.library;

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

        ColoringModule coloringModule = new ColoringModule();
        coloringModule.setGraph(graph);
        assertTrue(graph == coloringModule.getGraph());
        assertEquals(3, coloringModule.getVertexCount());
        assertEquals(3, coloringModule.getEdgeCount());

    }

    @Test
    public void invalidGraphSet() throws ColoringException {
        thrown.expect(ColoringException.class);
        thrown.expectMessage(ColoringException.INVALID_MATRIX);

        int[][] graph = {
                {1, 1, 0}
        };

        ColoringModule coloringModule = new ColoringModule();
        coloringModule.setGraph(graph);
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

        ColoringModule coloringModule = new ColoringModule();
        coloringModule.setGraph(graph);
    }

}
