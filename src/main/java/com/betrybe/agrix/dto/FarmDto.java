package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Farm;

/**
 * FarmDTO.
 */
public record FarmDto(Long id, String name, Double size) {
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(farm.getId(), farm.getName(), farm.getSize());
  }

  /**
   * toEntity. 
   */
  public Farm toEntity() {
    Farm farm = new Farm();

    farm.setName(this.name);
    farm.setSize(this.size);
    return farm;
  }
}
