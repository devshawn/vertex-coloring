package com.devshawn.coloring.server.repository

import com.devshawn.coloring.server.entity.Coloring
import com.devshawn.coloring.server.entity.Graph
import com.devshawn.coloring.server.enums.GeneratedType
import org.springframework.data.mongodb.repository.MongoRepository

interface ColoringRepository extends MongoRepository<Coloring, String> {
    List<Coloring> findAll()
    List<Coloring> findByType(GeneratedType type)
    Coloring findById(String id)
    List<Coloring> findByGraph(Graph graph)
}
