package com.wallanaq.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallanaq.api.dto.TokenIntrospectRequest;
import com.wallanaq.api.dto.TokenIntrospectResponse;
import com.wallanaq.api.service.TokenIntrospectService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/token/introspect")
public class TokenIntrospectController {
  
  @Autowired
  private TokenIntrospectService service;

  @PostMapping(consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
  public ResponseEntity<?> introspect(@Valid TokenIntrospectRequest request) {

    try {
      
      this.service.validate(request.token());

      return ResponseEntity.ok(new TokenIntrospectResponse(true));

    } catch (Exception e) {
      
      log.error("Token introspection failed. token: {} {}", request.token(), e.getMessage());

      return ResponseEntity.badRequest().body(new TokenIntrospectResponse(false));

    }

  }

}
