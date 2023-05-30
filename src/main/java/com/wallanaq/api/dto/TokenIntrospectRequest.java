package com.wallanaq.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public record TokenIntrospectRequest(

  @NotNull
  String token,
  @JsonProperty("token_type_hint")
  String tokenType

) {}
