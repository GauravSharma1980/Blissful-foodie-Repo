package com.blissful.foodie;

import com.blissful.foodie.exception.CustomErrorAttributes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlissfulFoodieAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlissfulFoodieAppApplication.class, args);
	}


	@Bean
	public ErrorAttributes errorAttributes(){
		return new CustomErrorAttributes();
	}
}
