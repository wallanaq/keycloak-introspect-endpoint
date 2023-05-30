package com.wallanaq.api.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wallanaq.api.config.EPublicKeyProvider;
import com.wallanaq.api.config.IntrospectConfig;

@Service
public class TokenIntrospectService {

  @Autowired
  private IntrospectConfig config;

  public boolean validate(String token) throws InvalidKeySpecException, MalformedURLException, JwkException {

    DecodedJWT jwt = JWT.decode(token);

    RSAPublicKey publicKey = loadPublicKey(jwt);

    Algorithm alg = Algorithm.RSA256(publicKey);

    JWTVerifier verifier = JWT.require(alg).withIssuer(jwt.getIssuer()).build();

    verifier.verify(token);

    return true;

  }

  private RSAPublicKey loadPublicKeyFromAuthorizationServer(DecodedJWT jwt) throws MalformedURLException, JwkException, InvalidKeySpecException {

    String authServerUrl = getAuthorizationServerCertificateUrl(jwt);

    JwkProvider provider = new UrlJwkProvider(new URL(authServerUrl));

    Jwk jwk = provider.get(jwt.getKeyId());

    if (jwk == null || jwk.getPublicKey() == null) {
      throw new InvalidKeySpecException("Public key with id " + jwt.getKeyId() + " not found");
    }

    return (RSAPublicKey) jwk.getPublicKey();

  }

  private RSAPublicKey loadPublicKeyJwksFile(DecodedJWT jwt) {
    return null;
  }

  private RSAPublicKey loadPublicKey(DecodedJWT jwt) throws InvalidKeySpecException, MalformedURLException, JwkException {

    if (jwt == null || jwt.getKeyId() == null) {
      throw new InvalidKeySpecException("Token invalid with key id null");
    }

    if (config.getPublicKeyProvider() == EPublicKeyProvider.PUBLIC_KEY_FILE) {
      return loadPublicKeyJwksFile(jwt);
    } else {
      return loadPublicKeyFromAuthorizationServer(jwt);
    }

  }

  private String getAuthorizationServerCertificateUrl(DecodedJWT jwt) {
    return jwt.getIssuer() + "/protocol/openid-connect/certs";
  }
  
}
