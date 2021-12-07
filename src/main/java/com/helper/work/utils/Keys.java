package com.helper.work.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Date;

public interface Keys {


    static String generateWithGoogle() throws IOException {
        GoogleCredential credential =
            GoogleCredential.fromStream(new FileInputStream("credentials.json"));
        PrivateKey privateKey = credential.getServiceAccountPrivateKey();
        String privateKeyId = credential.getServiceAccountPrivateKeyId();

        long now = System.currentTimeMillis();

        try {
            Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) privateKey);
            return JWT.create()
                .withKeyId(privateKeyId)
                .withIssuer(credential.getServiceAccountId())
                .withSubject(credential.getServiceAccountId())
//                .withAudience("https://accounts.google.com/o/oauth2/auth")
                .withAudience(credential.getTokenServerEncodedUrl())
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(now + 3600 * 1000L))
                .sign(algorithm);
        } catch (IllegalArgumentException | JWTCreationException e) {
            e.printStackTrace();
        }
        return "Not generated";
    }
}
