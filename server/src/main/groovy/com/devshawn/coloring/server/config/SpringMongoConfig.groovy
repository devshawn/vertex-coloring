package com.devshawn.coloring.server.config

import com.mongodb.Mongo
import com.mongodb.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoConfiguration

@Configuration
class SpringMongoConfig extends AbstractMongoConfiguration {

    @Override
    String getDatabaseName() {
        return "test"
    }

    @Override
    @Bean
    Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1")
    }
}
