package com.gokchu.saye.repository.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Artist {

	private String aId;
	private String aArtist;
	private String aGenre;
	private String aPopularity;
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getaArtist() {
		return aArtist;
	}
	public void setaArtist(String aArtist) {
		this.aArtist = aArtist;
	}
	public String getaGenre() {
		return aGenre;
	}
	public void setaGenre(String aGenre) {
		this.aGenre = aGenre;
	}
	public String getaPopularity() {
		return aPopularity;
	}
	public void setaPopularity(String aPopularity) {
		this.aPopularity = aPopularity;
	}
	
}
