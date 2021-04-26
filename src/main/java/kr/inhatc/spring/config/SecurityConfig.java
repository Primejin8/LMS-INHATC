package kr.inhatc.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록됨.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled=true) //secured 어노테이션 활성화, preAuthorize 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/student/**").access("hasRole('ROLE_STUDENT')") //인증만 되면 들어갈 수 있는 주소
		.antMatchers("/prof/**").access("hasRole('ROLE_PROF')")
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/mainpage")
		.loginProcessingUrl("/login") //login주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행해줌
		.defaultSuccessUrl("/"); // 요청하면 /로 그페이지로 그대로 보내줌
	}
	
}
