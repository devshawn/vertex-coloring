package com.devshawn.coloring.server.entity

import com.devshawn.coloring.server.enums.GeneratedType
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

    GeneratedType type
}
