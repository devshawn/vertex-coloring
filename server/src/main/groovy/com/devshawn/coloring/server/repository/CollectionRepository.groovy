package com.devshawn.coloring.server.repository

import com.devshawn.coloring.server.entity.Collection
import org.springframework.data.mongodb.repository.MongoRepository

interface CollectionRepository extends MongoRepository<Collection, String> {
    List<Collection> findAll()
    Collection findById(String id)
}
