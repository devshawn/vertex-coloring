package com.devshawn.coloring.server.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "result")
class Result {

    @Id
    String id

    @DBRef
    List<Simulation> simulations

    String name
    int vertices
    int runs
    int start
    int end
    int increment

}
