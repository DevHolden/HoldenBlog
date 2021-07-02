package com.cos.blog.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data		// 클래스 안의 모든 private 필드에 대해 @Getter와 @Setter을 적용시켜 게터와 세터를 만들어줌
@NoArgsConstructor	// 생성자를 자동으로 만들어줌
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
	int status;
	T data;
}
