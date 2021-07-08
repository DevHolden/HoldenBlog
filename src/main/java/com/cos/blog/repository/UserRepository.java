package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// 해당 JpaRepository는 User 테이블이 관리하는 리포지토리이다. 이 User 테이블의 PK는 Integer다.
// 자동으로 bean 등록이 된다.
// @Repository 생략 가능하다.
// DAO
public interface UserRepository extends JpaRepository<User, Integer>{
	// SELECT * FROM user WHERE username = 1?;		1에는 첫번째 파라미터가 들어감
	Optional<User> findByUsername(String username);
	
}




// JPA Naming 전략(실제로 JPA가 들고 있는 함수가 아님, but 이름을 이렇게 지어주면 아래의 쿼리문의 역할을 함)
// SELECT * FROM user WHERE username = ?1 AND password = ?2;
// User findByUsernameAndPassword(String username, String password);