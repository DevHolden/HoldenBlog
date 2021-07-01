let index = {
	init: function() {
		$("#btn-save").on("click", ()=>{	// function(){}이 아니라 ()=>{} : this를 바인딩 하기 위해 사용
		// function()을 사용하려면 _this.save()라고 해야 this가 올바르게 지정됨
			this.save();
		});
	},
	
	save: function(){
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),		// #아이디 값으로 값을 찾아 변수에 넣음
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		//console.log(data);
		
		// ajax 호출 시 default가 비동기 호출
		$.ajax({	// 회원가입 수행 요청
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data),	// 위의 data를 java로 그냥 던져주면 js로 이루어져있기에 이해하지 못함. 이를 JSON으로 변경하여 던지기 위한 함수
			contentType: "application/json; charset=utf-8",	// body데이터가 어떤 타입인지(MIME)
			dataType: "json"	// 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열, 생긴게 json이라면 -> javascript로 변경해줌
		}).done(function(resp){	// 응답이 정상이면
			alert("회원가입이 완료되었습니다.");
			console.log(resp);
			location.href = "/blog"
		}).fail(function(error){	 // 응답이 실패면
			alert(JSON.stringify(error));
		});	// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청을 한다.
	}
}

index.init();