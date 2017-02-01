package com.devshawn.coloring;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
                result = applyWelshPowell();
                break;
            case MAXIMAL_INDEPENDENT_SET:
                break;
        }

        return result;
    }

    // Implemented heuristics

    // Greedy heuristic
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

    // Greedy plus degree sequencing heuristic (Welsh-Powell algorithm)
    private ColoringResult applyWelshPowell() {

        // Make result array of vertices, assign no color to them
        int[] result = new int[graph.length];
        for(int i = 0; i < graph.length; i++) result[i] = -1;

        // Calculate degree of each vertex
        int[] degrees = new int[graph.length];
        Integer[] orderedVertices = new Integer[graph.length];

        for(int i = 0; i < graph.length; i++) {
            orderedVertices[i] = i;
            for(int j = 0; j < graph.length; j++) {
                if(graph[i][j] == 1) {
                    degrees[i]++;
                }
            }
        }

        // Sort our vertices by decreasing degree
        List<Integer> indexList = Arrays.asList(orderedVertices);
        Collections.sort(indexList, (left, right) -> (degrees[indexList.indexOf(right)] - degrees[indexList.indexOf(left)]));
        orderedVertices = indexList.toArray(new Integer[graph.length]);
        System.out.println(Arrays.toString(orderedVertices));

        // Make temporary available array to see what colors are available
        boolean available[] = new boolean[graph.length];
        for(int i = 0; i < graph.length; i++) available[i] = true;

        // Color the graph!
        for(int i = 0; i < graph.length; i++) {

            // Check neighbors for colors
            for(int j = 0; j < graph[orderedVertices[i]].length; j++) {
                if(graph[orderedVertices[i]][j] == 1 && result[j] != -1) {
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
            result[orderedVertices[i]] = color;

            // Reset our available colors
            for(int j = 0; j < graph.length; j++) available[j] = true;
        }


        return new ColoringResult(result, ColoringHeuristic.WELSH_POWELL);
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
