package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service	// 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해준다. IoC를 해준다.
public class UserService {

	@Autowired	// 의존성 주입(DI)
	private UserRepository userRepository;

	@Transactional	// 회원가입 서비스 전체가 하나의 트랜잭션으로 묶임, 모든 트랜잭션이 성공하면 Commit, 그렇지 않을 경우(오류가 있을 경우)에는 Rollback이 이루어짐
	public void 회원가입(User user) {
		userRepository.save(user); 
	}

	@Transactional(readOnly = true)	// SELECT할 때 트랜잭션 시작, 해당 서비스 종료 시에(트랜잭션 종료) 정합성 유지
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}
