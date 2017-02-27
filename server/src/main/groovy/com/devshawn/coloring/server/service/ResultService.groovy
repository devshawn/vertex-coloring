package com.devshawn.coloring.server.service

import com.devshawn.coloring.server.entity.Coloring
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
        for(Simulation simulation : result.simulations) {
            simulation.graph = null
            List<HeuristicResult> heuristicResults = new ArrayList<>()
            for(Coloring coloring : simulation.colorings) {
                heuristicResults.add(new HeuristicResult(heuristic: coloring.result.heuristic, time: coloring.result.time, colors: coloring.result.coloringNumber))
            }
            simulationResults.add(new SimulationResult(name: simulation.name, id: simulation.id, heuristics: heuristicResults, smallName: simulation.name.substring(simulation.name.lastIndexOf(':') + 2)))
        }


        ResultSummary resultSummary = new ResultSummary(id: result.id, name: result.name, simulations: simulationResults, vertices: result.vertices)
        return resultSummary
    }

    List<Result> list() {
        return resultRepository.findAll()
    }

    Result create(Map<String, String> resultData) {
        List<Simulation> simulations = runSimulations(resultData)
        int runs = 1
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

        for(Integer i = 0; i <= 100; i += 10) {
            // Generate a graph
            Map<String, String> graphData = new HashMap<>()
            graphData.put("simulation", "true")
            graphData.put("edges", i.toString())
            graphData.put("vertices", resultData.get("vertices"))
            graphData.put("name", "Generated by result " + resultData.get("name") + ": " + i + "% edges")
            Graph graph = graphService.create(graphData)

            // Run simulation on graph
            Map<String, String> simulationData = new HashMap<>()
            simulationData.put("name", "Simulation for graph generated by result " + resultData.get("name") + ": " + i + "% edges")
            simulationData.put("type", "complex")
            simulationData.put("graphId", graph.id)
            Simulation simulation = simulationService.create(simulationData)
            simulations.add(simulation)
        }

        return simulations
    }
}
