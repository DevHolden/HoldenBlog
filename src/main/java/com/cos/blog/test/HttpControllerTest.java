package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
// @RestController를 붙여줘야 Spring Framework는 이 클래스가 Controller라는 것을 알 수 있다.
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		// Builder 패턴 이용 시 생성자에 값을 넣을 때 순서를 지키지 않아도 된다.
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG+"getter : "+m.getUsername());
		m.setUsername("hoho");
		System.out.println(TAG+"setter : "+m.getUsername());
		
		return "lombok test 완료";
	}

	// 인터넷 브라우저를 통한 요청은 get 요청만 허용된다. (post, put, delete는 인터넷 주소창을 통해 요청할 수 없다)
	// 주소 : http://localhost:8000/blog/http/get
	@GetMapping("/http/get")
	// get : @RequestParam
	public String getTest(Member m) {
		return "get 요청 id :" + m.getId() + ",  " + m.getUsername() + ", "  + m.getPassword() + ", "  + m.getEmail();
	}
	
	// post 요청은 data를 주소에 붙여서 보내는 것이 아니라 body부분에 붙여서 보낸다.
	// 주소 :  http://localhost:8000/blog/http/post
	@PostMapping("/http/post")
	// get : @RequestBody
	public String postTest(@RequestBody Member m) {
		return "post 요청 id :" + m.getId() + ", " + m.getUsername() + ", "  + m.getPassword() + ", "  + m.getEmail();	}
	
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
