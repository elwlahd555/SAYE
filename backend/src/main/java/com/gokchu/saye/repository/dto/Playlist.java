package com.gokchu.saye.repository.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Playlist {
	private int pNo;
	private int pUNo;
	private String pName;
	public Playlist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Playlist(int pNo, int pUNo, String pName) {
		super();
		this.pNo = pNo;
		this.pUNo = pUNo;
		this.pName = pName;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public int getpUNo() {
		return pUNo;
	}
	public void setpUNo(int pUNo) {
		this.pUNo = pUNo;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
}
