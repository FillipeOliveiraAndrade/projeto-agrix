package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FertilizerService.
 */
@Service
public class FertilizerService {
  private FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Criando uma fertilizar no banco de dados.
   */
  public Fertilizer save(Fertilizer fertilizer) {
    Fertilizer newFertilizer = this.fertilizerRepository.save(fertilizer);
    return newFertilizer;
  }

  /**
   * Retornando todas as fertilizers di banco de dados.
   */
  public List<Fertilizer> findAll() {
    List<Fertilizer> fertilizers = this.fertilizerRepository.findAll();
    return fertilizers;
  }

  /**
   * Retornando um fertilizer específicado pelo ID.
   */
  public Fertilizer findById(Long id) {
    Optional<Fertilizer> fertilizerOptional = this.fertilizerRepository.findById(id);
    if (fertilizerOptional.isEmpty()) {
      throw new FertilizerNotFoundException("Fertilizante não encontrado!");
    }

    return fertilizerOptional.get();
  }
}
