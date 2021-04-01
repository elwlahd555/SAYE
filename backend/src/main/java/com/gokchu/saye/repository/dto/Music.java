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
	private String mArtistId;
	private String mAlbumId;
	private int mCnt;
	private String mEmotion;
	private String mDate;
	private String mUrl;
	public Music() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Music(int mNo, String mTitle, String mGenre, String mArtist, String mAlbum, String mPreview, String mImg,
			String mPopularity, String mId, String mArtistId, String mAlbumId, int mCnt, String mEmotion, String mDate,
			String mUrl) {
		super();
		this.mNo = mNo;
		this.mTitle = mTitle;
		this.mGenre = mGenre;
		this.mArtist = mArtist;
		this.mAlbum = mAlbum;
		this.mPreview = mPreview;
		this.mImg = mImg;
		this.mPopularity = mPopularity;
		this.mId = mId;
		this.mArtistId = mArtistId;
		this.mAlbumId = mAlbumId;
		this.mCnt = mCnt;
		this.mEmotion = mEmotion;
		this.mDate = mDate;
		this.mUrl = mUrl;
	}
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
	public String getmArtistId() {
		return mArtistId;
	}
	public void setmArtistId(String mArtistId) {
		this.mArtistId = mArtistId;
	}
	public String getmAlbumId() {
		return mAlbumId;
	}
	public void setmAlbumId(String mAlbumId) {
		this.mAlbumId = mAlbumId;
	}
	public int getmCnt() {
		return mCnt;
	}
	public void setmCnt(int mCnt) {
		this.mCnt = mCnt;
	}
	public String getmEmotion() {
		return mEmotion;
	}
	public void setmEmotion(String mEmotion) {
		this.mEmotion = mEmotion;
	}
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	public String getmUrl() {
		return mUrl;
	}
	public void setmUrl(String mUrl) {
		this.mUrl = mUrl;
	}
	
}
