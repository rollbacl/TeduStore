<%@ page 
	contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的订单 - 达内学子商城</title>
    <link href="../css/orders.css" rel="Stylesheet"/>
    <link href="../css/header.css" rel="Stylesheet"/>
    <link href="../css/footer.css" rel="Stylesheet"/>
    <link href="../css/personage.css" rel="stylesheet" />
    <link href="../css/common.css" rel="stylesheet" />
</head>
<body>
<!-- 顶部区域 -->
<c:import url="header.jsp"></c:import>

<!-- 我的订单导航栏-->
<div id="nav_order">
    <ul>
        <li><a href="">首页<span>&gt;</span>个人中心</a></li>
    </ul>
</div>
<!--我的订单内容区域 #container-->
<div id="container" class="clearfix">
    <!-- 左边栏-->
    <c:import url="user_left_side_bar.jsp"></c:import>
    
    <!-- 右边栏-->
    <!--个人信息头部-->
    <div class="rightsidebar_box rt">
        <div class="rs_header">
            <span ><a href="profile.do">我的信息</a></span>
            <span class="rs_header_active"><a href="password.do">安全管理</a></span>
        </div>

        <!--安全管理 -->
        <div class="rs_content">
            <p class="change_password_title">更改密码</p>
            <div class="new_password">
                <span class="word">输入旧密码：</span>
                <input type="password" id="old_password" />
                <span id="old_password_hint" 
                	class="change_hint"></span>
            </div>
            <div class="new_password">
                <span class="word">输入新密码：</span>
                <input type="password" id="new_password" />
                <span id="new_password_hint" 
                	class="change_hint"></span>
            </div>
            <div class="confirm_password">
                <span class="word">确认新密码：</span>
                <input type="password" id="confirm_password" />
                <span id="confirm_password_hint" 
                	class="confirm_hint"></span>
            </div>
            <div class="buttons-block">
                	<a href="#" 
                		onclick="changePassword()" 
                		class="button-blue">保存更改</a>
            </div>
        </div>


    </div>
</div>

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
             <p class="footer2"><img src="../images/footer/footerFont.png" alt=""/></p>
        </div>
        <div class="foot_left lf">
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
</body>
<style>
.msg-error { background: #f00; color: #fff; padding: 5px; }
.msg-correct { background: #0f0; color: #fff; padding: 5px; }
</style>
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<script type="text/javascript" src="../js/jquery.page.js"></script>
<script type="text/javascript" src="../js/orders.js"></script>
<script type="text/javascript">
// ===== 修改密码相关的验证 =====
// 规则1：密码至少6位长度
// 规则2：2次输入的新密码必须一致
// ----------------------------
// 事件1：适用于3个密码输入框，当丢失焦点时验证“规则1”
// 事件2：适用于2个新密码输入框，当丢失焦点时验证“规则2”
// ----------------------------

function changePassword() {
	// 获取3个密码输入框的值
	var pwd1 = $("#old_password").val();
	var pwd2 = $("#new_password").val();
	var pwd3 = $("#confirm_password").val();
	// 基本判断
	if (checkPasswordLength(pwd1) 
		&& checkPasswordLength(pwd2) 
		&& checkPasswordLength(pwd3) 
		&& checkPasswordEquals()) {
		// 同时满足2个规则，则提交
		$.ajax({
			"url": "handle_change_password.do",
			"data": "old_password=" + pwd1 + "&new_password=" + pwd2,
			"type": "POST",
			"dataType": "json",
			"success": function(obj) {
				alert(obj.message);
				$("#old_password").val("");
				$("#new_password").val("");
				$("#confirm_password").val("");
				$("#old_password_hint").hide();
				$("#new_password_hint").hide();
				$("#confirm_password_hint").hide();
			},
			"error": function() {
				alert("出现未知错误！");
				location.href = "../main/index.do";
			}
		});
	} else {
		// 有规则不满足，不提交
		alert("请检查错误，修改后再提交！");
	}
}

function checkPasswordLength(pwd) {
	return pwd.length >= 6;
}

// 验证2个新密码是否一致
function checkPasswordEquals() {
	// 获取2个新密码
	var pwd1 = $("#new_password").val();
	var pwd2 = $("#confirm_password").val();
	// 判断2个新密码是否一致
	$("#confirm_password_hint").show();
	if (pwd1 == pwd2) {
		// 2个新密码一致
		$("#confirm_password_hint").html("密码一致");
		$("#confirm_password_hint").attr("class", "msg-correct");
		return true;
	} else {
		// 2个新密码不一致
		$("#confirm_password_hint").html("两次输入的密码不一致");
		$("#confirm_password_hint").attr("class", "msg-error");
		return false;
	}
}

// 原密码丢失焦点时
$("#old_password").blur(function() {
	// 获取原密码
	var pwd = $("#old_password").val();
	// 判断原密码长度
	$("#old_password_hint").show();
	if (checkPasswordLength(pwd)) {
		// 长度OK
		$("#old_password_hint").html("密码格式正确");
		$("#old_password_hint").attr("class", "msg-correct");
	} else {
		// 长度不够
		$("#old_password_hint").html("密码的长度必须6位以上");
		$("#old_password_hint").attr("class", "msg-error");
	}
});

// 新密码丢失焦点时
$("#new_password").blur(function() {
	// 获取新密码
	var pwd = $("#new_password").val();
	// 判断新密码长度
	$("#new_password_hint").show();
	if (checkPasswordLength(pwd)) {
		// 长度OK
		$("#new_password_hint").html("密码格式正确");
		$("#new_password_hint").attr("class", "msg-correct");
		// 验证2次新密码是否一致
		checkPasswordEquals();
		// 返回
		return true;
	} else {
		// 长度不够
		$("#new_password_hint").html("密码的长度必须6位以上");
		$("#new_password_hint").attr("class", "msg-error");
		// 返回
		return false;
	}
});

// 确认密码丢失焦点时
$("#confirm_password").blur(function() {
	// 获取确认密码
	var pwd = $("#confirm_password").val();
	// 判断确认密码长度
	if (checkPasswordLength(pwd)) {
		// 长度OK
		$("#confirm_password_hint").html("密码格式正确");
		$("#confirm_password_hint").attr("class", "msg-correct");
		// 验证2次新密码是否一致
		checkPasswordEquals();
		// 返回
		return true;
	} else {
		// 长度不够
		$("#confirm_password_hint").html("密码的长度必须6位以上");
		$("#confirm_password_hint").attr("class", "msg-error");
		// 返回
		return false;
	}
});

// ===== 左侧边栏显示“账号管理” =====
$(function() {
	// 隐藏所有块中的所有菜单项
	$("#leftsidebar_box dd").hide();
	// 显示"账号管理"的所有菜单项
	$("#leftsidebar_box .count_managment dd").show();
	// 所有标题的最右侧显示的箭头设置为“向下”
	$("#leftsidebar_box dt img").attr("src","../images/myOrder/myOrder2.png");
	// "账号管理"所属的区块的最右侧显示的箭头设置为“向右”
	$("#leftsidebar_box .count_managment").find('img').attr("src","../images/myOrder/myOrder1.png");
});
// ============================
</script>
</html>