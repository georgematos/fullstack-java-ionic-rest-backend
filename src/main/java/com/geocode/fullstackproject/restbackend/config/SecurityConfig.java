package com.geocode.fullstackproject.restbackend.config;

import java.util.Arrays;
import java.util.Collections;

import com.geocode.fullstackproject.restbackend.security.JWTAuthenticationFilter;
import com.geocode.fullstackproject.restbackend.security.JWTAuthorizationFilter;
import com.geocode.fullstackproject.restbackend.security.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * SecurityConfig
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailService;

  @Autowired
  private JWTUtil jwtUtil;

  private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };

  private static final String[] PUBLIC_MATCHERS_GET = { "/produtos/**", "/categorias/**", "/estados",
      "/estados/{estado_id}/cidades" };

  private static final String[] PUBLIC_MATCHERS_POST = { "/clientes", "/auth/forgot/**" };

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable();
    http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET)
        .permitAll().antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll().anyRequest().authenticated();
    http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
    http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailService));
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
  }

  @Bean
  CorsConfigurationSource corsConfiguraionSource() {
    CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
    configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "OPTIONS"));
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    // Don't do this in production, use a proper list of allowed origins
    // TODO trocar o endereço pelo do servidor de produção
    config.setAllowedOrigins(Collections.singletonList("*"));
    // config.setAllowedOrigins(Collections.singletonList("http://localhost:8100"));
    config.setAllowedHeaders(Arrays.asList("*")); //"Origin", "Content-Type", "Accept"
    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

}
