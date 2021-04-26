package kr.inhatc.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("mainpage");
		registry.addViewController("/student").setViewName("student");
		registry.addViewController("/prof").setViewName("prof");
		registry.addViewController("/joinform").setViewName("joinform");
		
	}

}
