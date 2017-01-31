package com.devshawn.coloring;

public class Main {

    // For testing without writing JUnit tests

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}
        };

        Coloring coloring = new Coloring();
        coloring.setGraph(graph);
        ColoringResult result = coloring.applyHeuristic(ColoringHeuristic.GREEDY);
        result.printSummary();
    }
}
