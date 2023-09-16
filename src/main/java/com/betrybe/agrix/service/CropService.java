package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CropService.
 */
@Service
public class CropService {
  private CropRepository cropRepository;
  private FarmRepository farmRepository;
  private FertilizerRepository fertilizerRepository;

  /**
   * Construtor.
   */
  @Autowired
  public CropService(
      CropRepository cropRepository,
      FarmRepository farmRepository,
      FertilizerRepository fertilizerRepository
  ) {
    this.cropRepository = cropRepository;
    this.farmRepository = farmRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Retornando todas as crops do banco de dados.
   */
  public List<Crop> findAll() {
    List<Crop> crops = this.cropRepository.findAll();
    return crops;
  }

  /**
   * Criando uma crop no banco de dados.
   */
  public Crop save(Long id, Crop crop) {
    Optional<Farm> farmOptional = this.farmRepository.findById(id);
    if (farmOptional.isEmpty()) {
      throw new FarmNotFoundException("Fazenda não encontrada!");
    }

    Farm farm = farmOptional.get();
    Crop newCrop = new Crop();

    newCrop.setName(crop.getName());
    newCrop.setPlantedArea(crop.getPlantedArea());
    newCrop.setPlantedDate(crop.getPlantedDate());
    newCrop.setHarvestDate(crop.getHarvestDate());
    newCrop.setFarm(farm);

    Crop cropCreated = this.cropRepository.save(newCrop);
    return cropCreated;
  }

  /**
   * Retornando uma crop específicado pelo ID.
   */
  public Crop findById(Long id) {
    Optional<Crop> cropOptional = this.cropRepository.findById(id);
    if (cropOptional.isEmpty()) {
      throw new CropNotFoundException("Plantação não encontrada!");
    }

    return cropOptional.get();
  }

  /**
   * Retornando as crop filtrando pelas datas.
   */
  public List<Crop> findByDates(LocalDate start, LocalDate end) {
    List<Crop> crops = this.cropRepository.findByHarvestDateBetween(start, end);
    return crops;
  }

  /**
   * Associando crops e fertilizers.
   */
  public String combiningCropsAndFertilizers(Long cropId, Long fertilizerId) {
    Optional<Crop> cropOptional = this.cropRepository.findById(cropId);
    if (cropOptional.isEmpty()) {
      throw new CropNotFoundException("Plantação não encontrada!");
    }

    Optional<Fertilizer> fertilizerOptional = this.fertilizerRepository.findById(fertilizerId);
    if (fertilizerOptional.isEmpty()) {
      throw new FertilizerNotFoundException("Fertilizante não encontrado!");
    }

    Crop crop = cropOptional.get();
    Fertilizer fertilizer = fertilizerOptional.get();

    crop.getFertilizers().add(fertilizer);
    fertilizer.getCrops().add(crop);
    this.fertilizerRepository.save(fertilizer);

    return "Fertilizante e plantação associados com sucesso!";
  }

  /**
   * Retornando todas os fertilizantes de uma plantação.
   */
  public List<Fertilizer> getAllFertilizersOfCrop(Long id) {
    Optional<Crop> cropOptional = this.cropRepository.findById(id);
    if (cropOptional.isEmpty()) {
      throw new CropNotFoundException("Plantação não encontrada!");
    }

    Crop crop = cropOptional.get();
    List<Fertilizer> fertilizers = crop.getFertilizers();

    return fertilizers;
  }
}
