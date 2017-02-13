package com.devshawn.coloring.server.controller

import com.devshawn.coloring.server.entity.Graph
import com.devshawn.coloring.server.service.GraphService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/graphs")
class GraphController {

    @Autowired
    GraphService graphService

    @RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    List getGraphs() {
        List<Graph> graphs = graphService.list()
        return graphs
    }

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Graph get(@PathVariable String id) {
        Graph graph = graphService.get("")
        return graph
    }


}
