package com.geocode.fullstackproject.restbackend.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.geocode.fullstackproject.restbackend.domain.dto.EmailDTO;
import com.geocode.fullstackproject.restbackend.security.JWTUtil;
import com.geocode.fullstackproject.restbackend.security.UserSS;
import com.geocode.fullstackproject.restbackend.service.AuthService;
import com.geocode.fullstackproject.restbackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @Autowired
  private AuthService authService;

  @PostMapping(value = "/refresh_token")
  public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
    UserSS user = UserService.authenticatedUser();
    String token = jwtUtil.generateToken(user.getUsername());
    response.addHeader("Authorization", "Bearer " + token);
    response.addHeader("access-control-expose-headers", "Authorization");
    return ResponseEntity.noContent().build();
  }

  @PostMapping(value = "/forgot")
  public ResponseEntity<Void> forgot(@RequestBody @Valid EmailDTO emailDto) {

    authService.sendNewPassword(emailDto.getEmail());

    return ResponseEntity.noContent().build();
  }

}
