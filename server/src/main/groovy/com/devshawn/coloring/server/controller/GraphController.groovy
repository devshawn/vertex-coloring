package com.devshawn.coloring.server.controller

import com.devshawn.coloring.server.entity.Graph
import com.devshawn.coloring.server.service.GraphService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid

@RestController
@RequestMapping("/api/graphs")
class GraphController {
    final int maxVerticesToShowMatrix = 99

    @Autowired
    GraphService graphService

    @RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    List getGraphs() {
        List<Graph> graphs = graphService.list()
        for(Graph graph : graphs) {
            if(graph.vertices > maxVerticesToShowMatrix) {
                graph.matrix = null
            }
        }
        return graphs
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    Graph createGraph(@RequestBody Map<String, String> graphData) {
        System.out.println(graphData)
        Graph graph = graphService.create(graphData)
        return graph
    }

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Graph get(@PathVariable String id) {
        Graph graph = graphService.get(id)
        if(graph.vertices > maxVerticesToShowMatrix) {
            graph.matrix = null
        }
        return graph
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    Map delete(@PathVariable String id) {
        graphService.delete(id)
        return [success: true]
    }


}
