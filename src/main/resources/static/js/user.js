let index = {
	init: function() {
		$("#btn-save").on("click", ()=>{
			this.save();
		});
	},
	
	save: function(){
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),		// #아이디 값으로 값을 찾아 변수에 넣음
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		//console.log(data);
		
		$.ajax().done().fail();	// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청을 한다.
	}
}

index.init();