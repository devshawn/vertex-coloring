package com.devshawn.coloring.server.controller

import com.devshawn.coloring.library.ColoringHeuristic
import com.devshawn.coloring.server.entity.Coloring
import com.devshawn.coloring.server.service.ColoringService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/colorings")
class ColoringController {

    @Autowired
    ColoringService coloringService

    @RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    List getColorings() {
        List<Coloring> colorings = coloringService.list()
        return colorings
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    Coloring createColoring(@RequestBody Map<String, String> coloringData) {
        Coloring coloring = coloringService.create(coloringData)
        return coloring
    }

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Coloring get(@PathVariable String id) {
        Coloring coloring = coloringService.get(id)
        return coloring
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    Map delete(@PathVariable String id) {
        coloringService.delete(id)
        return [success: true]
    }

    @RequestMapping(value = "/heuristics", produces = MediaType.APPLICATION_JSON_VALUE)
    ColoringHeuristic[] heuristics() {
        return ColoringHeuristic.values()
    }
}
