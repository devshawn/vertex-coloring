package com.devshawn.coloring.server.entity

class ResultSummary {

    String id
    String name
    int vertices
    int runs
    int start
    int end
    int increment
    List<SimulationResult> simulations
    List<ComparisonSummary> comparisonSummaries

}
