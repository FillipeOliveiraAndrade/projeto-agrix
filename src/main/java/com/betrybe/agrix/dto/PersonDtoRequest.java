package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * PersonDtoRequest.
 */
public record PersonDtoRequest(Long id, String username, String password, Role role) {
  /**
   * toEntity.
   */
  public Person toEntity() {
    Person person = new Person();

    person.setUsername(username);
    person.setPassword(password);
    person.setRole(role);
    return person;
  }
}
