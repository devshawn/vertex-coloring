package com.devshawn.coloring.server.service

import com.devshawn.coloring.library.ColoringHeuristic
import com.devshawn.coloring.library.ColoringModule
import com.devshawn.coloring.library.ColoringResult
import com.devshawn.coloring.server.entity.Coloring
import com.devshawn.coloring.server.entity.Graph
import com.devshawn.coloring.server.enums.GeneratedType
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
        List<Coloring> colorings = coloringRepository.findAll()
        List<Coloring> coloringsList = new ArrayList<Coloring>()

        for(Coloring coloring : colorings) {
            ColoringResult result = coloring.result
            coloringsList.add(new Coloring(id: coloring.id, graph: new Graph(name: coloring.graph.name), result: result, type: coloring.type))
        }

        return coloringsList
    }

    Coloring create(Map<String, String> coloringData) {
        Graph graph = graphService.get(coloringData.get('graphId'))

        ColoringModule coloringModule = new ColoringModule()
        coloringModule.setGraph(graph.matrix)
        ColoringResult result = coloringModule.applyHeuristic(ColoringHeuristic.valueOf(coloringData.get('heuristic')))
        GeneratedType type = (coloringData.get('type') != null) ? GeneratedType.SIMULATION_GENERATED : GeneratedType.USER_GENERATED
        Coloring coloring = new Coloring(graph: graph, result: result, type: type, time: (result.time / 1000000))
        return save(coloring)
    }

    void delete(String id) {
        coloringRepository.delete(id)
    }
}
