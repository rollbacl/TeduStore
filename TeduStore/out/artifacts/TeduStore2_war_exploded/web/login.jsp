<%@ page 
 contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>学子商城登陆页面</title>
    <link href="../css/header.css" rel="stylesheet"/>
    <link href="../css/footer.css" rel="stylesheet"/>
    <link href="../css/animate.css" rel="stylesheet"/>
    <link href="../css/login.css" rel="stylesheet"/>
</head>
<body>
<!-- 页面顶部-->
<header id="top">
    <div class="top">
        <img src="../images/header/logo.png" alt=""/>
        <span>欢迎登录</span>
    </div>
</header>
<div id="container">
    <div id="cover" class="rt">
        <form id="login-form" method="post" name="form1">
            <div class="txt">
                <p>
					登录学子商城<span><a href="register.do">新用户注册</a></span>
                </p>
                <div class="text">
                    <input type="text" 
	                    placeholder="请输入您的用户名" 
	                    name="lname" 
	                    id="username" 
	                    required />
                    <span><img src="../images/login/yhm.png"></span>
                </div>
               
                <div class="text">
                    <input type="password" 
	                    id="password" 
	                    placeholder="请输入您的密码" 
	                    name="lwd" 
	                    required 
	                    minlength="6" 
	                    maxlength="15" />
                    <span><img src="../images/login/mm.png"></span>
                </div>
                
                <div class="text">
                    <input type="text" 
	                    id="code" 
	                    placeholder="请输验证码" 
	                    name="code" 
	                    required 
	                    minlength="4" 
	                    maxlength="4" />
                    <span><img id="codeImg" src="code.do"
                    	style="top:-38px;right:-156px"></span>
                </div>
                
                <div class="chose">
						<input type="checkbox" 
							class="checkbox" 
							id="ck_rmbUser" />自动登录
                    <span>忘记密码？</span>
                </div>
                <input class="button_login" 
                	type="button" 
                	value="登录" 
                	id="bt-login" />
            </div>
        </form>
    </div>
</div>
<!--错误提示-->
<div id="showResult"></div>
<!-- 品质保障，私人定制等-->
<div id="foot_box">
    <div class="icon1 lf">
        <img src="../images/footer/icon1.png" alt=""/>

        <h3>品质保障</h3>
    </div>
    <div class="icon2 lf">
        <img src="../images/footer/icon2.png" alt=""/>

        <h3>私人定制</h3>
    </div>
    <div class="icon3 lf">
        <img src="../images/footer/icon3.png" alt=""/>

        <h3>学员特供</h3>
    </div>
    <div class="icon4 lf">
        <img src="../images/footer/icon4.png" alt=""/>

        <h3>专属特权</h3>
    </div>
</div>
<!-- 页面底部-->
<div class="foot_bj">
    <div id="foot">
        <div class="lf">
            <p class="footer1"><img src="../images/footer/logo.png" alt="" class=" footLogo"/></p>
            <p class="footer2"><img src="../images/footer/footerFont.png"alt=""/></p>
            
        </div>
        <div class="foot_left lf" >
            <ul>
                <li><a href="#"><h3>买家帮助</h3></a></li>
                <li><a href="#">新手指南</a></li>
                <li><a href="#">服务保障</a></li>
                <li><a href="#">常见问题</a></li>
            </ul>
            <ul>
                <li><a href="#"><h3>商家帮助</h3></a></li>
                <li><a href="#">商家入驻</a></li>
                <li><a href="#">商家后台</a></li>
            </ul>
            <ul>
                <li><a href="#"><h3>关于我们</h3></a></li>
                <li><a href="#">关于达内</a></li>
                <li><a href="#">联系我们</a></li>
                <li>
                    <img src="../images/footer/wechat.png" alt=""/>
                    <img src="../images/footer/sinablog.png" alt=""/>
                </li>
            </ul>
        </div>
        <div class="service">
            <p>学子商城客户端</p>
            <img src="../images/footer/ios.png" class="lf">
            <img src="../images/footer/android.png" alt="" class="lf"/>
        </div>
        <div class="download">
            <img src="../images/footer/erweima.png">
        </div>
		<!-- 页面底部-备案号 #footer -->
            <div class="record">
                &copy;2017 达内集团有限公司 版权所有 京ICP证xxxxxxxxxxx
			</div>
    </div>
</div>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../jquery/jquery.cookie.js"></script>
<script>
$("#username").blur(function(){
	var data = $("#username").val();
	if (data == null || data == "") {
		$("#showResult").text("用户名不能为空！");
		$("#showResult").css("color","red");
		return false;
	}
});
$("#codeImg").click(function(){
	$("#codeImg")[0].src="code.do?"+new Date();
});
$("#code").blur(function(){
	var data = $("#code").val();
	if (data == null || data == "") {
		$("#showResult").text("验证码不能为空！");
		$("#showResult").css("color","red");
		return false;
	}
	$.ajax({
		"url": "check_code.do",
		"data": {code:data},
		"type": "POST", 
		"dataType": "json", 
		"success": function(obj) {
			if (obj.state == 1) {
				$("#showResult").text(obj.message);
				$("#showResult").css("color","green");
			} else {
				$("#showResult").text(obj.message);
				$("#showResult").css("color","red");
			}
		}
	});	
});
</script>
<script>
// 为#bt-login绑定单击事件
$('#bt-login').click(function(){
	// var data = '{ ' 
	//	+ '"username":' + $("#username").val() + ','
	//	+ '"password":' + $("#password").val()
	//	+ ' }';
	var data = "username=" + $("#username").val() + "&password=" + $("#password").val();
	// 提交登录
	$.ajax({
		"url": "handle_login.do",
		"data": data,
		"type": "POST", 
		"dataType": "json", 
		"success": function(obj) {
			if (obj.state == 1) {
				// 登录成功，保存Cookie
				saveCookie();
				// 跳转到主页
				// /user/login.do
				// /main/index.do
				location.href = "../main/index.do";
			} else {
				// 登录失败，用户名(0)或密码(-1)错误
				$("#showResult").html(obj.message);
			}
		}
	});
});
</script>
<script type="text/javascript">
// 页面加载完成后
// 判断在Cookie中是否有“自动登录”相关信息
// 如果有，则勾上“自动登录”，并在用户名和密码的输入框中设置默认值
$(document).ready(function () {
	if ($.cookie("rmbUser") == "true") {
		$("#ck_rmbUser").attr("checked", true);
		$("#username").val($.cookie("username"));
		$("#password").val($.cookie("password"));
	}
});

// 记住用户名密码
function saveCookie() {
	// 判断是否勾选了“自动登录”
	var checked = $("#ck_rmbUser").prop("checked");
	if (checked) {
		// 勾选了“自动登录”
		// 获取输入框中的内容
		var str_username = $("#username").val();
		var str_password = $("#password").val();
		// 在Cookie中记录“自动登录”、“用户名”和“密码”
		$.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
		$.cookie("username", str_username, { expires: 7 });
		$.cookie("password", str_password, { expires: 7 });
	} else {
		// 没有勾选“自动登录”
		// 清除Cookie中的数据，并设置为过期
		$.cookie("rmbUser", "false", { expire: -1 });
		$.cookie("username", "", { expires: -1 });
		$.cookie("password", "", { expires: -1 });
	}
};
</script>
</body>
</html>