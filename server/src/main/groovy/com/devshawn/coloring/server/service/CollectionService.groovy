package com.devshawn.coloring.server.service

import com.devshawn.coloring.server.entity.Result
import com.devshawn.coloring.server.repository.CollectionRepository
import com.devshawn.coloring.server.entity.Collection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CollectionService {
    @Autowired
    CollectionRepository collectionRepository

    @Autowired
    ResultService resultService

    @Transactional
    Collection save(Collection collection) {
        return collectionRepository.save(collection)
    }

    Collection get(String id) {
        return collectionRepository.findById(id)
    }

    List<Collection> list() {
        return collectionRepository.findAll()
    }

    Collection create(Map<String, String> collectionData) {
        List<Result> resultsList = new ArrayList<>()
        String[] results = collectionData.get("results").split("\n")

        for(int i = 0; i < results.length; i++) {
            Result result = resultService.get(results[i])
            if(result) {
                resultsList.add(result)
            }
        }

        Collection collection = new Collection(name: collectionData.get("name"), results: resultsList)
        return save(collection)
    }

    void delete(String id) {
        collectionRepository.delete(id)
    }
}
