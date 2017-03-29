package com.devshawn.coloring.server.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "collection")
class Collection {

    @Id
    String id

    @DBRef
    List<Result> results

    String name
}
