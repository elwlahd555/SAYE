package com.gokchu.saye.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gokchu.saye.repository.dto.User;
import com.gokchu.saye.repository.mapper.UserMapper;
import com.gokchu.saye.user.controller.SaltSHA256;

@Service
public class UserServiceImpl implements UserService {
	/* 유저 매퍼 객체 불러오기 */
	@Autowired
	private UserMapper userMapper;
	
	/* 로그인 */
	@Override
	public User login(User user) throws Exception {
		// 완전한 암호화 보안은 로그인 횟수까지 작업해야함 (나중에 시간 여유보고 작업할 것)
		System.out.println("로그인할 아이디 : " + user.getuId());
		String salt = userMapper.getUSaltByUId(user.getuId());
		System.out.println("출력 : " + salt);
		String password = user.getuPassword();
		
		password = SaltSHA256.getEncrypt(password, salt);
		user.setuPassword(password);
		
		User check = userMapper.selectUser(user);
		if(user.getuPassword().equals(check.getuPassword()))
			return check;
		else
			return null;		
	}
	
	/* C :: 회원 가입 */
	@Override
	public int insertUser(User user) throws Exception {
		
		// 1. 가입할 회원의 고유 salt 생성 및 저장
		String salt = SaltSHA256.generateSalt(); 
		user.setuSalt(salt); 
		
		// 2. 입력된 password + 생성된 salt 활용해서 암호화된 password 생성
		String password = user.getuPassword();
//		System.out.println("출력" + password);
		password = SaltSHA256.getEncrypt(password, salt);
		
		// 3. 입력된 비번 삽입
		user.setuPassword(password);
		
		// 4. 남은 유저 정보 삽입
		return userMapper.insertUser(user);		
	}
	
	/* R :: 회원 조회 */
	@Override
	public User selectUser(User user) throws Exception {
		return userMapper.selectUser(user);
	}	
	
	/* U :: 회원 수정 - 비밀번호 수정 */
	@Override
	public int updateUser(User user) throws Exception {
		String salt = SaltSHA256.generateSalt(); 
		user.setuSalt(salt); 
		
		String password = user.getuPassword();
		password = SaltSHA256.getEncrypt(password, salt);
		
		user.setuPassword(password);
		
		return userMapper.updateUserPassword(user);
	}	
	
	/* D :: 회원 삭제 */
	@Override
	public int deleteUser(String uId) throws Exception {
		return userMapper.deleteUser(uId);
	}	
	
	/* R :: 아이디 중복 체크 */
	@Override
	public int checkUId(String uId) throws Exception {
		return userMapper.checkUId(uId);
	}
	

	
	
	
	/* R :: 비밀번호 찾기 */
	@Override
	public int findPasswordByUId(String uId) throws Exception {
		
		String salt = userMapper.getUSaltByUId(uId);
		System.out.println("해당 고객 salt : " + salt);
		
		String tempPassword = "1234";
		tempPassword = SaltSHA256.getEncrypt(tempPassword, salt);
		
		return userMapper.selectPasswordByUId(uId, tempPassword);
	}

	/* 카카오 회원 가입 */
	@Override
	public int createKakaoUser(User user) throws Exception {
//		String rawPassword = user.getuPassword(); // 비밀번호 원문
//		String encPassword = encoder.encode(rawPassword); // 해쉬
//		user.setuPassword(rawPassword);

		return userMapper.insertKakaoUser(user);
	}	
	/* 카카오 로그인 */
	@Override
	public User kakaoLogin(User user) throws Exception {
		User check = userMapper.selectUser(user);
		if(user.getuPassword().equals(check.getuPassword()))
			return check;
		else
			return null;
	}	
	
	/* 로그인 시, 이메일로 맞는 salt 값 받아오기 */
	@Override
	public String getuSaltByUId(String getUId) throws Exception {
		return userMapper.getUSaltByUId(getUId);
	}
}
