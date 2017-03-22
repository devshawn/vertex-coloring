package com.devshawn.coloring.server.repository

import com.devshawn.coloring.server.entity.Graph
import com.devshawn.coloring.server.entity.Simulation
import com.devshawn.coloring.server.enums.SimulationType
import org.springframework.data.mongodb.repository.MongoRepository

interface SimulationRepository extends MongoRepository<Simulation, String> {
    List<Simulation> findAll()
    List<Simulation> findByType(SimulationType type)
    Simulation findById(String id)
}
