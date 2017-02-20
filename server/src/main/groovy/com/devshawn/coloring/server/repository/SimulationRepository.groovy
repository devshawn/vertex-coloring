package com.devshawn.coloring.server.repository

import com.devshawn.coloring.server.entity.Graph
import com.devshawn.coloring.server.entity.Simulation
import org.springframework.data.mongodb.repository.MongoRepository

interface SimulationRepository extends MongoRepository<Simulation, String> {
    List<Simulation> findAll()
    Simulation findById(String id)
}
