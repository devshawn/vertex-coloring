package com.devshawn.coloring.library;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class GraphGeneratorSpec {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void simple() {
        int[][] graph = GraphGenerator.simple(1000, 0.3);
        assertEquals(true, GraphGenerator.isSymmetric(graph));
    }

    @Test
    public void badProbability() throws ColoringException {
        thrown.expect(ColoringException.class);
        thrown.expectMessage(ColoringException.INVALID_PROBABILITY);

        int[][] graph = GraphGenerator.simple(4, 2);
    }

    @Test
    public void isNotSymmetric() {
        int[][] graph = {
                {1, 1},
                {0, 1}
        };

        assertEquals(false, GraphGenerator.isSymmetric(graph));
    }

    @Test
    public void isSymmetric() {
        int[][] graph = {
                {0, 1},
                {1, 0}
        };

        assertEquals(true, GraphGenerator.isSymmetric(graph));
    }
}
