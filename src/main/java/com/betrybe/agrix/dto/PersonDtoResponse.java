package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * PersonDtoResponse.
 */
public record PersonDtoResponse(Long id, String username, Role role) {
  /**
   * fromEntity.
   */
  public static PersonDtoResponse fromEntity(Person person) {
    return new PersonDtoResponse(
      person.getId(),
      person.getUsername(),
      person.getRole()
    ); 
  } 
}
