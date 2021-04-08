package com.gokchu.saye.user.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gokchu.saye.user.jwt.JwtService;

@Component
public class JwtInterceptor implements HandlerInterceptor {
	
	/* JWT 서비스 객체 불러오기 */
	@Autowired
	private JwtService jwtService;
	
	/* 인터셉터 메소드 실행. 요청의 토큰값 체킹(서비스의 check메소드 활용) */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("토큰생성에 들어옴");
		// TODO Auto-generated method stub
		if (request.getMethod().contentEquals("OPTIONS")) {
			return true;
		}
		else {
			String token = request.getHeader("auth-token");
			if (token != null && token.length() > 0) {
				jwtService.checkValid(token);
				return true;
			}
			else {
				throw new RuntimeException("인증 토큰이 만료되었습니다.");
			}
		}
	}
}
