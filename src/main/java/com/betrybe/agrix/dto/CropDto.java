package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/**
 * CropDTO.
 */
public record CropDto(
    Long id,
    String name,
    Double plantedArea,
    Long farmId,
    LocalDate plantedDate,
    LocalDate harvestDate
) {

  /**
   * fromEntity.
   */
  public static CropDto fromEntity(Crop crop) {
    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId(),
        crop.getPlantedDate(),
        crop.getHarvestDate()
    );
  }
  
  /**
   * toEntity.
   */
  public Crop toEntity() {
    Crop crop = new Crop();

    crop.setName(this.name);
    crop.setPlantedArea(this.plantedArea);
    crop.setPlantedDate(this.plantedDate);
    crop.setHarvestDate(this.harvestDate);
    return crop;
  }
}
