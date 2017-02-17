package com.devshawn.coloring.library;

import java.io.PrintWriter;

public class ColoringResult {
    private int[] result;
    private int highestColor;
    private ColoringHeuristic heuristicUsed;
    private int[] colors;
    private long time;

    public ColoringResult() {

    }

    public ColoringResult(int[] result, ColoringHeuristic heuristicUsed, long time) {

        // Find how many colors we used
        int highestColor = -1;

        for(int i = 0; i < result.length; i++) {
            if(result[i] > highestColor) {
                highestColor = result[i];
            }
        }

        // Add 1 as colors start with 0
        this.highestColor = highestColor + 1;

        // Count our color usage
        this.colors = new int[this.highestColor];
        for(int i = 0; i < result.length; i++) {
            colors[result[i]]++;
        }

        this.result = result;
        this.heuristicUsed = heuristicUsed;
        this.time = time;
    }

    public int getColoringNumber() {
        return highestColor;
    }

    public int[] getColoring() {
        return this.result;
    }

    public long getTime() { return this.time; }

    public ColoringHeuristic getHeuristic() { return this.heuristicUsed; }

    public String getSummary() {
        String string = "The graph was colored with the " + heuristicUsed.getName() + " heuristic.";

        string += "\r\n\tColor usage:";
        for(int i = 0; i < colors.length; i++) {
            string += "\r\n\tColor " + i + " => " + colors[i];
        }

        string += "\r\n\t-----------------------------------";

        string += "\r\n\tVertex to color mapping:";
        for(int i = 0; i < result.length; i++) {
            string += "\r\n\tVertex " + i + " => color " + result[i];
        }

        string += "\r\n\t-----------------------------------";

        string += "\r\n\tColors used: " + highestColor;

        return string;
    }

    public void printSummary(PrintWriter output) {
        output.println(getSummary());
        output.flush();
    }
}
