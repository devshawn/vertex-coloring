package com.devshawn.coloring.library;

import java.util.*;

public class ColoringModule {

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
                result = applyMaximalIndependentSet();
                break;
            case DSATUR:
                result = applyDSATUR();
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
        Collections.sort(indexList, (left, right) -> (degrees[indexList.indexOf(left)] - degrees[indexList.indexOf(right)]));
        Collections.reverse(indexList);
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

    private ColoringResult applyMaximalIndependentSet() {

        // Make result array of vertices, assign no color to them
        int[] result = new int[graph.length];
        for(int i = 0; i < graph.length; i++) result[i] = -1;

        // Copy our graph so we can manipulate it
        int[][] newGraph = new int[graph.length][graph.length];

        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j <graph.length; j++) {
                newGraph[i][j] = graph[i][j];
            }
        }

        Set<Integer> vertices = new HashSet<>();
        for(int i = 0; i < graph.length; i++) {
            vertices.add(i);
        }

        // Color using MIS heuristic
        int color = 0;
        while(!vertices.isEmpty()) {
            Set<Integer> tempVertices = new HashSet<>(vertices);
            Set<Integer> set = new HashSet<>();

            // Find a maximal independent set
            while(!tempVertices.isEmpty()) {
                Integer i = tempVertices.iterator().next();
                set.add(i);
                vertices.remove(i);
                tempVertices.remove(i);

                for(int k = 0; k < graph.length; k++) {
                    if(graph[i][k] == 1) {
                        tempVertices.remove(k);

                    }
                }
            }

            // Color the MIS with the next color
            for(int i : set) {
                result[i] = color;
            }

            color++;
        }

        return new ColoringResult(result, ColoringHeuristic.MAXIMAL_INDEPENDENT_SET);
    }

    private ColoringResult applyDSATUR() {

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
        Collections.sort(indexList, (left, right) -> (degrees[indexList.indexOf(left)] - degrees[indexList.indexOf(right)]));
        Collections.reverse(indexList);
        orderedVertices = indexList.toArray(new Integer[graph.length]);
        System.out.println(Arrays.toString(orderedVertices));

        Set<Integer> vertices = new HashSet<>();
        for(int i = 0; i < graph.length; i++) {
            vertices.add(orderedVertices[i]);
        }

        Set<Integer>[] saturated = new Set[graph.length];
        for(int i = 0; i < graph.length; i++) { saturated[i] = new HashSet<>(); }

        int selectedVertex = orderedVertices[0];
        while(!vertices.isEmpty()) {
            int minimumColor = getMinimumColorFromSaturated(saturated, selectedVertex);
            result[selectedVertex] = minimumColor;
            vertices.remove(selectedVertex);

            for(int i = 0; i < graph.length; i++) {
                if(graph[selectedVertex][i] == 1) {
                    saturated[i].add(minimumColor);
                }
            }
            selectedVertex = getMaximumVertexFromSaturated(saturated, vertices);
        }

        return new ColoringResult(result, ColoringHeuristic.DSATUR);
    }

    private int getMaximumVertexFromSaturated(Set<Integer>[] saturated, Set<Integer> vertices) {
        int max = -1;
        int maxSize = -1;
        for(int i = 0; i < graph.length; i++) {
            if(maxSize < saturated[i].size() && vertices.contains(i)) {
                max = i;
                maxSize = saturated[i].size();
            }
        }

        return max;
    }

    private int getMinimumColorFromSaturated(Set<Integer>[] saturated, int vertex) {
        int color = -1;
        for(int i = 0; i < graph.length; i++) {
            if(!saturated[vertex].contains(i)) {
                color = i;
                break;
            }
        }

        return color;
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
