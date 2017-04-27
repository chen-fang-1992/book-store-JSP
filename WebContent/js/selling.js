function previous() {
	document.getElementsByName("page")[0].value = 
		Number(document.getElementsByName("page")[0].value) - 1;
	form1.action = "../user/selling.jsp";
	form1.submit();
}

function next() {
	document.getElementsByName("page")[0].value = 
		Number(document.getElementsByName("page")[0].value) + 1;
	form1.action = "../user/selling.jsp";
	form1.submit();
}

function activate_sell() {
	document.forms["form2"].elements["action"].value = "activate_sell";
	form2.submit();
}

function unactivate_sell() {
	document.forms["form2"].elements["action"].value = "unactivate_sell";
	form2.submit();
}