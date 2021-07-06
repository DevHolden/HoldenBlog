package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration		// Bean 등록
@EnableWebSecurity	// 시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true)	// 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean		// IoC가 된다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {	// 시큐리티 필터에 대한 설정을 이 곳에서 한다.
		http
			.csrf().disable()	// csrf 토큰 비활성화 (테스트 시 걸어두는게 좋음)
			.authorizeRequests()				// 어떤 요청이 들어오면
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")		// /auth이하의 경로(/auth/loginForm, /auth/joinForm)이면
				.permitAll()								// 누구나 들어올 수 있다. (인가한다)
				.anyRequest()						// 반면 이외의 모든 요청에는
				.authenticated()					// 인증을 거쳐야한다.
			.and()
				.formLogin()
				.loginPage("/auth/loginForm");					
		
	}
}
