package com.devshawn.coloring.server.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "graph")
class Graph {

    @Id
    String id

    String name
    int[][] matrix
    int vertices
    int edges
}
