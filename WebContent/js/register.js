function register() {
	var username = document.registerForm.username.value;
	var password = document.registerForm.password.value;
	var retype = document.registerForm.retype.value;
	var creditcard = document.registerForm.creditcard.value;

	if (username == "") {
		alert("please input username");
	} else if (password == "") {
		alert("please input password");
	} else if (retype == "") {
		alert("please retype password");
	} else if (password != retype){
		alert("passwords must equal");
	} else if (creditcard == "") {
		alert("please input creditcard");
	} else {
		registerForm.submit();
	}
}