package com.gokchu.saye.repository.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Music {
	
	private int mNo;
	private String mTitle;
	private String mGenre;
	private String mArtist;
	private String mAlbum;
	private String mPreview;
	private String mImg;
	private String mPopularity;
	private String mId;
	private String mAId;
	private String mAlId;
	private String mCnt;
	
	
	
	public int getmNo() {
		return mNo;
	}
	public void setmNo(int mNo) {
		this.mNo = mNo;
	}
	public String getmTitle() {
		return mTitle;
	}
	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	public String getmGenre() {
		return mGenre;
	}
	public void setmGenre(String mGenre) {
		this.mGenre = mGenre;
	}
	public String getmArtist() {
		return mArtist;
	}
	public void setmArtist(String mArtist) {
		this.mArtist = mArtist;
	}
	public String getmAlbum() {
		return mAlbum;
	}
	public void setmAlbum(String mAlbum) {
		this.mAlbum = mAlbum;
	}
	public String getmPreview() {
		return mPreview;
	}
	public void setmPreview(String mPreview) {
		this.mPreview = mPreview;
	}
	public String getmImg() {
		return mImg;
	}
	public void setmImg(String mImg) {
		this.mImg = mImg;
	}
	public String getmPopularity() {
		return mPopularity;
	}
	public void setmPopularity(String mPopularity) {
		this.mPopularity = mPopularity;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmAId() {
		return mAId;
	}
	public void setmAId(String mAId) {
		this.mAId = mAId;
	}
	public String getmAlId() {
		return mAlId;
	}
	public void setmAlId(String mAlId) {
		this.mAlId = mAlId;
	}
	public String getmCnt() {
		return mCnt;
	}
	public void setmCnt(String mCnt) {
		this.mCnt = mCnt;
	}
	
	

}
