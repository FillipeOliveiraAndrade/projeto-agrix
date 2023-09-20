package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.AuthenticationDto;
import com.betrybe.agrix.dto.TokenResponseDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthenticationController.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  private AuthenticationManager authenticationManager;
  private TokenService tokenService;

  @Autowired
  public AuthenticationController(
      AuthenticationManager authenticationManager,
      TokenService tokenService
  ) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Authenticando person.
   */
  @PostMapping("/login")
  public ResponseEntity<TokenResponseDto> login(@RequestBody AuthenticationDto authenticationDto) {
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(
          authenticationDto.username(),
          authenticationDto.password()
        );

    Authentication auth = authenticationManager.authenticate(usernamePassword);
    Person person = (Person) auth.getPrincipal();

    String token = tokenService.generateToken(person);
    return ResponseEntity.ok(TokenResponseDto.fromEntity(token));
  }
}
