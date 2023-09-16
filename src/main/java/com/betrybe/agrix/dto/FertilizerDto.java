package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * FertilizerDto.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {
  /**
   * fromEntity.
   */
  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
      fertilizer.getId(),
      fertilizer.getName(),
      fertilizer.getBrand(),
      fertilizer.getComposition()
    );
  }

  /**
   * toEntity.
   */
  public Fertilizer toEntity() {
    Fertilizer fertilizer = new Fertilizer();

    fertilizer.setName(this.name);
    fertilizer.setBrand(this.brand);
    fertilizer.setComposition(this.composition);
    return fertilizer;
  }
}
