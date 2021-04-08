package com.gokchu.saye.repository.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uNo;
	private String uId;
	private String uPassword;
	private String uNickname;
	private String uImg;
	private String uJoindate;
	private String uProvider;
	private String uAuthority;
	private String uSalt;
	private String uJwt;
	public int getuNo() {
		return uNo;
	}
	public void setuNo(int uNo) {
		this.uNo = uNo;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public String getuNickname() {
		return uNickname;
	}
	public void setuNickname(String uNickname) {
		this.uNickname = uNickname;
	}
	public String getuImg() {
		return uImg;
	}
	public void setuImg(String uImg) {
		this.uImg = uImg;
	}
	public String getuJoindate() {
		return uJoindate;
	}
	public void setuJoindate(String uJoindate) {
		this.uJoindate = uJoindate;
	}
	public String getuProvider() {
		return uProvider;
	}
	public void setuProvider(String uProvider) {
		this.uProvider = uProvider;
	}
	public String getuAuthority() {
		return uAuthority;
	}
	public void setuAuthority(String uAuthority) {
		this.uAuthority = uAuthority;
	}
	public String getuSalt() {
		return uSalt;
	}
	public void setuSalt(String uSalt) {
		this.uSalt = uSalt;
	}
	public String getuJwt() {
		return uJwt;
	}
	public void setuJwt(String uJwt) {
		this.uJwt = uJwt;
	}
	

}
