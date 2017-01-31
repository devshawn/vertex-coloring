package com.devshawn.coloring;

public class ColoringResult {
    private int[] result;
    private int highestColor;
    private ColoringHeuristic heuristicUsed;
    private int[] colors;

    public ColoringResult(int[] result, ColoringHeuristic heuristicUsed) {

        // Find how many colors we used
        for(int i = 0; i < result.length; i++) {
            int highestColor = -1;

            if(result[i] > highestColor) {
                highestColor = result[i];
            }

            // Add 1 as colors start with 0
            this.highestColor = highestColor + 1;
        }

        // Count our color usage
        this.colors = new int[highestColor];
        for(int i = 0; i < result.length; i++) {
            colors[result[i]]++;
        }

        this.result = result;
        this.heuristicUsed = heuristicUsed;
    }

    public int getColoringNumber() {
        return highestColor;
    }

    public int[] getColoring() {
        return this.result;
    }

    public String getSummary() {
        String string = "The graph was colored with the " + heuristicUsed.getName() + " heuristic.";
        string += "\r\n\tColors used: " + highestColor;

        string += "\r\n\t-----------------------------------";

        string += "\r\n\tColor usage:";
        for(int i = 0; i < colors.length; i++) {
            string += "\r\n\tColor " + i + " => " + colors[i];
        }

        string += "\r\n\t-----------------------------------";

        string += "\r\n\tVertex to color mapping:";
        for(int i = 0; i < result.length; i++) {
            string += "\r\n\tVertex " + i + " => color " + result[i];
        }

        return string;
    }

    public void printSummary() {
        System.out.println(getSummary());
    }
}
