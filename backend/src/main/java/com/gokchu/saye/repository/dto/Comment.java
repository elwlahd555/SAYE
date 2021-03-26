package com.gokchu.saye.repository.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment {
	private int cNo;
	private int cBNo;
	private String cWriter;
	private String cContent;
	private String cDate;
	private int cUNo;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(int cNo, int cBNo, String cWriter, String cContent, String cDate, int cUNo) {
		super();
		this.cNo = cNo;
		this.cBNo = cBNo;
		this.cWriter = cWriter;
		this.cContent = cContent;
		this.cDate = cDate;
		this.cUNo = cUNo;
	}
	public int getcNo() {
		return cNo;
	}
	public void setcNo(int cNo) {
		this.cNo = cNo;
	}
	public int getcBNo() {
		return cBNo;
	}
	public void setcBNo(int cBNo) {
		this.cBNo = cBNo;
	}
	public String getcWriter() {
		return cWriter;
	}
	public void setcWriter(String cWriter) {
		this.cWriter = cWriter;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public String getcDate() {
		return cDate;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	public int getcUNo() {
		return cUNo;
	}
	public void setcUNo(int cUNo) {
		this.cUNo = cUNo;
	}
}
