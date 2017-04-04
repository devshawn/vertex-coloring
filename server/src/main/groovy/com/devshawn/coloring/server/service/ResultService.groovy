package com.devshawn.coloring.server.service

import com.devshawn.coloring.library.ColoringHeuristic
import com.devshawn.coloring.server.config.Constants
import com.devshawn.coloring.server.entity.Coloring
import com.devshawn.coloring.server.entity.Comparison
import com.devshawn.coloring.server.entity.ComparisonSummary
import com.devshawn.coloring.server.entity.Graph
import com.devshawn.coloring.server.entity.HeuristicResult
import com.devshawn.coloring.server.entity.Result
import com.devshawn.coloring.server.entity.ResultSummary
import com.devshawn.coloring.server.entity.Simulation
import com.devshawn.coloring.server.entity.SimulationResult
import com.devshawn.coloring.server.repository.ResultRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.math.RoundingMode

@Service
class ResultService {

    @Autowired
    ResultRepository resultRepository

    @Autowired
    GraphService graphService

    @Autowired
    SimulationService simulationService

    @Transactional
    Result save(Result result) {
        return resultRepository.save(result)
    }

    Result get(String id) {
        return resultRepository.findById(id)
    }

    List<Result> list() {
        return resultRepository.findAll()
    }

    Result create(Map<String, String> resultData) {
        List<Simulation> simulations = runSimulations(resultData)
        int runs = Integer.parseInt(resultData.get("runs"))
        int start = Integer.parseInt(resultData.get("start"))
        int end = Integer.parseInt(resultData.get("end"))
        int increment = Integer.parseInt(resultData.get("increment"))

        Map<Integer, List<Simulation>> percentages = new HashMap<>()
        for(int i = start; i <= end; i += increment) {
            percentages.put(i, new ArrayList<Simulation>())
        }

        List<SimulationResult> simulationResults = new ArrayList<>()

        for(Simulation simulation : simulations) {
            percentages.get(simulation.edgePercentage).add(simulation)
        }

        for(int i = start; i <= end; i += increment) {
            List<HeuristicResult> heuristicResults = new ArrayList<>()
            heuristicResults.add(new HeuristicResult(heuristic: ColoringHeuristic.GREEDY, time: 0, colors: 0, maximum: -1, minimum: Integer.MAX_VALUE))
            heuristicResults.add(new HeuristicResult(heuristic: ColoringHeuristic.WELSH_POWELL, time: 0, colors: 0, maximum: -1, minimum: Integer.MAX_VALUE))
            heuristicResults.add(new HeuristicResult(heuristic: ColoringHeuristic.MAXIMAL_INDEPENDENT_SET, time: 0, colors: 0, maximum: -1, minimum: Integer.MAX_VALUE))
            heuristicResults.add(new HeuristicResult(heuristic: ColoringHeuristic.DSATUR, time: 0, colors: 0, maximum: -1, minimum: Integer.MAX_VALUE))

            for(Simulation simulation : percentages.get(i)) {
                for(Coloring coloring : simulation.colorings) {
                    HeuristicResult heuristicResult = heuristicResults.find { it.heuristic == coloring.result.heuristic }
                    heuristicResult.time += coloring.result.time
                    heuristicResult.colors += coloring.result.coloringNumber

                    if(coloring.result.coloringNumber > heuristicResult.maximum) {
                        heuristicResult.maximum = coloring.result.coloringNumber
                    }

                    if(coloring.result.coloringNumber < heuristicResult.minimum) {
                        heuristicResult.minimum = coloring.result.coloringNumber
                    }
                }
            }

            for(HeuristicResult heuristicResult : heuristicResults) {
                heuristicResult.colors = round(heuristicResult.colors / runs, 2)
                heuristicResult.time /= runs
            }

            simulationResults.add(new SimulationResult(name: i + "% edges", heuristics: heuristicResults, smallName: i + "% edges"))

        }

        List<Simulation> sims = new ArrayList<>()
        for(Simulation simulation : simulations) {
            sims.add(new Simulation(id: simulation.id, name: simulation.name))
        }

        List<ComparisonSummary> comparisonSummaries = new ArrayList<>()
        for(SimulationResult simulationResult : simulationResults) {
            HeuristicResult greedy = simulationResult.heuristics.find { it.heuristic == ColoringHeuristic.GREEDY }
            HeuristicResult wp = simulationResult.heuristics.find { it.heuristic == ColoringHeuristic.WELSH_POWELL }
            HeuristicResult mis = simulationResult.heuristics.find { it.heuristic == ColoringHeuristic.MAXIMAL_INDEPENDENT_SET }
            HeuristicResult dsatur = simulationResult.heuristics.find { it.heuristic == ColoringHeuristic.DSATUR }

            double colors = greedy.colors - wp.colors
            double percentage = ((greedy.colors - wp.colors) / greedy.colors) * 100
            double percentDifference = (Math.abs(greedy.colors - wp.colors) / ((greedy.colors + wp.colors) / 2)) * 100
            long time = greedy.time - wp.time
            double timePercentage = ((greedy.time - wp.time) / greedy.time) * -100
            double timePercentDifference = ((Math.abs(greedy.time - wp.time) / ((greedy.time + wp.time) / 2))) * 100
            double minimumPercentDifference = ((Math.abs(greedy.minimum - wp.minimum) / ((greedy.minimum + wp.minimum) / 2))) * 100
            double maximumPercentDifference = ((Math.abs(greedy.maximum - wp.maximum) / ((greedy.maximum + wp.maximum) / 2))) * 100

            Comparison greedy_wp = new Comparison(colors: round(colors, 2), percentage: round(percentage, 2),
                    percentDifference: round(percentDifference, 2), time: time, timePercentage: round(timePercentage, 2),
                    timePercentDifference: round(timePercentDifference, 2), minimumPercentDifference: round(minimumPercentDifference, 2),
                    maximumPercentDifference: round(maximumPercentDifference, 2))

            colors = greedy.colors - mis.colors
            percentage = ((greedy.colors - mis.colors) / greedy.colors) * 100
            percentDifference = (Math.abs(greedy.colors - mis.colors) / ((greedy.colors + mis.colors) / 2)) * 100
            time = greedy.time - mis.time
            timePercentage = ((greedy.time - mis.time) / greedy.time) * -100
            timePercentDifference = ((Math.abs(greedy.time - mis.time) / ((greedy.time + mis.time) / 2))) * 100
            minimumPercentDifference = ((Math.abs(greedy.minimum - mis.minimum) / ((greedy.minimum + mis.minimum) / 2))) * 100
            maximumPercentDifference = ((Math.abs(greedy.maximum - mis.maximum) / ((greedy.maximum + mis.maximum) / 2))) * 100
            Comparison greedy_mis = new Comparison(colors: round(colors, 2), percentage: round(percentage, 2),
                    percentDifference: round(percentDifference, 2), time: time, timePercentage: round(timePercentage, 2),
                    timePercentDifference: round(timePercentDifference, 2), minimumPercentDifference: round(minimumPercentDifference, 2),
                    maximumPercentDifference: round(maximumPercentDifference, 2))

            colors = greedy.colors - dsatur.colors
            percentage = ((greedy.colors - dsatur.colors) / greedy.colors) * 100
            percentDifference = (Math.abs(greedy.colors - dsatur.colors) / ((greedy.colors + dsatur.colors) / 2)) * 100
            time = greedy.time - dsatur.time
            timePercentage = ((greedy.time - dsatur.time) / greedy.time) * -100
            timePercentDifference = ((Math.abs(greedy.time - dsatur.time) / ((greedy.time + dsatur.time) / 2))) * 100
            minimumPercentDifference = ((Math.abs(greedy.minimum - dsatur.minimum) / ((greedy.minimum + dsatur.minimum) / 2))) * 100
            maximumPercentDifference = ((Math.abs(greedy.maximum - dsatur.maximum) / ((greedy.maximum + dsatur.maximum) / 2))) * 100
            Comparison greedy_dsatur = new Comparison(colors: round(colors, 2), percentage: round(percentage, 2),
                    percentDifference: round(percentDifference, 2), time: time, timePercentage: round(timePercentage, 2),
                    timePercentDifference: round(timePercentDifference, 2), minimumPercentDifference: round(minimumPercentDifference, 2),
                    maximumPercentDifference: round(maximumPercentDifference, 2))

            colors = wp.colors - mis.colors
            percentage = ((wp.colors - mis.colors) / wp.colors) * 100
            percentDifference = (Math.abs(wp.colors - mis.colors) / ((wp.colors + mis.colors) / 2)) * 100
            time = wp.time - mis.time
            timePercentage = ((wp.time - mis.time) / wp.time) * -100
            timePercentDifference = ((Math.abs(wp.time - mis.time) / ((wp.time + mis.time) / 2))) * 100
            minimumPercentDifference = ((Math.abs(wp.minimum - mis.minimum) / ((wp.minimum + mis.minimum) / 2))) * 100
            maximumPercentDifference = ((Math.abs(wp.maximum - mis.maximum) / ((wp.maximum + mis.maximum) / 2))) * 100
            Comparison wp_mis = new Comparison(colors: round(colors, 2), percentage: round(percentage, 2),
                    percentDifference: round(percentDifference, 2), time: time, timePercentage: round(timePercentage, 2),
                    timePercentDifference: round(timePercentDifference, 2), minimumPercentDifference: round(minimumPercentDifference, 2),
                    maximumPercentDifference: round(maximumPercentDifference, 2))

            colors = wp.colors - dsatur.colors
            percentage = ((wp.colors - dsatur.colors) / wp.colors) * 100
            percentDifference = (Math.abs(wp.colors - dsatur.colors) / ((wp.colors + dsatur.colors) / 2)) * 100
            time = wp.time - dsatur.time
            timePercentage = ((wp.time - dsatur.time) / wp.time) * -100
            timePercentDifference = ((Math.abs(wp.time - dsatur.time) / ((wp.time + dsatur.time) / 2))) * 100
            minimumPercentDifference = ((Math.abs(wp.minimum - dsatur.minimum) / ((wp.minimum + dsatur.minimum) / 2))) * 100
            maximumPercentDifference = ((Math.abs(wp.maximum - dsatur.maximum) / ((wp.maximum + dsatur.maximum) / 2))) * 100
            Comparison wp_dsatur = new Comparison(colors: round(colors, 2), percentage: round(percentage, 2),
                    percentDifference: round(percentDifference, 2), time: time, timePercentage: round(timePercentage, 2),
                    timePercentDifference: round(timePercentDifference, 2), minimumPercentDifference: round(minimumPercentDifference, 2),
                    maximumPercentDifference: round(maximumPercentDifference, 2))

            colors = mis.colors - dsatur.colors
            percentage = ((mis.colors - dsatur.colors) / mis.colors) * 100
            percentDifference = (Math.abs(mis.colors - dsatur.colors) / ((mis.colors + dsatur.colors) / 2)) * 100
            time = mis.time - dsatur.time
            timePercentage = ((mis.time - dsatur.time) / mis.time) * -100
            timePercentDifference = ((Math.abs(mis.time - dsatur.time) / ((mis.time + dsatur.time) / 2))) * 100
            minimumPercentDifference = ((Math.abs(mis.minimum - dsatur.minimum) / ((mis.minimum + dsatur.minimum) / 2))) * 100
            maximumPercentDifference = ((Math.abs(mis.maximum - dsatur.maximum) / ((mis.maximum + dsatur.maximum) / 2))) * 100
            Comparison mis_dsatur = new Comparison(colors: round(colors, 2), percentage: round(percentage, 2),
                    percentDifference: round(percentDifference, 2), time: time, timePercentage: round(timePercentage, 2),
                    timePercentDifference: round(timePercentDifference, 2), minimumPercentDifference: round(minimumPercentDifference, 2),
                    maximumPercentDifference: round(maximumPercentDifference, 2))

            comparisonSummaries.add(new ComparisonSummary(name: simulationResult.name,
                    greedy_wp: greedy_wp,
                    greedy_mis: greedy_mis,
                    greedy_dsatur: greedy_dsatur,
                    wp_mis: wp_mis,
                    wp_dsatur: wp_dsatur,
                    mis_dsatur: mis_dsatur))
        }

        Result result = new Result(name: resultData.get("name"), simulations: simulationResults, comparisonSummaries: comparisonSummaries,
                vertices: Integer.parseInt(resultData.get("vertices")), runs: runs, start: start, end: end, increment: increment)

        return save(result)
    }

    void delete(String id) {
        resultRepository.delete(id)
    }

    List<Simulation> runSimulations(Map<String, String> resultData) {
        List<Simulation> simulations = new ArrayList<>()
        int start = Integer.parseInt(resultData.get("start"))
        int end = Integer.parseInt(resultData.get("end"))
        int increment = Integer.parseInt(resultData.get("increment"))

        Map<String, String> graphData = new HashMap<>()
        graphData.put("simulation", "true")
        graphData.put("edges", "1")
        graphData.put("vertices", "50")
        graphData.put("name", "Warmup")
        Graph warmupGraph = graphService.createForResult(graphData)

        for(int i = 0; i < Constants.WARMUP_RUNS; i++) {
            Map<String, String> simulationData = new HashMap<>()
            simulationData.put("name", "Warmup")
            simulationData.put("type", "complex")
            simulationData.put("edgePercentage", "50")
            Simulation simulation = simulationService.createForResult(simulationData, warmupGraph)
        }

        for(int i = start; i <= end; i += increment) {
            for(Integer j = 1; j <= Integer.parseInt(resultData.get("runs")); j++) {
                // Generate a graph
                graphData = new HashMap<>()
                graphData.put("simulation", "true")
                graphData.put("edges", i.toString())
                graphData.put("vertices", resultData.get("vertices"))
                graphData.put("name", "Generated by result " + resultData.get("name") + " (run #" + j + "): " + i + "% edges")
                Graph graph = graphService.createForResult(graphData)

                // Run simulation on graph
                Map<String, String> simulationData = new HashMap<>()
                simulationData.put("name", "Simulation for graph generated by result " + resultData.get("name") + " (run #" + j + "): " + i + "% edges")
                simulationData.put("type", "complex")
                simulationData.put("graphId", graph.id)
                simulationData.put("edgePercentage", i.toString())
                Simulation simulation = simulationService.createForResult(simulationData, graph)
                simulations.add(simulation)
            }
        }

        return simulations
    }

    static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException()

        BigDecimal bd = new BigDecimal(value)
        bd = bd.setScale(places, RoundingMode.HALF_UP)
        return bd.doubleValue()
    }
}
