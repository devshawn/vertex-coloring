package com.devshawn.coloring.server.entity

import com.devshawn.coloring.library.ColoringResult
import com.devshawn.coloring.server.enums.GeneratedType
import com.devshawn.coloring.server.enums.SimulationType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "simulation")
class Simulation {

    @Id
    String id

    @DBRef
    Graph graph

    @DBRef
    List<Coloring> colorings

    String name
    SimulationType type
}
