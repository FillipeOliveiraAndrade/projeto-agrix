package com.betrybe.agrix.exception;

/**
 * CropNotFoundException.
 */
public class CropNotFoundException extends RuntimeException {
  public CropNotFoundException(String message) {
    super(message);
  }
}
