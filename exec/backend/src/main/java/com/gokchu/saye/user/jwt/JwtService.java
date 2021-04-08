package com.gokchu.saye.user.jwt;

import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.gokchu.saye.repository.dto.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtService {
	
	/* 암호화 설정을 위한 임의 문자열 변수 */
	private String signature = "SAYE";
	
	/* 로그인 성공 시, 사용자 정보를 기반으로 JWT Token 생성 및 반환 */
	public String create(User user) {
		System.out.println("토큰 생성");
		JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder.setHeaderParam("typ",  "JWT");	// 헤더 설정 => 토큰의 타입으로 고정 값 역할
		jwtBuilder.setSubject("로그인 토큰") 			// 해당 토큰의 제목 설정
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30));	// 해당 토큰의 유효기간 60분
//			.claim("user", user).claim("Welcome", user.getuName() + "님 반갑습니다.");	// 담고싶은 정보 claim으로 추가 가능
		
		jwtBuilder.signWith(SignatureAlgorithm.HS256, signature.getBytes());		// 토큰 암호화 처리
		
		String jwt = jwtBuilder.compact();	// 마지막 직렬화 처리
		return jwt;
	}
	
	/* 생성되어 전달받은 토큰 확인 처리 => 토큰 문제 발생 시, RuntimeException 처리된다 */
	public void checkValid(String jwt) {
		Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
	}
	
	/* JWT Token 분석해서 필요한 정보 반환하기 */
	public Map<String, Object> get(String jwt) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
		} catch(final Exception e) {
			throw new RuntimeException();
		}
		return claims.getBody();
	}

}
