package com.devshawn.coloring.server.repository

import com.devshawn.coloring.server.entity.Result
import org.springframework.data.mongodb.repository.MongoRepository

interface ResultRepository extends MongoRepository<Result, String> {
    List<Result> findAll()
    Result findById(String id)
}
