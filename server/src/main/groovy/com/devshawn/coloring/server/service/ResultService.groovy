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

    ResultSummary getSummary(String id) {
        List<SimulationResult> simulationResults = new ArrayList<>()

        Result result = resultRepository.findById(id)
//        for(Simulation simulation : result.simulations) {
//            simulation.graph = null
//            List<HeuristicResult> heuristicResults = new ArrayList<>()
//            for(Coloring coloring : simulation.colorings) {
//                heuristicResults.add(new HeuristicResult(heuristic: coloring.result.heuristic, time: coloring.result.time, colors: coloring.result.coloringNumber))
//            }
//            simulationResults.add(new SimulationResult(name: simulation.name, id: simulation.id, heuristics: heuristicResults, smallName: simulation.name.substring(simulation.name.lastIndexOf(':') + 2)))
//        }

        Map<Integer, List<Simulation>> percentages = new HashMap<>()
        for(int i = Constants.EDGE_PERCENTAGE_START; i <= Constants.EDGE_PERCENTAGE_END; i += Constants.EDGE_PERCENTAGE_INCREMENT) {
            percentages.put(i, new ArrayList<Simulation>())
        }

        for(Simulation simulation : result.simulations) {
            percentages.get(simulation.edgePercentage).add(simulation)
        }

        for(int i = Constants.EDGE_PERCENTAGE_START; i <= Constants.EDGE_PERCENTAGE_END; i += Constants.EDGE_PERCENTAGE_INCREMENT) {
            List<HeuristicResult> heuristicResults = new ArrayList<>()
            heuristicResults.add(new HeuristicResult(heuristic: ColoringHeuristic.GREEDY, time: 0, colors: 0))
            heuristicResults.add(new HeuristicResult(heuristic: ColoringHeuristic.WELSH_POWELL, time: 0, colors: 0))
            heuristicResults.add(new HeuristicResult(heuristic: ColoringHeuristic.MAXIMAL_INDEPENDENT_SET, time: 0, colors: 0))
            heuristicResults.add(new HeuristicResult(heuristic: ColoringHeuristic.DSATUR, time: 0, colors: 0))

            for(Simulation simulation : percentages.get(i)) {
                for(Coloring coloring : simulation.colorings) {
                    HeuristicResult heuristicResult = heuristicResults.find { it.heuristic == coloring.result.heuristic }
                    heuristicResult.time += coloring.result.time
                    heuristicResult.colors += coloring.result.coloringNumber
                }
            }

            for(HeuristicResult heuristicResult : heuristicResults) {
                heuristicResult.colors = round(heuristicResult.colors / result.runs, 2)
                heuristicResult.time /= result.runs
            }

            simulationResults.add(new SimulationResult(name: i + "% edges", heuristics: heuristicResults, smallName: i + "% edges"))

        }

        List<Simulation> sims = new ArrayList<>()
        for(Simulation simulation : result.simulations) {
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
            Comparison greedy_wp = new Comparison(colors: round(colors, 2), percentage: round(percentage, 2))

            colors = greedy.colors - mis.colors
            percentage = ((greedy.colors - mis.colors) / greedy.colors) * 100
            Comparison greedy_mis = new Comparison(colors: round(colors, 2), percentage: round(percentage, 2))

            colors = greedy.colors - dsatur.colors
            percentage = ((greedy.colors - dsatur.colors) / greedy.colors) * 100
            Comparison greedy_dsatur = new Comparison(colors: round(colors, 2), percentage: round(percentage, 2))

            colors = wp.colors - mis.colors
            percentage = ((wp.colors - mis.colors) / wp.colors) * 100
            Comparison wp_mis = new Comparison(colors: round(colors, 2), percentage: round(percentage, 2))

            colors = wp.colors - dsatur.colors
            percentage = ((wp.colors - dsatur.colors) / wp.colors) * 100
            Comparison wp_dsatur = new Comparison(colors: round(colors, 2), percentage: round(percentage, 2))

            colors = mis.colors - dsatur.colors
            percentage = ((mis.colors - dsatur.colors) / mis.colors) * 100
            Comparison mis_dsatur = new Comparison(colors: round(colors, 2), percentage: round(percentage, 2))
            
            comparisonSummaries.add(new ComparisonSummary(name: simulationResult.name,
                    greedy_wp: greedy_wp,
                    greedy_mis: greedy_mis,
                    greedy_dsatur: greedy_dsatur,
                    wp_mis: wp_mis,
                    wp_dsatur: wp_dsatur,
                    mis_dsatur: mis_dsatur))
        }

        ResultSummary resultSummary = new ResultSummary(id: result.id, name: result.name, runs: result.runs, simulations: simulationResults, comparisonSummaries: comparisonSummaries, simulationsList: sims, vertices: result.vertices)
        return resultSummary
    }

    List<Result> list() {
        return resultRepository.findAll()
    }

    Result create(Map<String, String> resultData) {
        List<Simulation> simulations = runSimulations(resultData)
        int runs = Integer.parseInt(resultData.get("runs"))
        Result result = new Result(name: resultData.get("name"), simulations: simulations, vertices: Integer.parseInt(resultData.get("vertices")), runs: runs)
        return save(result)
    }

    void delete(String id) {
        Result result = resultRepository.findById(id)
        for(Simulation simulation : result.simulations) {
            Graph graph = simulation.graph
            if(graph) {
                graphService.delete(graph.id)
            }
            simulationService.delete(simulation.id)
        }
        resultRepository.delete(id)
    }

    List<Simulation> runSimulations(Map<String, String> resultData) {
        List<Simulation> simulations = new ArrayList<>()

        for(int i = Constants.EDGE_PERCENTAGE_START; i <= Constants.EDGE_PERCENTAGE_END; i += Constants.EDGE_PERCENTAGE_INCREMENT) {
            for(Integer j = 1; j <= Integer.parseInt(resultData.get("runs")); j++) {
                // Generate a graph
                Map<String, String> graphData = new HashMap<>()
                graphData.put("simulation", "true")
                graphData.put("edges", i.toString())
                graphData.put("vertices", resultData.get("vertices"))
                graphData.put("name", "Generated by result " + resultData.get("name") + " (run #" + j + "): " + i + "% edges")
                Graph graph = graphService.create(graphData)

                // Run simulation on graph
                Map<String, String> simulationData = new HashMap<>()
                simulationData.put("name", "Simulation for graph generated by result " + resultData.get("name") + " (run #" + j + "): " + i + "% edges")
                simulationData.put("type", "complex")
                simulationData.put("graphId", graph.id)
                simulationData.put("edgePercentage", i.toString())
                Simulation simulation = simulationService.create(simulationData)
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
