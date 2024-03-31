package com.example.ApiAuthentication.Controller;

import com.example.ApiAuthentication.JWT.JWTToken;
import com.example.ApiAuthentication.Utility.Auth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

@RestController
public class User {
    @GetMapping("/access")
    public String getUser(@RequestHeader Map<String, String> headers) throws InvalidKeySpecException, NoSuchAlgorithmException {
        // get the token bearer

        String bearerToken = headers.get("authorization") ;
        if( bearerToken == null ){
            return "Authentication is not provided" ;
        }
        bearerToken = bearerToken.substring(7) ;
        System.out.println("Token received" + bearerToken);

        Jws<Claims> jws = null ;
        try {
            jws = JWTToken.validateJWTToken(bearerToken, Auth.HMAC);
        } catch (Exception ex){
            System.out.println(ex.toString());
        }
        assert jws != null;
        if( jws == null ){
            return "Unauthorised Access" ;
        }
        System.out.println(jws.getBody());

        // Ready to apply your business logic.
        return "This is your data" ;

    }

    @PostMapping("/login")
    public String doLogin() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String jwtToken = null ;

        try {
//            jwtToken = JWTToken.GenerateJWTToken(Auth.RSA);
            jwtToken = JWTToken.GenerateJWTToken(Auth.HMAC);
        }catch (Exception ex) {
            System.out.println(ex.toString());
        }
        System.out.println("JWT Token - " + jwtToken);
        return jwtToken;
    }
}
