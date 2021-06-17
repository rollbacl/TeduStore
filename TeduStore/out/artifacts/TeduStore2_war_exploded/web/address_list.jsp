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
    <link href="../css/orders.css" rel="stylesheet"/>
    <link href="../css/header.css" rel="stylesheet"/>
    <link href="../css/footer.css" rel="stylesheet"/>
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
	<div class="rightsidebar_box rt">	
		<!--标题栏-->
		<div class="rs_header">
			<span class="address_title">收获地址管理</span>
		</div>
        
		<!-- 已有地址栏 -->
		<div class="address_list">
			<div class="header">
				<span class="tag">地址名称</span>
				<span class="name">姓名</span>
				<span class="addr">地址详情</span>
				<span class="phone">联系电话</span>
				<span class="op">操作</span>
			</div>
			
			<div id="address_list">
				<div class="content content_active">
					<span class="tag tag_active">办公室</span>
					<span class="name">杨洋</span>
					<span class="addr">北京市海淀区北下关街道中鼎大厦B座331</span>
					<span class="phone">18435110514</span>
					<span class="op">
						<a href="###">修改</a> | 
						<a href="###">删除</a>
					</span>
					<span class="set_default"></span>
				</div>
				<div class="content">
					<span class="tag tag_normal">家里</span>
					<span class="name">杨洋</span>
					<span class="addr">北京市大兴区西红门镇瑞海家园</span>
					<span class="phone">13788882346</span>
					<span class="op">
						<a href="###">修改</a> | 
						<a href="###">删除</a>
					</span>
					<span class="set_default">
						<a href="###">设为默认</a>
					</span>
				</div>
			</div>

		<!-- “新增收货地址”按钮 -->
		<div class="buttons-block">
			<a href="#" onclick="showPopup(0)" 
				class="button-blue">新增收货地址</a>
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

<!-- 弹出窗口 -->
<div id="popup_content">
	<!-- 标题 -->
	<h3>新增收货地址</h3>
	<!-- 表单 -->
	<div class="rs_content">
	<form id="address-form" method="post" action="">
		<!--收货人姓名-->
		<div class="recipients">
			<span class="red">*</span>
			<span class="kuan">收货人：</span>
			<input type="text" 
				name="recvName" 
				id="recvName"/>
		</div>
		<!--收货人所在城市等信息-->
		<div data-toggle="distpicker" class="address_content">
			<span class="red">*</span>
			<span class="kuan">省&nbsp;&nbsp;份：</span>
			<select 
				data-province="---- 选择省 ----" 
				id="recvProvince"
				name="recvProvince"
				onchange="getCities(-1, -1)"
				style="width: 120px;"></select>
			城市：
			<select 
				data-city="---- 选择市 ----" 
				id="recvCity"
				name="recvCity"
				onchange="getAreas(-1)"
				style="width: 120px;"></select>
			区/县：
			<select 
				data-district="---- 选择区 ----" 
				id="recvArea"
				name="recvArea"
				style="width: 120px;"></select>
		</div> 
		<!--收货人详细地址-->
		<div class="address_particular">
			<span class="red">*</span>
			<span class="kuan">详细地址：</span>
			<textarea name="recvAddr" 
				id="recvAddr" 
				cols="60" rows="3" 
				placeholder="建议您如实填写详细收货地址"></textarea>
		</div>
		<!--收货人地址-->
		<div class="address_tel">
			<span class="red">*</span>
			<span class="kuan">手机号码：</span>
			<input type="tel" 
				id="recvPhone" 
				name="recvPhone"/>
			固定电话：
			<input type="text" 
				name="recvTel" 
				id="recvTel"/>
		</div>
		<!--邮政编码-->
		<div class="address_postcode">
			<span class="red">&nbsp;</span>
			<span>邮政编码：</span>
			<input type="text" 
				name="recvZip"
				id="recvZip" />
		</div>
		<!--地址名称-->
		<div class="address_name">
			<span class="red">&nbsp;</span>
			<span>地址名称：</span>
			<input type="text" 
				id="recvTag" 
				name="recvTag"/>
			如：
			<span class="sp">家</span>
			<span class="sp">公司</span>
			<span class="sp">宿舍</span>
		</div>
		<!--保存收货人信息-->
		<div class="buttons-block">
			<input type="hidden" 
				name="id" id="id" />
      	<a href="#"
      		onclick="postForm()"
      		class="button-blue">保存收货人信息</a>
      	<a href="#"
      		onclick="dismissPopup()"
      		class="button-blue">取消</a>
      	<div style="clear: both;"></div>
      </div>
	</form>
	</div>
</div>
<div id="mask"></div>
</body>
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.page.js"></script>
<script type="text/javascript" src="../js/orders.js"></script>
<script type="text/javascript" src="../js/distpicker.data111.js"></script>
<script type="text/javascript" src="../js/distpicker111.js"></script>
<script type="text/javascript" src="../js/personal.js"></script>
<script type="text/javascript">
$(".lxdh_normal").each(function(i,e) {
	var phone = $(e).html();
	$(e).html(changePhone(phone));
});
</script>
<script type="text/javascript">
// 地址列表中每条数据的模版(带有特殊字符用于占位的HTML代码)
var htmlTemplate = '<div class="content %CONTENT_ACTIVE%">'
			+ '<span class="tag %TAG_TYPE%">%TAG%</span>'
			+ '<span class="name">%NAME%</span>'
			+ '<span class="addr">%ADDRESS%</span>'
			+ '<span class="phone">%PHONE%</span>'
			+ '<span class="op">'
			+ '<a href="###" onclick="showPopup(%ID%)">修改</a> | ' 
			+ '<a href="###" onclick="deleteAddress(%ID%)">删除</a>'
			+ '</span>'
			+ '<span class="set_default">'
			+ '<a href="###" onclick="setDefault(%ID%)" style="display: %SET_DEFAULT%;">设为默认</a>'
			+ '</span>'
			+ '</div>';

// 设为默认
function setDefault(id) {
	var url = "set_default.do";
	var data = "id=" + id;
	$.ajax({
		"url": url,
		"data": data,
		"type": "GET",
		"dataType": "json",
		"success": function(obj) {
			if (obj.state == 1) {
				showAddressList();
			} else {
				alert(obj.message);
			}
		},
		"error": function() {
			alert("登录信息已经过期，请重新登录！");
			location.href = "../user/login.do";
		}
	});
}
			
// 删除
function deleteAddress(id) {
	var c = confirm("您确定要删除这条收货地址吗？删除操作将不可恢复！");
	if (!c) {
		return;
	}
	
	var url = "delete.do";
	var data = "id=" + id;
	$.ajax({
		"url": url,
		"data": data,
		"type": "GET",
		"dataType": "json",
		"success": function(obj) {
			// alert(obj.message);
			if (obj.state == 1) {
				showAddressList();
			}
		},
		"error": function() {
			alert("登录信息已经过期，请重新登录！");
			location.href = "../user/login.do";
		}
	});
}

// 显示地址列表
function showAddressList() {
	var url = "get_list.do";
	$.ajax({
		"url": url,
		"type": "GET",
		"dataType": "json",
		"success": function(obj) {
			// 先清空原有列表
			$("#address_list").empty();
			// 声明变量，表示将填入到#address_list中的内容
			var htmlString = "";
			// obj是服务器响应的整个JSON字符串的JSON对象
			// 应该遍历obj.data，决定如何显示
			for (var i = 0; i < obj.data.length; i++) {
				// 获取当次遍历到的收货地址数据
				var address = obj.data[i];
				// 准备模版并替换占位符
				htmlString += htmlTemplate;
				htmlString = htmlString.replace("%TAG%", address.recvTag);
				htmlString = htmlString.replace("%NAME%", address.recvName);
				htmlString = htmlString.replace("%ADDRESS%", address.recvDistrict + address.recvAddr);
				htmlString = htmlString.replace("%PHONE%", address.recvPhone);
				htmlString = htmlString.replace(/%ID%/g, address.id);
				
				if (address.isDefault == 1) {
					htmlString = htmlString.replace("%CONTENT_ACTIVE%", "content_active");
					htmlString = htmlString.replace("%TAG_TYPE%", "tag_active");
					htmlString = htmlString.replace("%SET_DEFAULT%", "none");
				} else {
					htmlString = htmlString.replace("%CONTENT_ACTIVE%", "");
					htmlString = htmlString.replace("%TAG_TYPE%", "tag_normal");
					htmlString = htmlString.replace("%SET_DEFAULT%", "inline");
				}
			}
			// 将内容填入到#address_list中
			$("#address_list").html(htmlString);
		}
	});
}

// ===== 弹出窗口 =====
// 显示弹出窗口
function showPopup(id) {
	// 【新】清空表单中各控件已经有的值
	$("#address-form")[0].reset();
	
	// 将id设置到隐藏域中，以便于后续一并提交
	$("#id").val(id);
	
	// 【新】根据id判断当前操作的类型是增加还是编辑
	var title = 
		id == 0 ? "新增收货地址" : "修改收货地址";
	$("#popup_content h3").html(title);
	
	// 设置弹出区域的尺寸
	var popupWidth = 780;
	var popupHeight = 450;

	// 获取窗口尺寸
	var windowWidth = $(window).width();
	var windowHeight = $(window).height();

	// 弹出mask
	$("#mask").css({
		"width": windowWidth,
		"height": $(document).height()
	});
	$("#mask").show();

	// 弹出popup
	$("#popup_content").css({
		"width": popupWidth,
		"height": popupHeight,
		"left": (windowWidth - popupWidth) / 2,
		"top": 120
	});
	$("#popup_content").show();
	
	// 【新】发出AJAX请求，获取需要编辑的数据，并显示到各控件中
	if (id != 0) {
		// 当前按照“修改”来处理页面
		var url = "get.do";
		var data = "id=" + id;
		$.ajax({
			"url": url,
			"data": data,
			"type": "GET",
			"dataType": "json",
			"success": function(obj) {
				var address = obj.data;
				$("#recvName").val(address.recvName);
				$("#recvAddr").val(address.recvAddr);
				$("#recvPhone").val(address.recvPhone);
				$("#recvTel").val(address.recvTel);
				$("#recvZip").val(address.recvZip);
				$("#recvTag").val(address.recvTag);
				// 【新】加载省的列表
				getProvinces(
						address.recvProvince,
						address.recvCity,
						address.recvArea);
			}
		});
	} else {
		// 【新】 按照“新增”模式处理页面
		// 加载省的列表
		getProvinces(-1, -1, -1);
	}
}

// 隐藏弹出窗口
function dismissPopup() {
	// 隐藏mask
	$("#mask").hide();
	// 隐藏popup
	$("#popup_content").hide();
}

// ===== 加载省列表 =====
function getProvinces(
		provinceCode, cityCode, areaCode) {
	var url = "../dict/provinces.do";
	$.ajax({
		"url": url,
		"type": "GET",
		"dataType": "json",
		"success": function(obj) {
			// 添加默认节点
			var op = document.createElement("option");
			op.value = -1;
			op.text = "----- 请选择 -----";
			document.getElementById("recvProvince").appendChild(op);
			// 遍历添加节点
			for (var i = 0; i < obj.data.length; i++) {
				// 创建<option>节点
				var op = document.createElement("option");
				// 配置节点属性
				// <option value="值">显示的文字</option>
				op.value = obj.data[i].provinceCode;
				op.text = obj.data[i].provinceName;
				// 将节点添加到<select>中
				document.getElementById("recvProvince").appendChild(op);
			}
			// 【新】选中默认的选项(Option)
			$("#recvProvince").val(provinceCode);
			// 【新】加载城市列表
			getCities(cityCode, areaCode);
		}
	});
}
// ===== 加载市列表 =====
function getCities(cityCode, areaCode) {
	$("#recvCity").empty();
	$("#recvArea").empty();
	var url = "../dict/cities.do";
	var data = "provinceCode=" + $("#recvProvince").val();
	$.ajax({
		"url": url,
		"data": data,
		"type": "GET",
		"dataType": "json",
		"success": function(obj) {
			// 添加默认节点
			var op = document.createElement("option");
			op.value = -1;
			op.text = "----- 请选择 -----";
			document.getElementById("recvCity").appendChild(op);
			// 遍历添加节点
			for (var i = 0; i < obj.data.length; i++) {
				// 创建<option>节点
				var op = document.createElement("option");
				// 配置节点属性
				// <option value="值">显示的文字</option>
				op.value = obj.data[i].cityCode;
				op.text = obj.data[i].cityName;
				// 将节点添加到<select>中
				document.getElementById("recvCity").appendChild(op);
			}
			// 【新】选中默认的选项(Option)
			$("#recvCity").val(cityCode);
			// 【新】加载区列表
			getAreas(areaCode);
		}
	});
}
// ===== 加载区列表 =====
function getAreas(areaCode) {
	$("#recvArea").empty();
	var url = "../dict/areas.do";
	var data = "cityCode=" + $("#recvCity").val();
	$.ajax({
		"url": url,
		"data": data,
		"type": "GET",
		"dataType": "json",
		"success": function(obj) {
			// 添加默认节点
			var op = document.createElement("option");
			op.value = -1;
			op.text = "----- 请选择 -----";
			document.getElementById("recvArea").appendChild(op);
			// 遍历添加节点
			for (var i = 0; i < obj.data.length; i++) {
				// 创建<option>节点
				var op = document.createElement("option");
				// 配置节点属性
				// <option value="值">显示的文字</option>
				op.value = obj.data[i].areaCode;
				op.text = obj.data[i].areaName;
				// 将节点添加到<select>中
				document.getElementById("recvArea").appendChild(op);
			}
			// 【新】选中默认的选项(Option)
			$("#recvArea").val(areaCode);
		}
	});
}

// ===== 提交 =====
function postForm() {
	// 获取隐藏域中设置的id值
	var id = $("#id").val();
	// 根据id确定当前是“新增”还是“修改”
	// 从而决定数据提交到哪个路径
	var url = (id == 0) 
		? "add.do" : "handle_update.do";
	// 序列化表单中的数据，也包括隐藏域中的id
	var data = $("#address-form").serialize();
	// 发出请求，并处理响应
	$.ajax({
		"url": url,
		"data": data,
		"type": "POST",
		"dataType": "json",
		"success": function(obj) {
			// 提示操作成功
			alert(obj.message);
			// 关闭弹出窗口
			dismissPopup();
			// 刷新列表
			showAddressList();
		},
		"error": function() {
			alert("登录信息已经过期，请重新登录！");
			location.href = "../user/login.do";
		}
	});
}

// ===== 页面加载完成时应该执行的任务 =====
$(function() {
	// ----- 默认左侧边栏显示“收货地址” -----
	// 隐藏所有块中的所有菜单项
	$("#leftsidebar_box dd").hide();
	// 显示"收货地址"的所有菜单项
	$("#leftsidebar_box .address dd").show();
	// 所有标题的最右侧显示的箭头设置为“向下”
	$("#leftsidebar_box dt img").attr("src","../images/myOrder/myOrder2.png");
	// "收货地址"所属的区块的最右侧显示的箭头设置为“向右”
	$("#leftsidebar_box .address").find('img').attr("src","../images/myOrder/myOrder1.png");
	// ----- 加载地址列表 -----
	showAddressList();
});
</script>
</html>