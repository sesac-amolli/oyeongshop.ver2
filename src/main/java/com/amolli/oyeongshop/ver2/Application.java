package com.amolli.oyeongshop.ver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	// 리뷰 이미지 등록-aws사용하려고 com.amazonaws.sdk.disableEc2Metadata 설정해줌
	static {
		System.setProperty("com.amazoneaws.sdk.disableEc2Metadata", "true");
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean(name = "uploadPath")
	public String uploadPath() {
		return "C:\\Users\\User\\Desktop\\pictures\\";
	}

}
