package com.gokchu.saye.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gokchu.saye.repository.dto.Music;

@Mapper
public interface MusicMapper {

	List<Music> selectByPopularity();

	List<Music> selectByDate();

	List<Music> selectByTitle(String mTitle);

//	String temp(int i);
//
//	void update(int i, String artist);



}
