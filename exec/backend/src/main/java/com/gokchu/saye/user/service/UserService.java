package com.gokchu.saye.user.service;

import com.gokchu.saye.repository.dto.User;

public interface UserService {

	/* 로그인 */
	User login(User user) throws Exception;

	/* C :: 회원 가입 */
	int insertUser(User user) throws Exception;

	/* R :: 회원 조회 */
	User selectUser(User user) throws Exception;

	/* U :: 회원 수정 - 비밀번호 수정 */
	int updateUser(User user) throws Exception;

	/* D :: 회원 삭제 */
	int deleteUser(String uId) throws Exception;

	/* R :: 아이디 중복 체크 */
	int checkUId(String uId) throws Exception;

	/* R :: 비밀번호 찾기 */
	int findPasswordByUId(String uId) throws Exception;

	/* 카카오 회원 가입 */
	int createKakaoUser(User user) throws Exception;

	/* 카카오 로그인 */
	User kakaoLogin(User user) throws Exception;

	/* 로그인 시, 이메일로 맞는 salt 값 받아오기 */
	String getuSaltByUId(String getUId) throws Exception;

}