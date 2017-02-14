package com.devshawn.coloring.library;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static int[][] matrixStringToGraph(String str) {
        Scanner sc = new Scanner(str);

        // Find number of vertices
        Matcher m = Pattern.compile("\r\n|\r|\n").matcher(str);
        int vertices = 1;
        while (m.find())
        {
            vertices++;
        }

        int[][] graph = new int[vertices][];

        for(int i = 0; i < vertices; i++) {
            String[] line = sc.nextLine().split("");
            int[] numbers = new int[vertices];
            for(int j = 0; j < line.length; j++) {
                numbers[j] = Integer.parseInt(line[j]);
            }
            graph[i] = numbers;
        }

        return graph;
    }

    public static int[][] generateAdjacencyMatrix(int vertices, List<Tuple> edges) {
        int[][] graph = new int[vertices][vertices];

        for(Tuple edge : edges) {
            graph[edge.to][edge.from] = 1;
            graph[edge.from][edge.to] = 1;
        }

        return graph;
    }

    public static String adjacencyMatrixToString(int[][] graph) {
        String str = "";
        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph.length; j++) {
                str += graph[i][j];
            }
            str += "\n";
        }
        return str;
    }
}

class Tuple {
    public final int from;
    public final int to;
    public Tuple(int from, int to) {
        this.from = from;
        this.to = to;
    }
}
