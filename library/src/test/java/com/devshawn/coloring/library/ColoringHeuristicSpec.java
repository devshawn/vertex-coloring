package com.devshawn.coloring.library;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColoringHeuristicSpec {

    @Test
    public void getName() {
        ColoringHeuristic heuristic = ColoringHeuristic.GREEDY;
        assertEquals("greedy", heuristic.getName());
    }
}
