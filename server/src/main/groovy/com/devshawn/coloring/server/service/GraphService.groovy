package com.devshawn.coloring.server.service

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
}
