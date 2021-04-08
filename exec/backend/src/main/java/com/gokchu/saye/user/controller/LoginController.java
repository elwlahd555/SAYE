package com.gokchu.saye.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gokchu.saye.repository.dto.User;
import com.gokchu.saye.user.jwt.JwtService;
import com.gokchu.saye.user.service.UserService;

@RestController
public class LoginController {

	/* jwt 객체 불러오기 */
	@Autowired
	private JwtService jwtService;
	
	/* 유저 서비스 객체 불러오기 */
	@Autowired
	private UserService userService;
	
	/* 로그인 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response, HttpSession session) throws Exception {
		
		System.out.println(user+"유저정보"+user.getuId()+user.getuPassword());
		Map<String, Object> resultMap = new HashMap<>();
		User check = userService.login(user);
		if(check != null) {
			String token = jwtService.create(check);
			
			// 파라미터 1번째 것은 FE 대로 따라가기..
			resultMap.put("auth-token", token);
			resultMap.put("uNo", check.getuNo());
			resultMap.put("uId", check.getuId()); 
			resultMap.put("uPassword", check.getuPassword());
			resultMap.put("uNickname", check.getuNickname());
			resultMap.put("uImg", check.getuImg());
			resultMap.put("uJoinDate", check.getuJoindate());
			resultMap.put("uProvider", check.getuProvider());
			resultMap.put("uSalt", check.getuSalt());
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		}
		resultMap.put("message", "로그인에 실패하였습니다.");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/auth/kakao/callback")
	public ResponseEntity<?> kakaoCallback(@RequestBody User user, HttpServletResponse response, HttpSession session) throws Exception { // Data를 return 해주는 controller method
		User kakaoUser = new User();
		System.out.println(user.toString());
		kakaoUser.setuId(user.getuId());
		kakaoUser.setuPassword(user.getuPassword());
		kakaoUser.setuNickname(user.getuNickname());
		kakaoUser.setuProvider("kakao");
		
		// 가입자 혹은 비가입자 체크해서 처리
		try {
			User originUser = userService.selectUser(kakaoUser);
			if(originUser == null) {
				userService.createKakaoUser(kakaoUser);
				System.out.println("카카오 아이디로 회원가입 성공");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		User toLogin = userService.selectUser(user);
		
		Map<String, Object> resultMap = new HashMap<>();
		User check = userService.kakaoLogin(toLogin);
		if(check != null) {
			String token = jwtService.create(check);
			
			resultMap.put("auth-token", token);
			resultMap.put("uNo", check.getuNo());
			resultMap.put("uId", check.getuId()); 
			resultMap.put("uPassword", check.getuPassword());
			resultMap.put("uNickname", check.getuNickname());
			resultMap.put("uImg", check.getuImg());
			resultMap.put("uJoinDate", check.getuJoindate());
			resultMap.put("uProvider", check.getuProvider());
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		}
		resultMap.put("message", "로그인에 실패하였습니다.");
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.NO_CONTENT);
	}
	
	
	// Jython Test용. 이거 보고 참고해서 python파일 연결해서 쓰면 됩니다.
	private static PythonInterpreter intPre;

	@GetMapping("/testJython")
	public String getTest() {
		intPre = new PythonInterpreter();
		intPre.execfile("src/main/java/com/gokchu/saye/test/test.py");
		intPre.exec("print(testFunc(5,10))");

		PyFunction pyFuntion = (PyFunction) intPre.get("testFunc", PyFunction.class);
		int a = 10, b = 20;
		PyObject pyobj = pyFuntion.__call__(new PyInteger(a), new PyInteger(b));
		System.out.println(pyobj.toString());

		return pyobj.toString();
	}
}
