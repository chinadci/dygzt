//新建cookie。
//hours为空字符串时,cookie的生存期至浏览器会话结束。hours为数字0时,建立的是一个失效的cookie,这个cookie会覆盖已经建立过的同名、同path的cookie（如果这个cookie存在）。
function setCookie(name, value, hours, path) {
	var name = escape(name);
	var value = escape(value);
	var expires = new Date();
	expires.setTime(expires.getTime() + hours * 3600000);
	path = path == "" ? "" : ";path=" + path;
	_expires = (typeof hours) == "string" ? "" : ";expires="
			+ expires.toUTCString();
	document.cookie = name + "=" + value + _expires + path;
}
//获取cookie值
function getCookieValue(name) {
	var name = escape(name);
	// 读cookie属性，这将返回文档的所有cookie
	var allcookies = document.cookie;
	// 查找名为name的cookie的开始位置
	name += "=";
	var pos = allcookies.indexOf(name);
	// 如果找到了具有该名字的cookie，那么提取并使用它的值
	if (pos != -1) { // 如果pos值为-1则说明搜索"version="失败
		var start = pos + name.length; // cookie值开始的位置
		var end = allcookies.indexOf(";", start); // 从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置
		if (end == -1)
			end = allcookies.length; // 如果end值为-1说明cookie列表里只有一个cookie
		var value = allcookies.substring(start, end); // 提取cookie的值
		return (value); // 对它解码
	} else
		return ""; // 搜索失败，返回空字符串
}
//删除cookie
function deleteCookie(name, path) {
	var name = escape(name);
	var expires = new Date(0);
	path = path == "" ? "" : ";path=" + path;
	document.cookie = name + "=" + ";expires=" + expires.toUTCString() + path;
}

//提交前验证 并检验是否缓存
function check(){
	if($.trim($("#username").val()) == ""){
		$("#username").focus();
		$("#msg").text("请输入用户名");
		return false;
	}
	else if($.trim($("#password").val()) == ""){
		$("#password").focus();
		$("#msg").text("请输入密码");
		return false;
	}
	else if($.trim($("#xzfy").val()) == ""){
		$("#xzfy").focus();
		$("#msg").text("请选择法院");
		return false;
	}
	
	if ($("#remember").attr('checked') == 'checked') {
		setCookie("username", $("#username").val(), 24 * 31, "/");
		setCookie("password", $("#password").val(), 24 * 31, "/");
		setCookie("xzfy", $("#xzfy").val(), 24 * 31, "/");
		setCookie("fydm", $("#fydm").val(), 24 * 31, "/");
	} 
	return true;
}

window.onload = function() {
	// 分析cookie值，显示上次的登陆信息
	var userNameValue = unescape(getCookieValue("username"));
	if(userNameValue != "") {
		$("#username").val(userNameValue);
	}
	var passwordValue = unescape(getCookieValue("password"));
	if(passwordValue != "") {
		$("#password").val(passwordValue);
	}
	var xzfyValue = unescape(getCookieValue("xzfy"));
	if(xzfyValue != "") {
		$("#xzfy").val(xzfyValue);
	}
	var fydmValue = unescape(getCookieValue("fydm"));
	if(fydmValue != "") {
		$("#fydm").val(fydmValue);
	}
	$("#username").focus();
};