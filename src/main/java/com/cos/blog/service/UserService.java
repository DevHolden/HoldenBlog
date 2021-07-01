package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service	// 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해준다. IoC를 해준다.
public class UserService {

	@Autowired	// 의존성 주입(DI)
	private UserRepository userRepository;
	
	@Transactional	// 회원가입 서비스 전체가 하나의 트랜잭션으로 묶임, 모든 트랜잭션이 성공하면 Commit, 그렇지 않을 경우(오류가 있을 경우)에는 Rollback이 이루어짐
	public Integer 회원가입(User user) {
		
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserService : 회원가입()" + e.getMessage());
			return -1;
		}
	}
}
