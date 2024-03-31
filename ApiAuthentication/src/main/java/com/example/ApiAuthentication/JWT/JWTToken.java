package com.example.ApiAuthentication.JWT;

import com.example.ApiAuthentication.Utility.Auth;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
public class JWTToken {

    public static String EMAIL = "ABHISHEKKR8799@GMAIL.COM";
    public static String NAME = "Abhishek Kumar";
    public static String SUBJECT = "subject";

    public static String SECRET_KEY = "HGHGDHGDHFGSD786JHhg7nbhfjhhjvjdfhbzdfsdgfhsgdjfvdshbgjxgbcgxc" ;


    public static String GenerateRSAJWTToken() throws NoSuchAlgorithmException {
        PrivateKey privateKey = null;
        try {
            privateKey = getPrivateKey();
        } catch (Exception ex) {
            System.out.println("Private Key error - " + ex.toString());
        }
        Instant currentTime = Instant.now();
        return Jwts.builder()
                .claim("name", NAME)
                .claim("email", EMAIL)
                .claim("subject", SUBJECT)
                .id(UUID.randomUUID().toString())
                .issuedAt(Date.from(currentTime))
                .expiration(Date.from(currentTime.plus(5l, ChronoUnit.MINUTES)))
                .signWith(privateKey)
                .compact();

    }
    public static String GenerateHMACJWTToken() throws NoSuchAlgorithmException {
        Instant currentTime = Instant.now() ;
        byte[] byteArr = Base64.getDecoder().decode(SECRET_KEY) ;
        Key hmacKey = new SecretKeySpec(byteArr, SignatureAlgorithm.HS256.getJcaName());

//        Key hmacKey = Keys.hmacShaKeyFor(byteArr) ; // here it will pick the default sign algo
        String jwtToken = Jwts.builder()
                .claim("name", NAME)
                .claim("email", EMAIL)
                .claim("subject", SUBJECT)
                .id(UUID.randomUUID().toString())
                .issuedAt(Date.from(currentTime))
                .expiration(Date.from(currentTime.plus(5l, ChronoUnit.MINUTES)))
                .signWith(hmacKey)
                .compact();

        return jwtToken ;
    }

    public static Jws<Claims> validateHMACToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token) ;

    }
    public static String GenerateJWTToken(Auth tokenType) throws NoSuchAlgorithmException {
        String token = null ;
        if( tokenType == Auth.RSA ){
            token = GenerateRSAJWTToken() ;
        }else{
            token =GenerateHMACJWTToken() ;
        }

        return token ;
    }

    public static Jws<Claims> validateJWTToken(String token, Auth tokenType) throws InvalidKeySpecException, NoSuchAlgorithmException {

        Jws<Claims> jws = null ;
        if( Auth.RSA == tokenType ){
            jws = validateRSAToken(token) ;
        }else if( tokenType == Auth.HMAC ){
            jws = validateHMACToken(token) ;
        }

        return jws ;
    }

    public static PrivateKey getPrivateKey() throws NoSuchAlgorithmException {
        PrivateKey privateKey = null ;
        String rsaPrivateKey = "-----BEGIN PRIVATE KEY-----" +
                "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDK7c0HtOvefMRM" +
                "s1tkdiJm+A16Df85lQlmXjQvMHNgY4P/znvl4kRON9DdBdo3K81OG7pR/0H9XvdB" +
                "TEojj/6vCVuMDeeIiBrgx0OJjhv0r8oUD4d52+1kXXITaniyZcbJ08s4sF7fUSCu" +
                "IZOhvvwQTd/tIwXGU1qqfg+bsomQ6h2czPSKXAux54vUiRO2IWf/Y6twyk8cy1PH" +
                "IOfelCVUJ4kzmP+CsOH7Rh3JMwZ0Mc4GAzndWpKwNXKjVM20/bKE9FgIiIjzmEQd" +
                "VpSdUz2MbAKM1kskdaHXQyuaHoHfPwESYuEwBld4vh9AGMF3jYMu8ggnAzVRIoWG" +
                "Mr5eCE2tAgMBAAECggEBAKBPXiKRdahMzlJ9elyRyrmnihX7Cr41k7hwAS+qSetC" +
                "kpu6RjykFCvqgjCpF+tvyf/DfdybF0mPBStrlkIj1iH29YBd16QPSZR7NkprnoAd" +
                "gzl3zyGgcRhRjfXyrajZKEJ281s0Ua5/i56kXdlwY/aJXrYabcxwOvbnIXNxhqWY" +
                "NSejZn75fcacSyvaueRO6NqxmCTBG2IO4FDc/xGzsyFKIOVYS+B4o/ktUOlU3Kbf" +
                "vwtz7U5GAh9mpFF+Dkr77Kv3i2aQUonja6is7X3JlA93dPu4JDWK8jrhgdZqY9p9" +
                "Q8odbKYUaBV8Z8CnNgz2zaNQinshzwOeGfFlsd6H7SECgYEA7ScsDCL7omoXj4lV" +
                "Mt9RkWp6wQ8WDu5M+OCDrcM1/lfyta2wf7+9hv7iDb+FwQnWO3W7eFngYUTwSw5x" +
                "YP2uvOL5qbe7YntKI4Q9gHgUd4XdRJJSIdcoY9/d1pavkYwOGk7KsUrmSeoJJ2Jg" +
                "54ypVzZlVRkcHjuwiiXKvHwj2+UCgYEA2w5YvWSujExREmue0BOXtypOPgxuolZY" +
                "pS5LnuAr4rvrZakE8I4sdYjh0yLZ6qXJHzVlxW3DhTqhcrhTLhd54YDogy2IT2ff" +
                "0GzAV0kX+nz+mRhw0/u+Yw6h0QuzH9Q04Wg3T/u/K9+rG335j/RU1Tnh7nxetfGb" +
                "EwJ1oOqcXikCgYEAqBAWmxM/mL3urH36ru6r842uKJr0WuhuDAGvz7iDzxesnSvV" +
                "5PKQ8dY3hN6xfzflZoXssUGgTc55K/e0SbP93UZNAAWA+i29QKY6n4x5lKp9QFch" +
                "dXHw4baIk8Z97Xt/kw07f6FAyijdC9ggLHf2miOmdEQzNQm/9mcJ4cFn+DECgYEA" +
                "gvOepQntNr3gsUxY0jcEOWE3COzRroZD0+tLFZ0ZXx/L5ygVZeD4PwMnTNrGvvmA" +
                "tAFt54pomdqk7Tm3sBQkrmQrm0+67w0/xQ9eJE/z37CdWtQ7jt4twHXc0mVWHa70" +
                "NdPhTRVIAWhil7rFWANOO3Gw2KrMy6O1erW7sAjQlZECgYBmjXWzgasT7JcHrP72" +
                "fqrEx4cg/jQFNlqODNb515tfXSBBoAFiaxWJK3Uh/60/I6cFL/Qoner4trNDWSNo" +
                "YENBqXLZnWGfIo0vAIgniJ6OD67+1hEQtbenhSfeE8Hou2BnFOTajUxmYgGm3+hx" +
                "h8TPOvfHATdiwIm7Qu76gHhpzQ==" +
                "-----END PRIVATE KEY-----";

        rsaPrivateKey = rsaPrivateKey.replace("-----BEGIN PRIVATE KEY-----", "") ;
        rsaPrivateKey = rsaPrivateKey.replace("-----END PRIVATE KEY-----", "") ;
        System.out.println(rsaPrivateKey);

        PKCS8EncodedKeySpec keySpecs = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(rsaPrivateKey)) ;
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            privateKey = kf.generatePrivate(keySpecs) ;
        }catch (Exception ex) {
            System.out.println("Exception caused" + ex.toString());
        }

        return privateKey ;
    }

    public static Jws<Claims> validateRSAToken(String token) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PublicKey publicKey = getPublicKey() ;


        Jws<Claims> jws = null ;
        try {
            jws = Jwts.parser()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
        return jws ;
    }

    public static PublicKey getPublicKey() {
        String rsaPublicKey = "-----BEGIN PUBLIC KEY-----" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyu3NB7Tr3nzETLNbZHYi" +
                "ZvgNeg3/OZUJZl40LzBzYGOD/8575eJETjfQ3QXaNyvNThu6Uf9B/V73QUxKI4/+" +
                "rwlbjA3niIga4MdDiY4b9K/KFA+HedvtZF1yE2p4smXGydPLOLBe31EgriGTob78" +
                "EE3f7SMFxlNaqn4Pm7KJkOodnMz0ilwLseeL1IkTtiFn/2OrcMpPHMtTxyDn3pQl" +
                "VCeJM5j/grDh+0YdyTMGdDHOBgM53VqSsDVyo1TNtP2yhPRYCIiI85hEHVaUnVM9" +
                "jGwCjNZLJHWh10Mrmh6B3z8BEmLhMAZXeL4fQBjBd42DLvIIJwM1USKFhjK+XghN" +
                "rQIDAQAB" +
                "-----END PUBLIC KEY-----";

        rsaPublicKey = rsaPublicKey.replace("-----BEGIN PUBLIC KEY-----", "") ;
        rsaPublicKey = rsaPublicKey.replace("-----END PUBLIC KEY-----", "") ;

        byte[] byteArr = Base64.getDecoder().decode(rsaPublicKey) ;

        X509EncodedKeySpec encodedKeySpecs = new X509EncodedKeySpec(byteArr) ;
        PublicKey publicKey = null ;
        try{
            KeyFactory kf = KeyFactory.getInstance("RSA") ;
            publicKey = kf.generatePublic(encodedKeySpecs) ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return publicKey ;
    }
}
