/**
 * 按钮点击后读秒解锁再次点击
 * @param {Object} btn	this
 * @param {Object} wait	等待秒数
 */
function btnWaitTime(btn,wait) {
	if(wait == 0) { 
		btn.removeAttribute("disabled");
		btn.value = "获取验证码";
		wait = 10;
	} else {
		btn.setAttribute("disabled", true);
		btn.value = "重新发送(" + wait + ")";
		wait--;
		setTimeout(function() {
			btnWaitTime(btn,wait)
		},1000)
	}
}

/**
 * 登录下一步
 */
function upLoginNext(){
	$('#login-one-nav').addClass('hidden');
	$('#login-one-con').addClass('hidden');
	$('#login-rests').addClass('hidden');
	$('#login-two').removeClass('hidden');
}

/**
 * CA登录
 */
function upLoginCA(){
	$('#login-one-nav').addClass('hidden');
	$('#login-one-con').addClass('hidden');
	$('#login-rests').addClass('hidden');
	$('#login-ca').removeClass('hidden');
}


/**
 * 社保号登录
 */
function upLoginSBH(){
	$('#login-one-nav').addClass('hidden');
	$('#login-one-con').addClass('hidden');
	$('#login-rests').addClass('hidden');
	$('#login-sbh').removeClass('hidden');
}


/**
 * 登录返回上一步
 */
function upLoginBack(){
	$('#login-one-nav').removeClass('hidden');
	$('#login-one-con').removeClass('hidden');
	$('#login-rests').removeClass('hidden');
	$('#login-sbh').addClass('hidden');
	$('#login-two').addClass('hidden');
	$('#login-ca').addClass('hidden');
}

/**
 * 根据验证类别控制表单项
 */
function upLoginForm(sel){
	var val = sel.value;
	if(val=="1"){
		$('#mypassword').removeClass('hidden');
		$('#mycaptcha2').removeClass('hidden');
		$('#mycontact').addClass('hidden');
		$('#mycaptcha1').addClass('hidden');
	}else{
		$('#mypassword').addClass('hidden');
		$('#mycaptcha2').addClass('hidden');
		$('#mycontact').removeClass('hidden');
		$('#mycaptcha1').removeClass('hidden');
		if(val=="2"){
			$('#mycontact input').val("182****8888");
		}else if(val=="3"){
			$('#mycontact input').val("1648******.com");
		}
	}
}


