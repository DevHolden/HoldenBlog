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
	// SELECT 만 하는 것엔 readOnly = ture를 건다.
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);	// 모든 게시글 정보를 다 들고온다.
	}
	
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
				});
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}
}
