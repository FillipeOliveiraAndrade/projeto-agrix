package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.PersonDtoRequest;
import com.betrybe.agrix.dto.PersonDtoResponse;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PersonController.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
  private PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Criando nova pessoa.
   */
  @PostMapping
  public ResponseEntity<PersonDtoResponse> createPerson(@RequestBody PersonDtoRequest personDto) {
    Person newPerson = this.personService.create(personDto.toEntity());
    PersonDtoResponse response = PersonDtoResponse.fromEntity(newPerson);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
