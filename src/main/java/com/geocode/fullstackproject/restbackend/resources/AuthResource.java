package com.geocode.fullstackproject.restbackend.resources;

import javax.servlet.http.HttpServletResponse;

import com.geocode.fullstackproject.restbackend.security.JWTUtil;
import com.geocode.fullstackproject.restbackend.security.UserSS;
import com.geocode.fullstackproject.restbackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthResource
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

  @Autowired
  private JWTUtil jwtUtil;

  @PostMapping(value = "/refresh_token")
  public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
    UserSS user = UserService.authenticatedUser();
    String token = jwtUtil.generateToken(user.getUsername());
    response.addHeader("Authorization", "Bearer " + token);
    return ResponseEntity.noContent().build();
  }

}
