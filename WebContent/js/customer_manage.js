function previous() {
	document.getElementsByName("page")[1].value = Number(document.getElementsByName("page")[1].value) - 1;
	form1.action = "../admin/customer_manage.jsp";
	form1.submit();
}

function next() {
	document.getElementsByName("page")[1].value = Number(document.getElementsByName("page")[1].value) + 1;
	form1.action = "../admin/customer_manage.jsp";
	form1.submit();
}


function back_home() {
	form1.action = "../admin/admin_manage.jsp";
	form1.submit();
}

function unban() {
	document.forms["form2"].elements["action"].value = "unban";
	form2.submit();
}

function ban() {
	document.forms["form2"].elements["action"].value = "ban";
	form2.submit();
}