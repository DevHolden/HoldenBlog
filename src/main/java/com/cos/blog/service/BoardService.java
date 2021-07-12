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
	
	@Transactional	
	public void 글수정하기(int id, Board requestBoard) {			
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
				});	// 영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수 종료시 (Service가 종료될 때) 트랜잭션도 종료. 이 때 더티체킹이 일어난다. -> 자동 업데이트가 됨. DB flush
	}}
