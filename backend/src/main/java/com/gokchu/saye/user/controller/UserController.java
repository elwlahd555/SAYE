package com.gokchu.saye.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gokchu.saye.repository.dto.User;
import com.gokchu.saye.user.jwt.JwtService;
import com.gokchu.saye.user.service.UserService;

@RestController
@RequestMapping("/user") // FE랑 맞추기
public class UserController {

	/* JWT 사용을 위한 객체 불러오기 */
	@Autowired
	private JwtService jwtService;

	/* 유저서비스 객체 불러오기 */
	@Autowired
	private UserService userService;

	/* C :: 회원 추가(가입) */
	@PostMapping("/signup")
	public ResponseEntity<String> createUser(@RequestBody User user) throws Exception {
		System.out.println("가입할 아이디 : " + user.toString());

//		User u = new User();
//		u.setuId(user.get("uId"));
//		u.setuPassword(user.get("uPassword"));
//		u.setuImg(user.get("uImg"));
//		u.setuNickname(user.get("uNickname"));

		if (userService.insertUser(user) > 0) {
			return new ResponseEntity<String>(user.getuId() + "회원님 가입 성공", HttpStatus.OK);
		}
		return new ResponseEntity<String>("회원 가입 실패", HttpStatus.NO_CONTENT);
	}

	/* R :: 회원 상세 조회 */
	@GetMapping("/mypage")
	public ResponseEntity<Map<String, Object>> reviewUser(HttpServletRequest request) {
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();

		try {
			resultMap.putAll(jwtService.get(request.getHeader("auth-token")));
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			resultMap.put("errMessage", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	/* U :: 회원 수정 - 비밀번호 수정 */
	@PutMapping("/mypage/edit")
	public ResponseEntity<String> editUser(@RequestBody User user) throws Exception {
		System.out.println(user.getuId() + "회원님 정보 수정하기");
		if (userService.updateUser(user) > 0) {
			return new ResponseEntity<String>("회원 정보 수정 성공", HttpStatus.OK);
		}
		return new ResponseEntity<String>("회원 정보 수정 실패", HttpStatus.NO_CONTENT);
	}

	/* D :: 회원 삭제(탈퇴) */
	@DeleteMapping("/mypage/delete")
	public ResponseEntity<String> deleteUser(String uId) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		if (userService.deleteUser(uId) > 0) {
			return new ResponseEntity<String>(uId + "회원님이 탈퇴되었습니다.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("회원 탈퇴 실패", HttpStatus.NO_CONTENT);
	}

	/* R :: 아이디 중복 체크 */
	@GetMapping("/checkid")
	public ResponseEntity<String> checkId(String uId) throws Exception {
		System.out.println("여까지는 옴");
		System.out.println(userService.checkUId(uId));
		if (userService.checkUId(uId) == 0) {
			return new ResponseEntity<String>("중복아님", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("중복", HttpStatus.OK);
		}
	}
	
	/* R :: 비밀번호 찾기 */
	@GetMapping("/find/password")
	public ResponseEntity<String> findPassword(String uId) throws Exception {
		System.out.println(uId + "회원님의 비밀번호 찾기");
		if (userService.findPasswordByUId(uId) > 0) {
			return new ResponseEntity<String>("임시비밀번호 발급 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("비밀번호 찾기 실패", HttpStatus.NO_CONTENT);
		}
	}
}

/* C :: 회원 가입 */
/* R :: 회원 조회 */
/* U :: 회원 수정 */
/* D :: 회원 삭제 */
/* R :: 아이디 중복 체크 */
/* R :: 비밀번호 찾기 */
