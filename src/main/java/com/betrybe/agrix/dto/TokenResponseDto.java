package com.betrybe.agrix.dto;

/**
 * TokenResponseDto.
 */
public record TokenResponseDto(String token) {
  /**
   * fromEntity.
   */
  public static TokenResponseDto fromEntity(String token) {
    return new TokenResponseDto(token);
  }
}
