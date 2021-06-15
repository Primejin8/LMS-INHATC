package kr.inhatc.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//스프링 시큐리티 필터가 스프링 필터체인에 등록됨.
@EnableWebSecurity
//secured 어노테이션 활성화, preAuthorize 어노테이션 활성화
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled=true)
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
		// 직책에 따라 권한을 주어 해당 직책이 아니면 접근 불가
			.antMatchers("/std/**").access("hasRole('ROLE_STUDENT')")
			.antMatchers("/prof/**").access("hasRole('ROLE_PROF')")
			.antMatchers("/index/**").access("hasRole('ROLE_STUDENT') or hasRole('ROLE_PROF')")
			.anyRequest().permitAll() // 나머지
			.and()
			.formLogin()
				.loginPage("/mainpage") // 로그인 기능 구현한 페이지
				.loginProcessingUrl("/login") // login주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행해줌, 로그인 form의 액션과 일치
				.defaultSuccessUrl("/index") // 로그인 성공 시 이동경로
			.and()
			.logout()
			.logoutSuccessUrl("/mainpage")
			.invalidateHttpSession(true);

	}

}
