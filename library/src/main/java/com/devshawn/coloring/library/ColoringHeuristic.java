package com.devshawn.coloring.library;

public enum ColoringHeuristic {
    GREEDY ("greedy"),
    WELSH_POWELL ("welsh-powell"),
    MAXIMAL_INDEPENDENT_SET ("maximal independent set"),
    DSATUR ("DSATUR");

    private String name;

    ColoringHeuristic(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
