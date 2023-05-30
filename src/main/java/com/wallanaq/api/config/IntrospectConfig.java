package com.wallanaq.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "keycloak.introspect")
public class IntrospectConfig {
  
  EPublicKeyProvider publicKeyProvider;
  
}
