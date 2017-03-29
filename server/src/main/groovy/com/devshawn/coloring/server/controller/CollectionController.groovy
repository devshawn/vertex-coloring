package com.devshawn.coloring.server.controller

import com.devshawn.coloring.server.entity.Collection
import com.devshawn.coloring.server.service.CollectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/collections")
class CollectionController {

    @Autowired
    CollectionService collectionService

    @RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    List getCollections() {
        List<Collection> collections = collectionService.list()
        return collections
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    Collection createCollection(@RequestBody Map<String, String> collectionData) {
        Collection collection = collectionService.create(collectionData)
        return collection
    }

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Collection get(@PathVariable String id) {
        Collection collection = collectionService.get(id)
        return collection
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    Map delete(@PathVariable String id) {
        collectionService.delete(id)
        return [success: true]
    }


}
