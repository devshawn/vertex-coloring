package com.devshawn.coloring.server.service

import com.devshawn.coloring.library.GraphGenerator
import com.devshawn.coloring.server.entity.Graph
import com.devshawn.coloring.server.repository.GraphRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GraphService {

    @Autowired
    GraphRepository graphRepository

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
        if(graphData.containsKey('matrix')) {
            int[][] matrix = GraphGenerator.matrixStringToGraph(graphData.get('matrix'))
            Graph graph = new Graph(name: graphData.get('name'), matrix: matrix, vertices: matrix.length)
            return save(graph)
        }
        double percentage = Integer.parseInt(graphData.get('edges')) / 100.0
        int[][] matrix = GraphGenerator.simple(Integer.parseInt(graphData.get('vertices')), percentage)

        Graph graph = new Graph(name: graphData.get('name'), matrix: matrix, vertices: matrix.length)
        return save(graph)
    }

    void delete(String id) {
        graphRepository.delete(id)
    }
}
