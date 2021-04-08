package com.gokchu.saye.repository.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlaylistMusic {
	private int pmPNo;
	private int pmMNo;
	public PlaylistMusic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlaylistMusic(int pmPNo, int pmMNo) {
		super();
		this.pmPNo = pmPNo;
		this.pmMNo = pmMNo;
	}
	public int getPmPNo() {
		return pmPNo;
	}
	public void setPmPNo(int pmPNo) {
		this.pmPNo = pmPNo;
	}
	public int getPmMNo() {
		return pmMNo;
	}
	public void setPmMNo(int pmMNo) {
		this.pmMNo = pmMNo;
	}
}
