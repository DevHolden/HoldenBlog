package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service	// 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해준다. IoC를 해준다.
public class UserService {

	@Autowired	// 의존성 주입(DI)
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	

	@Transactional	// 회원가입 서비스 전체가 하나의 트랜잭션으로 묶임, 모든 트랜잭션이 성공하면 Commit, 그렇지 않을 경우(오류가 있을 경우)에는 Rollback이 이루어짐
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();	// 입력받은 password 원본
		String encPassword = encoder.encode(rawPassword);	// 해싱 완료
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user); 
	}
	
	@Transactional
	public void 회원수정(User user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
		// select를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서.
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다.
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		// 회원수정 함수 종료 시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 이루어짐
	}
}
