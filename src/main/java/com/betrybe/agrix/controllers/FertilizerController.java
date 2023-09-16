package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
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
 * FertilizerController.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {
  private FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Criando uma fertilizar no banco de dados.
   */
  @PostMapping
  public ResponseEntity<FertilizerDto> createFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer newFertilizer = this.fertilizerService.save(fertilizerDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(FertilizerDto.fromEntity(newFertilizer));
  }

  /**
   * Retornando as fertilizers do banco de dados.
   */
  @GetMapping
  public ResponseEntity<List<FertilizerDto>> getAllFertilizers() {
    List<Fertilizer> fertilizers = this.fertilizerService.findAll();
    List<FertilizerDto> fertilizersDto = fertilizers.stream()
        .map(FertilizerDto::fromEntity).toList();
    return ResponseEntity.ok(fertilizersDto);
  }

  /**
   * Retornando um fertilizer pelo ID.
   */
  @GetMapping("{id}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Long id) {
    Fertilizer fertilizer = this.fertilizerService.findById(id);
    FertilizerDto fertilizerDto = FertilizerDto.fromEntity(fertilizer);
    return ResponseEntity.ok(fertilizerDto);
  }
}
