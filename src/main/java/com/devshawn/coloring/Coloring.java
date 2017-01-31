package com.devshawn.coloring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Coloring {

    private int[][] graph;
    private int vertexCount;
    private int edgeCount;

    // Apply a given heuristic to the graph
    public ColoringResult applyHeuristic(ColoringHeuristic heuristic) {
        ColoringResult result = new ColoringResult();

        switch(heuristic) {
            case GREEDY:
                result = applyGreedyHeuristic();
                break;
            case WELSH_POWELL:
                break;
            case MAXIMAL_INDEPENDENT_SET:
                break;
        }

        return result;
    }

    // Implemented heuristics
    private ColoringResult applyGreedyHeuristic() {

        // Make result array of vertices, assign no color to them
        int[] result = new int[graph.length];
        for(int i = 0; i < graph.length; i++) result[i] = -1;

        // Make temporary available array to see what colors are available
        boolean available[] = new boolean[graph.length];
        for(int i = 0; i < graph.length; i++) available[i] = true;

        // Color the graph!
        for(int i = 0; i < graph.length; i++) {

            // Check neighbors for colors
            for(int j = 0; j < graph[i].length; j++) {
                if(graph[i][j] == 1 && result[j] != -1) {
                    available[result[j]] = false;
                }
            }

            // Find smallest available color
            int color = -1;
            for(int j = 0; j < available.length; j++) {
                if(available[j]) {
                    color = j;
                    break;
                }
            }

            // Set this vertex to that color
            result[i] = color;

            // Reset our available colors
            for(int j = 0; j < graph.length; j++) available[j] = true;
        }

        // Generate a ColorResult
        return new ColoringResult(result, ColoringHeuristic.GREEDY);
    }

    // Simple functions to get information about the graph

    public int[][] getGraph() {
        return graph;
    }

    public void setGraph(int[][] graph) {
        if(graph.length != 0 && graph.length != graph[0].length) {
            throw new ColoringException(ColoringException.INVALID_MATRIX);
        }

        for(int i = 0; i < graph.length; i++) {
            if(graph[i][i] != 0) {
                throw new ColoringException(ColoringException.SELF_LOOPS);
            }
        }

        int edgeCount = 0;

        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph[i].length; j++) {
                edgeCount += graph[i][j];
            }
        }

        setVertexCount(graph.length);
        setEdgeCount(edgeCount / 2);
        this.graph = graph;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void setVertexCount(int vertexCount) {
        this.vertexCount = vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void setEdgeCount(int edgeCount) {
        this.edgeCount = edgeCount;
    }

}
