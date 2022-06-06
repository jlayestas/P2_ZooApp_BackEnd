package com.zoo.service;

import java.io.IOException;
import java.security.Key;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoo.models.User;
import com.zoo.models.UserJwtDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;

public class JwtService {

private Key key;
	
	public JwtService() {
		// You will need a SECRET KEY to create the token, I would recommend to store
		// it inside a .env file. For this example though, I will store it a variable.
		byte[] secret = "my_secret_passwordafdsalkj;lkvjasd;lkfoijeowiru324u02938098134lkhj;ldjfa;sldkjfDSFSLDKJFLSKJG".getBytes();
        key = Keys.hmacShaKeyFor(secret);
	}
	
	public String createJwt(User user) throws InvalidKeyException, JsonProcessingException {
		//DTO = data transfer object
		//this POJO serves as an object that carries data between processes
		//in this case we are using this DTO to convert our java objects in 
		//a more string-formatted object that can be easily converted by the ObjectMapper
		//1. turn user into userJwtDTO
		UserJwtDTO dto = new UserJwtDTO(user.getUserId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserRole());
		
		//2. create a jwt with our dto
		String jwt = Jwts.builder()
                .claim("user_dto", new ObjectMapper().writeValueAsString(dto))
                .signWith(key)
                .compact();
		//3. return the new jwt
		return jwt;
	}
	
	public UserJwtDTO parseJwt(String jwt) throws IOException, JsonProcessingException{
		//1. Generate the token using claims from the jwt and our secret key
		//claims = pieces of information asserted about a subject from a token
		Jws<Claims> token = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);

		//2. parse the token into a string
        String dtoString = (String) token.getBody().get("user_dto");

        //3. send the data to the client as json
        return (new ObjectMapper()).readValue(dtoString, UserJwtDTO.class);
	}
}
