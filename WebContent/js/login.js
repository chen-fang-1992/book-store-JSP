function check_login() {
	var username = document.loginForm.username.value;
	var password = document.loginForm.password.value;

	if (username == "") {
		window.alert("username please");
	} else if (password == "") {
		window.alert("password please");
	} else {
		loginForm.submit();
	}
}

function check_admin() {
	var administrator = document.adminForm.administrator.value;
	var password = document.adminForm.password.value;

	if (administrator == "") {
		window.alert("username please");
	} else if (password == "") {
		window.alert("password please");
	} else {
		adminForm.submit();
	}
}