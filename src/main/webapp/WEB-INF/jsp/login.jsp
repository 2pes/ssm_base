<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>

<script type="text/javascript"
	src="${ctx }/webjars/easyui-186/1.8.6/jquery.min.js"></script>
<script type="text/javascript">
	var ctx = "${ctx }";
</script>
<body>

	<div class="container-fluid">
		<div class="row" style="margin-top: 8%;">
			<center>
				<font color="red" size="5px">${error_msg }</font>
			</center>
			<center>
				<h2>AdminLogin</h2>
			</center>
		</div>
		<div class="row">
			<center>
				<div class="div_login">
					<div class="row">
						<center>
							<p style="margin-top: 10px;">请登录开启你的会话旅程</p>
						</center>
					</div>
					<div class="row">
						<form action="${ctx }/login" method="post">
							<div class="form-group">
								<input type="text" name="username" class="form-control"
									id="name" placeholder="姓名" style="width: 350px;">
							</div>
							<div class="form-group">
								<input type="password" name="password" class="form-control"
									id="passwd" placeholder="密码" style="width: 350px;">
							</div>
							<div>
								<div class="col-lg-5 col-md-5 checkbox">
									<label><input type="checkbox" name="remember">记住登录信息</label>
								</div>
								<div class="col-lg-4 col-lg-offset-3 col-md-4 col-md-offset-3">
									<button type="submit" class="btn btn-primary">登录</button>
								</div>
							</div>
						</form>
					</div>
					<div class="row">
						<div class="col-lg-5 col-md-5">
							<a href="${ctx }/register.jsp">注册一个新账户</a>
						</div>
					</div>
				</div>
			</center>
		</div>
	</div>
</body>
<!-- 消息弹出框提示 -->
<script type="text/javascript">
	var msg = "${off_msg}"
	if (msg != "") {
		alert("${off_msg}");
	}
</script>
</html>