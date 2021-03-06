package com.devshawn.coloring.server.controller

import com.devshawn.coloring.server.entity.Result
import com.devshawn.coloring.server.service.ResultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/results")
class ResultController {

    @Autowired
    ResultService resultService

    @RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    List getResults() {
        List<Result> results = resultService.list()

        return results
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    Result createResult(@RequestBody Map<String, String> resultData) {
        Result result = resultService.create(resultData)
        return result
    }

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Result get(@PathVariable String id) {
        Result result = resultService.get(id)
        return result
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    Map delete(@PathVariable String id) {
        resultService.delete(id)
        return [success: true]
    }
}
