package com.devshawn.coloring.server.service

import com.devshawn.coloring.library.ColoringHeuristic
import com.devshawn.coloring.library.ColoringModule
import com.devshawn.coloring.library.ColoringResult
import com.devshawn.coloring.server.entity.Coloring
import com.devshawn.coloring.server.entity.Graph
import com.devshawn.coloring.server.repository.ColoringRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ColoringService {

    @Autowired
    ColoringRepository coloringRepository

    @Autowired
    GraphService graphService

    @Transactional
    Coloring save(Coloring coloring) {
        return coloringRepository.save(coloring)
    }

    Coloring get(String id) {
        return coloringRepository.findById(id)
    }

    List<Coloring> list() {
        return coloringRepository.findAll()
    }

    Coloring create(Map<String, String> coloringData) {
        Graph graph = graphService.get(coloringData.get('graphId'))

        ColoringModule coloringModule = new ColoringModule()
        coloringModule.setGraph(graph.matrix)
        ColoringResult result = coloringModule.applyHeuristic(ColoringHeuristic.valueOf(coloringData.get('heuristic')))
        Coloring coloring = new Coloring(graph: graph, result: result)
        return save(coloring)
    }

    void delete(String id) {
        coloringRepository.delete(id)
    }
}
