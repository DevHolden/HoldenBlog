package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping({"", "/"})
	// 스프링에서는 데이터를 가져갈 때 모델이 필요함
	public String index(Model model, @PageableDefault(size=3, sort="id", direction = Direction.DESC) Pageable pageable) {		
		// /WEB-INF/views/index.jsp
		model.addAttribute("boards", boardService.글목록(pageable));
		return "index";
	}
	
	// USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
