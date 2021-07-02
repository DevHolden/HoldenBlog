package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity	// User 클래스가 MySQL에 테이블이 자동으로 생성된다.
// @DynamicInsert	// INSERT 할 때 NULL인 필드를 자동으로 제외해줌 (Default값이 존재하는 경우 Default값이 자동으로 들어갈 수 있게 해준다)
public class User {
	
	@Id	// PRIMARY KEY 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;	// 시퀀스, auto_increment (자동으로 들어감)
	
	@Column(nullable = false, length = 30, unique = true)	//	isNotNull, 길이 제한
	private String username;
	
	@Column(nullable = false, length = 100)		// 해쉬 (비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// @ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)		// DB에는 RoleType이라는 타입이 없으므로 해당 타입이 STRING이라고 알려줌
	private RoleType role;	// Enum을 쓰는게 좋다. 
	
	@CreationTimestamp	// 시간이 자동으로 입력
	private Timestamp createDate;
}
