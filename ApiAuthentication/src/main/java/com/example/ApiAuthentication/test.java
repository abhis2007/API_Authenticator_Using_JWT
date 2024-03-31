//package com.example.ApiAuthentication;
//
//
//import java.security.*;
//import java.security.spec.PKCS8EncodedKeySpec;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RestController;
//import io.jsonwebtoken.Jwts;
//
//
//import javax.crypto.spec.SecretKeySpec;
//import java.security.spec.InvalidKeySpecException;
//import java.sql.SQLOutput;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.Base64;
//import java.util.Date;
//import java.util.Map;
//import java.util.UUID;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//
//import java.security.NoSuchAlgorithmException;
//import java.security.PublicKey;
//import java.security.spec.InvalidKeySpecException;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Base64;
//
//@RestController
//public class test {
//    @Value("${security.jwt.secret-key}")
//    private static String secretKey = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4" ;
////    private static String secretKey = "hgggggggggghhghhggjhgjgh7676578ghvghgggggggggghhghhggjhgjgh7676578ghvggfhgfgh" ;
//
//    private static final String SECRET_KEY = "your-jhghjghjgfhghfhgf76587hv67bh-key";
//    private static final long EXPIRATION_TIME_MS = 86400000; // 24 hours
//
//    @PostMapping("/login")
//
//    public String getUser(@RequestHeader Map<String, String> headers) {
//        System.out.println(headers.get("authorization"));
//        //return buildToken() ;
//
//        return createJwtSignedRSA() ;
//    }
//
//    @GetMapping("/access")
//    public Jws<Claims> getAccess(@RequestHeader Map<String, String> headers){
//        String token = headers.get("authorization") ;
//        String PrivateKey = secretKey ;
//
//        //Jws<Claims> t = parseJwt(token) ;
//        Jws<Claims> t  = null ;
//        try{
//            parseRSAJwt(token) ;
//        }catch (Exception ex){
//            System.out.println(ex.toString());
//        }
//        return t ;
//
////        if( validateToken(token) ){
////            System.out.println("Token is proper");
////            return "Invalid request" ;
////        }
////        return "Authenticated ....." ;
//    }
//
//    public static boolean validateToken(String token) {
//        SignatureAlgorithm sa = SignatureAlgorithm.HS256;
//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), sa.getJcaName());
//
//        JwtParser jwtParser = Jwts.parser()
//                .verifyWith(secretKeySpec)
//                .build();
//        try {
//             jwtParser.parse(token);
//
//        } catch (Exception e) {
//            //throw new Exception("Could not verify JWT token integrity!", e);
//            return false ;
//        }
//
//        return true ;
//    }
//
//
//
//    private String buildToken() {
//        Map<String, Object> extraClaims =  null;
//        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS);
//        byte[] apiKeySecretBytes = SECRET_KEY.getBytes();
//       // Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
//
//        String token = Jwts
//                .builder()
//                .setSubject("userDetails.getUsername()")
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS256, getSignInKey())
//                .compact();
//
//        System.out.println("generated token = " + token);
//        return token ;
///*
//        String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
//
//        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
//                SignatureAlgorithm.HS256.getJcaName());
//
//        Instant now = Instant.now();
//        String jwtToken = Jwts.builder()
//                .claim("name", "Jane Doe")
//                .claim("email", "jane@example.com")
//                .setSubject("jane")
//                .setId(UUID.randomUUID().toString())
//                .setIssuedAt(Date.from(now))
//                .setExpiration(Date.from(now.plus(5l, ChronoUnit.MINUTES)))
//                .signWith(hmacKey)
//                .compact();
//        System.out.println(jwtToken);
//        return jwtToken ;*/
//    }
//
//    public static Key getSignInKey() {
////        byte[] keyBytes = new byte[32]; // 256 bits
////        SecureRandom secureRandom = new SecureRandom();
////        secureRandom.nextBytes(keyBytes);
//
//        // Encode the byte array to Base64 to get a string representation
//        //String secretKey = Base64.getEncoder().encodeToString(keyBytes);
//
//
//        //byte[] keyBytes = Decoders.BASE64.decode(secretKey);
////        byte[] apiKeySecretBytes = SECRET_KEY.getBytes();
////      Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
//        //secretKey = "hgggggggggghhghhggjhgjgh7676578ghvghgggggggggghhghhggjhgjgh7676578ghvggfhgfgh" ;
//        System.out.println("secret key = " + secretKey);
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        return Keys.hmacShaKeyFor(keyBytes);
////        return signingKey ;
//    }
//    public static Jws<Claims> parseJwt(String jwtString) {
//        String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
//        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
//                SignatureAlgorithm.HS256.getJcaName());
//        Jws<Claims> jwt = null;
//        try {
//            jwt = Jwts.parser()
//                    .setSigningKey(hmacKey)
//                    .build()
//                    .parseClaimsJws(jwtString);
//        }
//        catch(Exception ex) {
//            System.out.println(ex.toString());
//        }
//        return jwt;
//    }
//
//
//
//    public Jws<Claims> parseRSAJwt(String jwtString) throws InvalidKeySpecException, NoSuchAlgorithmException {
//
//        PublicKey publicKey = null ;
//        try {
//            getPublicKey();
//        }catch (Exception ex){
//            System.out.println(ex.toString());
//        }
//
//        Jws<Claims> jwt = io.jsonwebtoken.Jwts.parser()
//                .setSigningKey(publicKey)
//                .build()
//                .parseClaimsJws(jwtString);
//
//        return jwt;
//    }
//
//    private static PublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
//        String rsaPublicKey = "-----BEGIN PUBLIC KEY-----" +
//                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyu3NB7Tr3nzETLNbZHYi" +
//                "ZvgNeg3/OZUJZl40LzBzYGOD/8575eJETjfQ3QXaNyvNThu6Uf9B/V73QUxKI4/+" +
//                "rwlbjA3niIga4MdDiY4b9K/KFA+HedvtZF1yE2p4smXGydPLOLBe31EgriGTob78" +
//                "EE3f7SMFxlNaqn4Pm7KJkOodnMz0ilwLseeL1IkTtiFn/2OrcMpPHMtTxyDn3pQl" +
//                "VCeJM5j/grDh+0YdyTMGdDHOBgM53VqSsDVyo1TNtP2yhPRYCIiI85hEHVaUnVM9" +
//                "jGwCjNZLJHWh10Mrmh6B3z8BEmLhMAZXeL4fQBjBd42DLvIIJwM1USKFhjK+XghN" +
//                "rQIDAQAB" +
//                "-----END PUBLIC KEY-----";
//        rsaPublicKey = rsaPublicKey.replace("-----BEGIN PUBLIC KEY-----", "");
//        rsaPublicKey = rsaPublicKey.replace("-----END PUBLIC KEY-----", "");
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(rsaPublicKey));
//        KeyFactory kf = KeyFactory.getInstance("RSA");
//        PublicKey publicKey = kf.generatePublic(keySpec);
//        return publicKey;
//    }
//
//    public String createJwtSignedRSA()  {
//
//        PrivateKey privateKey = null ;
//        try{
//            privateKey = getPrivateKey();
//        }catch( Exception ex){
//            System.out.println(ex.toString());
//        }
//
//        Instant now = Instant.now();
//        String jwtToken = Jwts.builder()
//                .claim("name", "Jane Doe")
//                .claim("email", "jane@example.com")
//                .setSubject("jane")
//                .setId(UUID.randomUUID().toString())
//                .setIssuedAt(Date.from(now))
//                .setExpiration(Date.from(now.plus(5l, ChronoUnit.MINUTES)))
//                .signWith(privateKey)
//                .compact();
//
//        System.out.println("Token  " + jwtToken);
//        return jwtToken;
//    }
//
//    private static PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
//        String rsaPrivateKey = "-----BEGIN PRIVATE KEY-----" +
//                "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDK7c0HtOvefMRM" +
//                "s1tkdiJm+A16Df85lQlmXjQvMHNgY4P/znvl4kRON9DdBdo3K81OG7pR/0H9XvdB" +
//                "TEojj/6vCVuMDeeIiBrgx0OJjhv0r8oUD4d52+1kXXITaniyZcbJ08s4sF7fUSCu" +
//                "IZOhvvwQTd/tIwXGU1qqfg+bsomQ6h2czPSKXAux54vUiRO2IWf/Y6twyk8cy1PH" +
//                "IOfelCVUJ4kzmP+CsOH7Rh3JMwZ0Mc4GAzndWpKwNXKjVM20/bKE9FgIiIjzmEQd" +
//                "VpSdUz2MbAKM1kskdaHXQyuaHoHfPwESYuEwBld4vh9AGMF3jYMu8ggnAzVRIoWG" +
//                "Mr5eCE2tAgMBAAECggEBAKBPXiKRdahMzlJ9elyRyrmnihX7Cr41k7hwAS+qSetC" +
//                "kpu6RjykFCvqgjCpF+tvyf/DfdybF0mPBStrlkIj1iH29YBd16QPSZR7NkprnoAd" +
//                "gzl3zyGgcRhRjfXyrajZKEJ281s0Ua5/i56kXdlwY/aJXrYabcxwOvbnIXNxhqWY" +
//                "NSejZn75fcacSyvaueRO6NqxmCTBG2IO4FDc/xGzsyFKIOVYS+B4o/ktUOlU3Kbf" +
//                "vwtz7U5GAh9mpFF+Dkr77Kv3i2aQUonja6is7X3JlA93dPu4JDWK8jrhgdZqY9p9" +
//                "Q8odbKYUaBV8Z8CnNgz2zaNQinshzwOeGfFlsd6H7SECgYEA7ScsDCL7omoXj4lV" +
//                "Mt9RkWp6wQ8WDu5M+OCDrcM1/lfyta2wf7+9hv7iDb+FwQnWO3W7eFngYUTwSw5x" +
//                "YP2uvOL5qbe7YntKI4Q9gHgUd4XdRJJSIdcoY9/d1pavkYwOGk7KsUrmSeoJJ2Jg" +
//                "54ypVzZlVRkcHjuwiiXKvHwj2+UCgYEA2w5YvWSujExREmue0BOXtypOPgxuolZY" +
//                "pS5LnuAr4rvrZakE8I4sdYjh0yLZ6qXJHzVlxW3DhTqhcrhTLhd54YDogy2IT2ff" +
//                "0GzAV0kX+nz+mRhw0/u+Yw6h0QuzH9Q04Wg3T/u/K9+rG335j/RU1Tnh7nxetfGb" +
//                "EwJ1oOqcXikCgYEAqBAWmxM/mL3urH36ru6r842uKJr0WuhuDAGvz7iDzxesnSvV" +
//                "5PKQ8dY3hN6xfzflZoXssUGgTc55K/e0SbP93UZNAAWA+i29QKY6n4x5lKp9QFch" +
//                "dXHw4baIk8Z97Xt/kw07f6FAyijdC9ggLHf2miOmdEQzNQm/9mcJ4cFn+DECgYEA" +
//                "gvOepQntNr3gsUxY0jcEOWE3COzRroZD0+tLFZ0ZXx/L5ygVZeD4PwMnTNrGvvmA" +
//                "tAFt54pomdqk7Tm3sBQkrmQrm0+67w0/xQ9eJE/z37CdWtQ7jt4twHXc0mVWHa70" +
//                "NdPhTRVIAWhil7rFWANOO3Gw2KrMy6O1erW7sAjQlZECgYBmjXWzgasT7JcHrP72" +
//                "fqrEx4cg/jQFNlqODNb515tfXSBBoAFiaxWJK3Uh/60/I6cFL/Qoner4trNDWSNo" +
//                "YENBqXLZnWGfIo0vAIgniJ6OD67+1hEQtbenhSfeE8Hou2BnFOTajUxmYgGm3+hx" +
//                "h8TPOvfHATdiwIm7Qu76gHhpzQ==" +
//                "-----END PRIVATE KEY-----";
//
//        rsaPrivateKey = rsaPrivateKey.replace("-----BEGIN PRIVATE KEY-----", "");
//        rsaPrivateKey = rsaPrivateKey.replace("-----END PRIVATE KEY-----", "");
//
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(rsaPrivateKey));
//        KeyFactory kf = KeyFactory.getInstance("RSA");
//        PrivateKey privKey = kf.generatePrivate(keySpec);
//        return privKey;
//    }
//
//}
