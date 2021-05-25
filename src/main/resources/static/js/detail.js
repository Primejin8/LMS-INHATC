// 검색
function btnGoodCnt(boardId, empSeq) {
	console.log("boardId", boardId);
	console.log("empSeq", empSeq);
	$.ajax({
		url: "requestObject",
		data: JSON.stringify({
			"boardId":boardId,
			"empSeq":empSeq
		}),
		type: "POST",
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success: function(e) {
			console.log("success", e);
			if(e.resultType == true){
				$('#goodCnt').text(e.goodCnt);
			}
		},
		error: function(e){
			console.log("error", e);
		}
	});
}