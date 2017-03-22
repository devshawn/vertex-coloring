package com.devshawn.coloring.server.repository

import com.devshawn.coloring.server.entity.Graph
import com.devshawn.coloring.server.enums.GeneratedType
import org.springframework.data.mongodb.repository.MongoRepository

interface GraphRepository extends MongoRepository<Graph, String> {
    List<Graph> findAll()
    List<Graph> findByTypeNot(GeneratedType type)
    Graph findById(String id)
}
