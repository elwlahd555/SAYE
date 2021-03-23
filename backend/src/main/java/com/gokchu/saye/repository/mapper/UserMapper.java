package com.gokchu.saye.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gokchu.saye.repository.dto.User;

@Mapper
public interface UserMapper {
	
	/* 로그인 */
	public User selectUserLogin(User user) throws Exception;
	
	/* C :: 회원 가입 */
	public int insertUser(User user) throws Exception;
	public int insertKakaoUser(User user) throws Exception;
	
	/* R :: 회원 조회 */
	public User selectUser(User user) throws Exception;
	
	/* U :: 회원 수정 - 비밀번호 변경 */
	public int updateUserPassword(User user) throws Exception;
	
	/* D :: 회원 삭제 */
	public int deleteUser(String uId) throws Exception;
	
	/* R :: 아이디 중복 체크 */
	public int checkUId(String uId) throws Exception;
	
	/* R :: 비밀번호 찾기 */	
	public int selectPasswordByUId(String uId, String tempPassword) throws Exception;
	
	/* 로그인 시, 아이디로 접근하여 해당 아이디의 salt 가져오기 */
	public String getUSaltByUId(String uId) throws Exception;
}
