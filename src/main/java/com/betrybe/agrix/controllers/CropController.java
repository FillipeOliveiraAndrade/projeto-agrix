package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * CropController.
 */
@RestController
@RequestMapping("/crops")
public class CropController {
  private CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Retornando todas as crops.
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> findAllCrops() {
    List<Crop> crops = this.cropService.findAll();
    List<CropDto> cropsDto = crops.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.ok(cropsDto);
  }

  /**
   * Retornando crop pelo ID.
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) {
    Crop crop = this.cropService.findById(id);
    CropDto cropDto = CropDto.fromEntity(crop);
    return ResponseEntity.ok(cropDto);
  }

  /**
   * Retornando crop filtrando pelas datas.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getCropsByHarvestDate(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
  ) {
    List<Crop> crops = this.cropService.findByDates(start, end);
    List<CropDto> cropsDto = crops.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.ok(cropsDto);
  }

  /**
   * Associando as tabelas Crop e Fertilizer.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> compiningCropAndFertilizer(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId
  ) {
    String result = this.cropService.combiningCropsAndFertilizers(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  /**
   *  Retornando todas os fertilizers de uma crop.
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getAllFertilizersOfCrop(@PathVariable Long cropId) {
    List<Fertilizer> fertilizers = this.cropService.getAllFertilizersOfCrop(cropId);
    List<FertilizerDto> fertilizersDto = fertilizers.stream()
        .map(FertilizerDto::fromEntity).toList();
    return ResponseEntity.ok(fertilizersDto);
  }
}
