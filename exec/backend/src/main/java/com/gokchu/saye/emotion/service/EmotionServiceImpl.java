package com.gokchu.saye.emotion.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class EmotionServiceImpl implements EmotionService {

	@Override
	public String EmotionAnalysis(String query) {
		URL url = null;
		URLConnection connection = null;
		StringBuilder responseBody = new StringBuilder();

		try {
			// URL 지정
			url = new URL("http://svc.saltlux.ai:31781");
			connection = url.openConnection();
			// Header 정보 지정
			connection.addRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			connection.setDoInput(true);

			JSONObject jsonBody = new JSONObject();
			// 사용자 키
			jsonBody.put("key", "e0109a5b-77f0-4e73-8480-64dbe5dce9e1");
			// 서비스 ID
			jsonBody.put("serviceId", "11987300804");

			// 서비스에서 필요로 하는 parameter
			JSONObject argument = new JSONObject();
//			argument.put("filename", "https://www.w3.org/2001/sw/sweo/public/UseCases/SaltLux-KTF/KTF.pdf");
			argument.put("type", "1");
			argument.put("query", query);

			jsonBody.put("argument", argument);

			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());

			bos.write(jsonBody.toJSONString().getBytes(StandardCharsets.UTF_8));
			bos.flush();
			bos.close();

			BufferedReader br = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			String line = null;
			while ((line = br.readLine()) != null) {
				responseBody.append(line);
			}
			br.close();

		} catch (Throwable e) {
			System.out.println(e.toString());
		}

		return responseBody.toString();
	}

}
