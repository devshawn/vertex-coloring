package com.devshawn.coloring.server.entity

import com.devshawn.coloring.library.ColoringResult
import com.devshawn.coloring.server.enums.GeneratedType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "coloring")
class Coloring {

    @Id
    String id

    @DBRef
    Graph graph

    ColoringResult result
    long time
    Integer timestamp = new Date().getTime() / 1000

    GeneratedType type
}
