package com.betrybe.agrix.exception;

/**
 * FertilizerNotFoundException.
 */
public class FertilizerNotFoundException extends RuntimeException {
  public FertilizerNotFoundException(String message) {
    super(message);
  }
}
