package com.gokchu.saye.repository.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Board {
	private int bNo;
	private String bClass;
	private String bTitle;
	private String bContent;
	private String bWriter;
	private String bDate;
	private int bUNo;
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int bNo, String bClass, String bTitle, String bContent, String bWriter, String bDate, int bUNo) {
		super();
		this.bNo = bNo;
		this.bClass = bClass;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bWriter = bWriter;
		this.bDate = bDate;
		this.bUNo = bUNo;
	}
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public String getbClass() {
		return bClass;
	}
	public void setbClass(String bClass) {
		this.bClass = bClass;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public String getbWriter() {
		return bWriter;
	}
	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}
	public String getbDate() {
		return bDate;
	}
	public void setbDate(String bDate) {
		this.bDate = bDate;
	}
	public int getbUNo() {
		return bUNo;
	}
	public void setbUNo(int bUNo) {
		this.bUNo = bUNo;
	}
}
