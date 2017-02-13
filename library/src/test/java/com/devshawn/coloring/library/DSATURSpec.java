package com.devshawn.coloring.library;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DSATURSpec {
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
        ColoringResult result = coloring.applyHeuristic(ColoringHeuristic.DSATUR);

        System.out.println(Arrays.toString(result.getColoring()));
    }
}
