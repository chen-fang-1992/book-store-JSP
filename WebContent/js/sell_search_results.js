function previous() {
	document.getElementsByName("page")[0].value = Number(document.getElementsByName("page")[0].value) - 1;
	form1.action = "../search/sell_search_results.jsp";
	form1.submit();
}

function next() {
	document.getElementsByName("page")[0].value = Number(document.getElementsByName("page")[0].value) + 1;
	form1.action = "../search/sell_search_results.jsp";
	form1.submit();
}