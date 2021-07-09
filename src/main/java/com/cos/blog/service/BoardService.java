package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired	// 의존성 주입(DI)
	private BoardRepository boardRepository;

	@Transactional	
	public void 글쓰기(Board board, User user) {	// title, content를 받는다.
		board.setUser(user);
		board.setCount(0);
		boardRepository.save(board);
	}
	
	// Pageable을 넘기면 Page로 리턴받는다.
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);	// 모든 게시글 정보를 다 들고온다.
	}
}
