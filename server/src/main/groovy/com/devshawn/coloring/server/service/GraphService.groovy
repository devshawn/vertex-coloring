package com.devshawn.coloring.server.service

import com.devshawn.coloring.library.ColoringModule
import com.devshawn.coloring.library.GraphGenerator
import com.devshawn.coloring.server.entity.Coloring
import com.devshawn.coloring.server.entity.Graph
import com.devshawn.coloring.server.enums.GeneratedType
import com.devshawn.coloring.server.repository.ColoringRepository
import com.devshawn.coloring.server.repository.GraphRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.annotation.Generated

@Service
class GraphService {

    @Autowired
    GraphRepository graphRepository

    @Autowired
    ColoringRepository coloringRepository

    @Transactional
    Graph save(Graph graph) {
        return graphRepository.save(graph)
    }

    Graph get(String id) {
        return graphRepository.findById(id)
    }

    List<Graph> list() {
        return graphRepository.findAll()
    }

    Graph create(Map<String, String> graphData) {
        ColoringModule coloringModule = new ColoringModule()

        if(graphData.containsKey('matrix')) {
            int[][] matrix = GraphGenerator.matrixStringToGraph(graphData.get('matrix'))
            coloringModule.setGraph(matrix)
            Graph graph = new Graph(name: graphData.get('name'), matrix: matrix, vertices: matrix.length, edges: coloringModule.getEdgeCount(), type: GeneratedType.USER_ENTERED)
            return save(graph)
        }

        double percentage = Integer.parseInt(graphData.get('edges')) / 100.0
        int[][] matrix = GraphGenerator.simple(Integer.parseInt(graphData.get('vertices')), percentage)
        coloringModule.setGraph(matrix)
        GeneratedType type = (graphData.containsKey("simulation") ? GeneratedType.SIMULATION_GENERATED : GeneratedType.USER_GENERATED)
        Graph graph = new Graph(name: graphData.get('name'), matrix: matrix, vertices: matrix.length, edges: coloringModule.getEdgeCount(), type: type)
        return save(graph)
    }

    void delete(String id) {
        Graph graph = graphRepository.findById(id)
        List<Coloring> colorings = coloringRepository.findByGraph(graph)
        println colorings
        for(Coloring coloring : colorings) {
            coloringRepository.delete(coloring)
        }
        graphRepository.delete(id)
    }
}
