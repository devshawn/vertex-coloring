package com.devshawn.coloring;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GraphGeneratorSpec {

    @Test
    public void simple() {
        int[][] graph = GraphGenerator.simple(1000, 0.3);
        assertEquals(true, GraphGenerator.isSymmetric(graph));
    }
}
