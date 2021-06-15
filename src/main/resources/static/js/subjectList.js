// 검색
function btnSubjectAdd(seq, empSeq, empName, subName) {
	console.log("seq", seq);
	console.log("empSeq", empSeq);
	console.log("empName", empName);
	console.log("subSeq", seq);
	console.log("subName", subName);
	$.ajax({
		url: "btnSubjectAdd",
		data: JSON.stringify({
			"seq":seq,
			"empSeq":empSeq,
			"empName":empName,
			"subSeq":seq,
			"subName":subName
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

function search() {
	var searchType = $('#searchType').val();
	var searchText = $('#searchText').val();
	location.href = `/subjectList?searchType=${searchType}&searchText=${searchText}`;
}