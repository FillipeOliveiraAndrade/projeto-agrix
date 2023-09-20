package com.betrybe.agrix.security;

import com.betrybe.agrix.service.PersonService;
import com.betrybe.agrix.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * SecurityFilter.
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {
  private TokenService tokenService;
  private PersonService personService;

  public SecurityFilter(TokenService tokenService, PersonService personService) {
    this.tokenService = tokenService;
    this.personService = personService;
  }

  /**
   * Filtros.
   */
  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws IOException, ServletException {
    String token = recoveryToken(request);

    if (token != null) {
      String subject = tokenService.validateToken(token);
      UserDetails userDetails = personService.loadUserByUsername(subject);

      UsernamePasswordAuthenticationToken auth =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      
      SecurityContextHolder.getContext().setAuthentication(auth);
    }

    filterChain.doFilter(request, response);
  }

  private String recoveryToken(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    
    if (authHeader == null) {
      return null;
    }

    return authHeader.replace("Bearer ", "");
  }
}
