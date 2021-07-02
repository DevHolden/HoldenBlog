package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController	// 데이터만 return
public class UserApiController {

	@Autowired	// 의존성 주입(DI) : 필요한(의존하는) 클래스를 직접 생성하는 것이 아닌, 주입해줌으로써 객체간의 결합도를 줄일 수 있다.
	private UserService userService;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {	// username, password, email
		System.out.println("UserApiController : save 호출됨");
		// 실제로 DB에 insert를 하고 아래에서 return을 해야 함
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new  ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
