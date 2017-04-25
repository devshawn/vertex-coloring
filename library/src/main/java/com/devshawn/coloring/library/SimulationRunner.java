package com.devshawn.coloring.library;

import java.io.*;

public class SimulationRunner {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        final int EDGE_PERCENTAGE_START = 10;
        final int EDGE_PERCENTAGE_END = 90;
        final int EDGE_PERCENTAGE_INCREMENT = 10;
        final int RUNS = 1000;
        final int[] vertices = {50, 100, 250, 500, 1000};


        File file = new File("full.csv");
        file.delete();
        PrintWriter pw = new PrintWriter(new FileWriter(file, true));

        if(file.length() == 0) {
            printHeaders(pw);
        }

        pw.flush();

        // warmup

        for(int run = 0; run < 1000; run++) {
            ColoringModule coloringModule = new ColoringModule();
            int[][] graph = GraphGenerator.simple(100, 90 / (double) 100);
            coloringModule.setGraph(graph);
            ColoringResult greedy = coloringModule.applyHeuristic(ColoringHeuristic.GREEDY);
            ColoringResult wp = coloringModule.applyHeuristic(ColoringHeuristic.WELSH_POWELL);
            ColoringResult dsatur = coloringModule.applyHeuristic(ColoringHeuristic.DSATUR);
        }

        // actual

        for(int vertex : vertices) {
            System.out.println("Starting vertex: " + vertex);

            for(int edgePercentage = EDGE_PERCENTAGE_START; edgePercentage <= EDGE_PERCENTAGE_END; edgePercentage += EDGE_PERCENTAGE_INCREMENT) {
                System.out.println("\tStarting edge percentage: " + edgePercentage);

                for(int run = 0; run < RUNS; run++) {
                    ColoringModule coloringModule = new ColoringModule();
                    int[][] graph = GraphGenerator.simple(vertex, edgePercentage / (double) 100);
                    coloringModule.setGraph(graph);
                    ColoringResult greedy = coloringModule.applyHeuristic(ColoringHeuristic.GREEDY);
                    ColoringResult wp = coloringModule.applyHeuristic(ColoringHeuristic.WELSH_POWELL);
                    ColoringResult dsatur = coloringModule.applyHeuristic(ColoringHeuristic.DSATUR);

                    printHeuristic(pw, edgePercentage, vertex, greedy);
                    printHeuristic(pw, edgePercentage, vertex, wp);
                    printHeuristic(pw, edgePercentage, vertex, dsatur);
                }
            }
        }

        pw.close();
    }

    public static void printHeuristic(PrintWriter pw, int edgePercentage, int vertices, ColoringResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append(edgePercentage);
        sb.append(',');
        sb.append(vertices);
        sb.append(',');
        sb.append(result.getHeuristic());
        sb.append(',');
        sb.append(result.getColoringNumber());
        sb.append(',');
        sb.append(result.getTime());
        sb.append('\n');
        pw.write(sb.toString());
        pw.flush();

    }

    public static void printHeaders(PrintWriter pw) {
        StringBuilder sb = new StringBuilder();
        sb.append("type");
        sb.append(',');
        sb.append("vertices");
        sb.append(',');
        sb.append("heuristic");
        sb.append(',');
        sb.append("colors");
        sb.append(',');
        sb.append("time");
        sb.append('\n');
        pw.write(sb.toString());
        pw.flush();
    }
}
