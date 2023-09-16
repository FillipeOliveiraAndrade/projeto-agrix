package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FarmService.
 */
@Service
public class FarmService {
  private FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  /**
   * Criando uma farm no banco de dados.
   */
  public Farm save(Farm farm) {
    Farm created = this.farmRepository.save(farm);
    return created;
  }

  /**
   * Retornando todas as farms do banco de dados.
   */
  public List<Farm> findAll() {
    List<Farm> farms = this.farmRepository.findAll();
    return farms;
  }

  /**
   * Retornando uma farm específicado pelo ID.
   */
  public Farm findById(Long id) {
    Optional<Farm> farmOptional = this.farmRepository.findById(id);

    if (farmOptional.isEmpty()) {
      throw new FarmNotFoundException("Fazenda não encontrada!");
    }

    return farmOptional.get();
  }

  /**
   * Retornando todas as plantações de uma fazenda específica.
   */
  public List<Crop> findAllCropsOfFarm(Long id) {
    Farm farm = this.findById(id);
    List<Crop> cropsOfFarm = farm.getCrops();
    return cropsOfFarm;
  }
}
