function previous() {
	document.getElementsByName("page")[1].value = Number(document.getElementsByName("page")[1].value) - 1;
	form1.action = "../admin/item_manage.jsp";
	form1.submit();
}

function next() {
	document.getElementsByName("page")[1].value = Number(document.getElementsByName("page")[1].value) + 1;
	form1.action = "../admin/item_manage.jsp";
	form1.submit();
}

function back_home() {
	form1.action = "../admin/admin_manage.jsp";
	form1.submit();
}

function recovery() {
	document.forms["form2"].elements["action"].value = "recovery";
	form2.submit();
}

function remove() {
	document.forms["form2"].elements["action"].value = "remove";
	form2.submit();
}