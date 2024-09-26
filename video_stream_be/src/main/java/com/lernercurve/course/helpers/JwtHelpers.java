package com.lernercurve.course.helpers;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.lernercurve.course.entity.VideoMetadata;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.JwtSignatureValidator;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtHelpers {
	 private static final long JWT_TOKEN_VALIDITY=24*60*60*1000;
		
		private static final String SECRET_KEY="689ce06cc5023e385524edad4fc939b6f3e99a1eb6ce09646c2c1f22174c8992dab259e5df4660bf618efa280ba31d0ee1816e127a00c63520a897b0c461c3a3775b399332666f7f\r\n"
				+ "d9daf16a252f6fd19e33d57167c163d72766b71f704edc6109fc88b56ce94d17a491cc313b5b0ca1054b8809f7baa0960a4678aec4bc75a0d39edac4d9356b894a55567f42da65bc\r\n"
				+ "fb535dc3cc8b721578fa30740a96ca63ba6bd9f8a53d88a8d55014b05caf2b806b92f05cda8fb18a8bdb30211fd5f3dd8d76a67b78eadeb44f8f7d32a3e4c96ec41622932b2f490c\r\n"
				+ "ff788cf64a4244c45adefe40b5d11b440c8830c1e32d1115a7001d793d7ed9ef5aab058e969dde52cfde2be233452168a0bea986f3ee4489dfe65def7d56baf610168e143461aebe\r\n"
				+ "ddaf6d7e1ea0f6b21e421ce8507eceae4fbe663dfa398c70ca47d1913152b86c4728c16e772cfcded851d4b9529b0fb1201830c4b2ae6815ba00fec45a75cdc9696a8603638d3a9f\r\n"
				+ "e607586afef467e9abf278ab40cc6f3fd581dd077e91ae30f99d5129eb8620ec9125db575dec824112b8f1074bd73a4746d86ef3d1cd4eab22513c5032d0fe1d378b8d96ef6eb481\r\n"
				+ "6e36ca5db6398b94cacdb46b6a72b7ffba6b79a437310ee966fb8e82a292a56c24b433cd59f2a8eeba3e14a833b7a18bcb3001081a9fb0462025e8d25fae825e53fd2551c02d62c0\r\n"
				+ "05ccd3fae2255a354b6485b8248fe3cf8398c3cf21f49f85e6689d0578e74254637b564c2553563e2a9f69a5b7655d75c3b2ea02f08b9a74efffbc3f2d9a4aaf230b3c0efac3c62d\r\n"
				+ "cd85dc9936611624277970d76593129c7bf9c25974d8cfe5a7dd524ce365d5fbe90f6185d5b6b716c9076b96428a083689a6328d2f2f13f72c6e917592d5d2e8dc8c5535f78ddfc9\r\n"
				+ "8f0cc05c6e77980d67c90830248fbe3bb61c8fc7f90227d4b04af0b54843e219bfdb55141756ecf56ba966fbe7e724e21db37abb84ae426b2a47c1952edde3923e34277cf0bf68bc\r\n"
				+ "4598dd338c53fb697d20e85ab92ba7eec1c6d7f5b334ac3184cfa5e8544377997a37aafd9e1c82d3820dddb3f1e6ba4964146da47a724f39609cab1a4064c51c62b6f887137cc1f3\r\n"
				+ "8aceb7c77b66071ac2e44d390c6a356d0e516620e53d348a0f237c4c204aa740d7e01d68da69f0b2c090057b899ec24b6bcdd175265257b9003da534f68bf413b53ddba904b846fe\r\n"
				+ "30d16f5c59d357550e4b70bf326a132e178961c788a77e73896ef0e96c233bfa756e6d4cd1cbc52c529f5fc725f18975095afaf3d070ec02d04a2a2a00a3e00f6d0636f367009561\r\n"
				+ "badaabd8b7a9ae0e7d41cdb551d4053f1ef81d3d3fac827d3da557cb0c83841e076be241c2e738058afef72e42d7dc36df6da34dbd289fa27a596b35c64b24ee6b8345288485c6fb\r\n"
				+ "44479d72592c9f942db5f94a503efef3";
		

		//retrieve username from jwt token
			public String getUsernameFromToken(String token) {
				return getClaimFromToken(token, Claims::getSubject);
			}
			//retrieve expiration date from jwt token
			public Date getExpirationDateFromToken(String token) {
				return getClaimFromToken(token, Claims::getExpiration);
			}
			public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
				final Claims claims = getAllClaimsFromToken(token);
				return claimsResolver.apply(claims);
			}
		    //for retrieveing any information from token we will need the secret key
			private Claims getAllClaimsFromToken(String token) {
				return Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token).getBody();
			}
			//check if the token has expired
			private Boolean isTokenExpired(String token) {
				final Date expiration = getExpirationDateFromToken(token);
				return expiration.before(new Date());
			}
			//generate token for user
			public String generateToken(VideoMetadata finoFserDetails) {
				Map<String, Object> claims = new HashMap<>();
//				claims.put("role",finoFserDetails.getAuthorities());
				return doGenerateToken(claims, finoFserDetails.getDescription());
			}
			//while creating the token -
			//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
			//2. Sign the JWT using the HS512 algorithm and secret key.
			//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
			//   compaction of the JWT to a URL-safe string 
			private String doGenerateToken(Map<String, Object> claims, String subject) {
				return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
						.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()),SignatureAlgorithm.HS256).compact();
			}
			//validate token
			public Boolean validateToken(String token, VideoMetadata finoFserDetails) {
				final String username = getUsernameFromToken(token);
				return (username.equals(finoFserDetails.getDescription()) && !isTokenExpired(token));
			}
			
			public boolean inValidateJwtToken(String token) {
				var isTokenInvaliadted=false;
				try {
					Claims allClaimsFromToken = getAllClaimsFromToken(token);
					
				} catch (Exception e) {
					log.info("Unable to invaliadate jwt token:- {}",e.getMessage());
				}
				
				
				return isTokenInvaliadted;
			}
		
}
