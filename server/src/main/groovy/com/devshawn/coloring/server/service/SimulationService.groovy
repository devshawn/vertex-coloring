package com.devshawn.coloring.server.service

import com.devshawn.coloring.server.entity.Coloring
import com.devshawn.coloring.server.entity.Graph
import com.devshawn.coloring.server.entity.Simulation
import com.devshawn.coloring.server.enums.GeneratedType
import com.devshawn.coloring.server.enums.SimulationType
import com.devshawn.coloring.server.repository.ColoringRepository
import com.devshawn.coloring.server.repository.SimulationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SimulationService {

    @Autowired
    GraphService graphService

    @Autowired
    ColoringService coloringService

    @Autowired
    SimulationRepository simulationRepository

    @Autowired
    ColoringRepository coloringRepository

    @Transactional
    Simulation save(Simulation simulation) {
        return simulationRepository.save(simulation)
    }

    Simulation get(String id) {
        Simulation simulation = simulationRepository.findById(id)

        if(simulation.type == SimulationType.SIMPLE) {
            simulation.graph.matrix = null
            for(Coloring coloring : simulation.colorings) {
                coloring.graph = null
            }
        }

        return simulation
    }

    List<Simulation> list() {
        List<Simulation> simulations = simulationRepository.findByType(SimulationType.SIMPLE)
        List<Simulation> simulationsList = new ArrayList<Simulation>()

        for(Simulation simulation : simulations) {
            simulationsList.add(new Simulation(name: simulation.name, type: simulation.type, id: simulation.id))
        }

        return simulationsList
    }

    Simulation create(Map<String, String> simulationData) {
        if(simulationData.get('type') == 'SIMPLE') {
            Graph graph = graphService.get(simulationData.get('graphId'))
            List<Coloring> colorings = runIteration(simulationData.get('graphId'))
            Simulation simulation = new Simulation(name: simulationData.get('name'), type: SimulationType.SIMPLE, graph: graph, colorings: colorings)

            return save(simulation)
        }

        Graph graph = graphService.get(simulationData.get('graphId'))
        List<Coloring> colorings = runIteration(simulationData.get('graphId'))
        Simulation simulation = new Simulation(name: simulationData.get('name'), type: SimulationType.COMPLEX, graph: graph, colorings: colorings, edgePercentage: Integer.parseInt(simulationData.get('edgePercentage')))

        return save(simulation)
    }

    Simulation createForResult(Map<String, String> simulationData, Graph graph) {
        List<Coloring> colorings = runIterationForResult(graph)
        Simulation simulation = new Simulation(name: simulationData.get('name'), type: SimulationType.COMPLEX, graph: graph, colorings: colorings, edgePercentage: Integer.parseInt(simulationData.get('edgePercentage')))
        return simulation
    }

    void delete(String id) {
        Simulation simulation = simulationRepository.findById(id)
        for(Coloring coloring : simulation.colorings) {
            coloringRepository.delete(coloring)
        }
        simulationRepository.delete(id)
    }

    private List<Coloring> runIteration(String graphId) {
        List<Coloring> colorings = new ArrayList<Coloring>()
        Map<String, String> data = new HashMap<String, String>()
        data.put("type", "simulation")
        data.put("graphId", graphId)

        data.put("heuristic", "GREEDY")
        colorings.add(coloringService.create(data))

        data.put("heuristic", "WELSH_POWELL")
        colorings.add(coloringService.create(data))

        data.put("heuristic", "MAXIMAL_INDEPENDENT_SET")
        colorings.add(coloringService.create(data))

        data.put("heuristic", "DSATUR")
        colorings.add(coloringService.create(data))

        return colorings
    }

    private List<Coloring> runIterationForResult(Graph graph) {
        List<Coloring> colorings = new ArrayList<Coloring>()
        Map<String, String> data = new HashMap<String, String>()
        data.put("type", "simulation")

        data.put("heuristic", "GREEDY")
        colorings.add(coloringService.createForResult(data, graph))

        data.put("heuristic", "WELSH_POWELL")
        colorings.add(coloringService.createForResult(data, graph))

        data.put("heuristic", "MAXIMAL_INDEPENDENT_SET")
        colorings.add(coloringService.createForResult(data, graph))

        data.put("heuristic", "DSATUR")
        colorings.add(coloringService.createForResult(data, graph))

        return colorings
    }
}
