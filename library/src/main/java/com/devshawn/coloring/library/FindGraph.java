package com.devshawn.coloring.library;

import java.util.Arrays;
import java.util.Random;

public class FindGraph {

    public static void main(String[] args) {
        ColoringResult greedy;
        ColoringResult wp;
        ColoringResult dsatur;
        int[][] graph;

        do {
            ColoringModule coloringModule = new ColoringModule();
            double prob = new Random().nextInt(11) / (double) 100;
            graph = GraphGenerator.simple(8, prob);
            coloringModule.setGraph(graph);
            greedy = coloringModule.applyHeuristic(ColoringHeuristic.GREEDY);
            wp = coloringModule.applyHeuristic(ColoringHeuristic.WELSH_POWELL);
            dsatur = coloringModule.applyHeuristic(ColoringHeuristic.DSATUR);
        } while (wp.getColoringNumber() == dsatur.getColoringNumber() || greedy.getColoringNumber() == wp.getColoringNumber() || greedy.getColoringNumber() == dsatur.getColoringNumber());

        System.out.println(greedy.getColoringNumber());
        System.out.println(wp.getColoringNumber());
        System.out.println(dsatur.getColoringNumber());
        System.out.println(Arrays.deepToString(graph));
    }

}
