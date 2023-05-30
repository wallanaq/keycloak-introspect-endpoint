package com.wallanaq.api.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "keycloak.introspect")
public class IntrospectConfig {
  
  private EPublicKeyProvider publicKeyProvider;
  private String publicKeyFile;
  
  private Map<String, String> publicKeys;
  
  @PostConstruct
  @SuppressWarnings({"unchecked"})
  public void init() {

    if (publicKeyProvider == EPublicKeyProvider.PUBLIC_KEY_FILE) {

      log.debug("Loading authorization server public keys from file");

      try {

        InputStream inStream = new FileInputStream(new File(publicKeyFile));
        publicKeys = (Map<String,String>) new Yaml().load(inStream);

      } catch (FileNotFoundException e) {
        log.error("Error to load public keys file {}", publicKeyFile, e);
      }

    }

  }

}
