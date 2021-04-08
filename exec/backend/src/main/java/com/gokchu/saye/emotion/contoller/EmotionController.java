package com.gokchu.saye.emotion.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gokchu.saye.emotion.service.EmotionService;

@RestController
@RequestMapping("/emotion")
public class EmotionController {

	@Autowired
	private EmotionService service;

	@PostMapping("/analysis")
	public String EmotionAnalysis(@RequestBody String query) {
		return service.EmotionAnalysis(query);
	}
}
