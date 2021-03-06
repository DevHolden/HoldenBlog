let index = {
	init: function() {
		$("#btn-save").on("click", () => {	// function(){}이 아니라 ()=>{} : this를 바인딩 하기 위해 사용
			// function()을 사용하려면 _this.save()라고 해야 this가 올바르게 지정됨
			this.save();
		});

		$("#btn-delete").on("click", () => {
			this.deleteById();
		});

		$("#btn-update").on("click", () => {
			this.update();
		});
	},

	save: function() {
		let data = {
			title: $("#title").val(),		// 제목
			content: $("#content").val(),	// 컨텐츠(내용)
		};

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {	// 응답이 정상이면
			alert("글 작성이 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {	 // 응답이 실패면
			alert(JSON.stringify(error));
		});
	},

	deleteById: function() {
		let id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json"
		}).done(function(resp) {	// 응답이 정상이면
			alert("글 삭제가 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {	 // 응답이 실패면
			alert(JSON.stringify(error));
		});
	},

	update: function() {
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),		// 제목
			content: $("#content").val(),	// 컨텐츠(내용)
		};

		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {	// 응답이 정상이면
			alert("글 수정이 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {	 // 응답이 실패면
			alert(JSON.stringify(error));
		});
	}
}

index.init();