package com.devshawn.coloring.library;

public class GraphGenerator {

    // simple graph with a given number of vertices
    // an edge between any two vertices with given probability
    // this is referred to as the Erdos-Renyi model
    public static int[][] simple(int vertices, double probability) {
        if(probability < 0 || probability > 1) {
            throw new ColoringException(ColoringException.INVALID_PROBABILITY);
        }

        int[][] graph = new int[vertices][vertices];

        for(int i = 0; i < vertices; i++) {
            for(int j = 0; j < i; j++) {
                int number = (Math.random() < probability) ? 1 : 0;
                graph[i][j] = number;
                graph[j][i] = number;
            }
        }

        return graph;
    }

    public static boolean isSymmetric(int[][] graph) {
        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < i; j++) {
                if(graph[i][j] != graph[j][i]) {
                    return false;
                }
            }
        }

        return true;
    }
}
