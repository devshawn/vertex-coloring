package com.devshawn.coloring.server.controller

import com.devshawn.coloring.server.entity.Graph
import com.devshawn.coloring.server.entity.Simulation
import com.devshawn.coloring.server.service.GraphService
import com.devshawn.coloring.server.service.SimulationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/simulations")
class SimulationController {

    @Autowired
    SimulationService simulationService

    @RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    List getSimulations() {
        List<Simulation> simulations = simulationService.list()
        return simulations
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    Simulation createGraph(@RequestBody Map<String, String> simulationData) {
        Simulation simulation = simulationService.create(simulationData)
        return simulation
    }

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Simulation get(@PathVariable String id) {
        Simulation simulation = simulationService.get(id)
        return simulation
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    Map delete(@PathVariable String id) {
        simulationService.delete(id)
        return [success: true]
    }
}
