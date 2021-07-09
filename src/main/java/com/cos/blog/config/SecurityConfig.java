package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

// Bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration		// Bean 등록
@EnableWebSecurity	// 시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true)	// 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean		// IoC가 된다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인을 해준다. 이 때 password를 가로채기 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {	// 시큐리티 필터에 대한 설정을 이 곳에서 한다.
		http
			.csrf().disable()	// csrf 토큰 비활성화 (테스트 시 걸어두는게 좋음)
			.authorizeRequests()				// 어떤 요청이 들어오면
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**")		// /auth이하의 경로(/auth/loginForm, /auth/joinForm)이면
				.permitAll()								// 누구나 들어올 수 있다. (인가한다)
				.anyRequest()						// 반면 이외의 모든 요청에는
				.authenticated()					// 인증을 거쳐야한다.
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc")	// 스프링 시큐리티가 해당 주소로 오는 로그인을 가로채서 대신 로그인을 처리한다.
				.defaultSuccessUrl("/");		
		
	}
}
