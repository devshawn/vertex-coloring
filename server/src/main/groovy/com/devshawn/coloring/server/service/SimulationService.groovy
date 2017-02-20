package com.devshawn.coloring.server.service

import com.devshawn.coloring.server.entity.Coloring
import com.devshawn.coloring.server.entity.Simulation
import com.devshawn.coloring.server.enums.GeneratedType
import com.devshawn.coloring.server.repository.SimulationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SimulationService {

    @Autowired
    SimulationRepository simulationRepository

    @Transactional
    Simulation save(Simulation simulation) {
        return simulationRepository.save(simulation)
    }

    Simulation get(String id) {
        return simulationRepository.findById(id)
    }

    List<Simulation> list() {
        return simulationRepository.findAll()
    }

    Simulation create(Map<String, String> simulationData) {
        Simulation simulation = new Simulation()
        return save(simulation)
    }

    void delete(String id) {
        Simulation simulation = simulationRepository.findById(id)
        simulationRepository.delete(id)
    }
}
