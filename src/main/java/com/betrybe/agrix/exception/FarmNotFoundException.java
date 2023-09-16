package com.betrybe.agrix.exception;

/**
 * FarmNotFoundException.
 */
public class FarmNotFoundException extends RuntimeException {
  public FarmNotFoundException(String message) {
    super(message);
  }
}
