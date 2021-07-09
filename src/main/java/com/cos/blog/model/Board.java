package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob	// 대용량 데이터
	private String content;	// 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨.
	
	private int count;		// 조회수
	
	// Many = Board, User = One : 한 명의 유저는 여러 개의 게시글을 쓸 수 있다.
	@ManyToOne(fetch = FetchType.EAGER)		// FetchType.EAGER : 무조건 user의 정보를 들고와야한다. (join을 해서 들고옴)
	@JoinColumn(name="userId")
	private User user;	// DB는 오브젝트를 저장할 수 없기에 FK 사용 but 자바 같은 객체지향 프로그램에서는 오브젝트를 저장할 수 있다.
	
	@OneToMany	(mappedBy = "board", fetch = FetchType.EAGER)	
	// mappedBy가 적혀있으면 연관관계의 주인이 아니다(난 FK가 아니에요) DB에 칼럼을 만들면 안된다.
	// 그저 Board를 SELECT할 때 JOIN문을 통해 값을 얻기 위함이다.
	// @JoinColumn은 FK가 필요 없기 때문에 필요가 없다.
	private List<Reply> reply;	// 하나의 게시글에 댓글이 1개일수도 있고 여러개일 수도 있어서 List의 형태를 가져야한다.
	
	@CreationTimestamp	// 데이터가 insert 혹은 update될 때 자동으로 Date 값이 들어감.
	private Timestamp createDate;
}
