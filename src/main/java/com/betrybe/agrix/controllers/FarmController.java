package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FarmController.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {
  private FarmService farmService;
  private CropService cropService;

  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Criando nova farm.
   */
  @PostMapping
  public ResponseEntity<Farm> createdFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = this.farmService.save(farmDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  /**
   * Retornando todas as farms.
   */
  @GetMapping
  public ResponseEntity<List<FarmDto>> findAllFarms() {
    List<Farm> farmsList = this.farmService.findAll();
    List<FarmDto> farmDtos = farmsList.stream().map(FarmDto::fromEntity).toList();
    return ResponseEntity.ok(farmDtos);
  }

  /**
   * Retornando farm pelo ID.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) {
    Farm farm = this.farmService.findById(id);
    FarmDto farmDto = FarmDto.fromEntity(farm);
    return ResponseEntity.ok(farmDto);
  }

  /**
   * Criando nova crop.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(
      @PathVariable Long farmId,
      @RequestBody CropDto cropDto
  ) {
    Crop newCrop = this.cropService.save(farmId, cropDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(CropDto.fromEntity(newCrop));
  }

  /**
   * Retornando todas as crops de uma fazenda apenas.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getCropOfFarm(@PathVariable Long farmId) {
    List<Crop> crops = this.farmService.findAllCropsOfFarm(farmId);
    List<CropDto> cropsDto = crops.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.ok(cropsDto);
  }
}
