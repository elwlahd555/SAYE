package com.gokchu.saye.repository.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LikeMusic {
	private int lmUNo;
	private int lmMNo;
	public LikeMusic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LikeMusic(int lmUNo, int lmMNo) {
		super();
		this.lmUNo = lmUNo;
		this.lmMNo = lmMNo;
	}
	public int getLmUNo() {
		return lmUNo;
	}
	public void setLmUNo(int lmUNo) {
		this.lmUNo = lmUNo;
	}
	public int getLmMNo() {
		return lmMNo;
	}
	public void setLmMNo(int lmMNo) {
		this.lmMNo = lmMNo;
	}
}
