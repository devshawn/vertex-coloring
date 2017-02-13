package com.devshawn.coloring.server.repository

import com.devshawn.coloring.server.entity.Graph
import org.springframework.data.mongodb.repository.MongoRepository

interface GraphRepository extends MongoRepository<Graph, String> {
    List<Graph> findAll()
    Graph findById(String id)
}
