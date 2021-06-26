package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// 해당 JpaRepository는 User 테이블이 관리하는 리포지토리이다. 이 User 테이블의 PK는 Integer다.
// DAO
public interface UserRepository extends JpaRepository<User, Integer>{
	
}
