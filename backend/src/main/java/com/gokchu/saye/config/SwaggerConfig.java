package com.gokchu.saye.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("SAYE (Sing About Your Emotion)")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("SAYE :: Sing About Your Emotion, 당신의 감정을 노래하다")
				.description("오늘 하루에 대한 감정(고민,생각)을 분석하여 당신을 위한 노래 추천 서비스")
				.termsOfServiceUrl("https://www.notion.so/oct14jh/One-Line-301b283ab8ca4fb7b49647eb99e98a20")
				.license("SAYE(Sing About Your Emotion), 개발팀-곡추남")
				.licenseUrl("khyun7621@naver.com").version("1.0").build();
	}
}