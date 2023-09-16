package com.betrybe.agrix.controllers;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalControllerAdvice.
 */
@ControllerAdvice
public class GlobalControllerAdvice {
  @ExceptionHandler({
    FarmNotFoundException.class,
    CropNotFoundException.class,
    FertilizerNotFoundException.class
  })
  public ResponseEntity<String> farmNotFound(RuntimeException exception) {
    return ResponseEntity.status(404).body(exception.getMessage());
  }
}
