// 검색
function search() {
	var searchType = $('#searchType').val();
	var searchText = $('#searchText').val();
	location.href = `/boardList?searchType=${searchType}&searchText=${searchText}`;
}