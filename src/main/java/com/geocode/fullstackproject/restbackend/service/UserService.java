package com.geocode.fullstackproject.restbackend.service;

import com.geocode.fullstackproject.restbackend.security.UserSS;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * UserService
 */
public class UserService {

  public static UserSS authenticatedUser() {
    try {
      return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    } catch (Exception e) {
      return null;
    }
  }
  
}
