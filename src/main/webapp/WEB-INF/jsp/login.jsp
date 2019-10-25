<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录/注册</title>

    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="${ctx }/webjars/easyui-186/1.8.6/jquery.min.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link rel="shortcut icon" href="${ctx }/static/images/favicon.ico"/>
    <script type="text/javascript">
        var ctx = "${ctx }";
    </script>
    <link href="${ctx }/static/css/login.css" rel="stylesheet" type="text/css" media="all"/>
<body>
<div class="main">
    <h1>登录注册表单切换</h1>
    <div class="w3_login">
        <div class="w3_login_module">
            <div class="module form-module">
                <div class="toggle">
                    <i class="fa fa-times fa-pencil"></i>
                    <div class="tooltip">点击切换</div>
                </div>
                <div class="form">
                    <h2>登录账号</h2>
                    <form action="#" method="post">
                        <input type="text" name="username" placeholder="用户名" required=" "/>
                        <input type="password" name="password" placeholder="密码" required=" "/>
                        <div class="form-remember"> <input type="checkbox" name="rememberMe"/><span>记住我</span></div>
                        <div class="form-randomcode">

                            <input type="text"  id="randomcode" name="randomcode" size="8"  placeholder="验证码" required=" "/> <img
                                id="randomcode_img" src="${ctx }/validatecode" alt="点击刷新"
                                  />
                            <%--<a href=javascript:randomcode_refresh()>刷新</a>--%>
                        </div>


                        <input type="submit" value="登录"/>
                    </form>
                </div>
                <div class="form">
                    <h2>创建一个账号</h2>
                    <form action="#" method="post">
                        <input type="text" name="username" placeholder="用户名" required=" "/>
                        <input type="password" name="password" placeholder="密码" required=" "/>
                        <input type="email" name="Email" placeholder="Email" required=" "/>
                        <input type="text" name="Phone" placeholder="电话" required=" "/>
                        <input type="submit" value="注册"/>
                    </form>
                </div>
                <div class="cta"><a href="#">忘记密码?</a></div>
            </div>
        </div>
    </div>
</div>
</body>
<!-- 消息弹出框提示 -->
<script type="text/javascript">
    ;!function () {
        var location = window.location + "";
        var boo = location.indexOf('login');
        if (boo == -1) {
            window.top.location.href = window.location.href;
            window.top.location.reload();
        }

    }();

    $('#password').textbox({
        inputEvents: $.extend({}, $.fn.textbox.defaults.inputEvents, {
            keyup: function (event) {
                if (event.keyCode == 13) {
                    submitData();
                }
            }
        })
    });

    var msg = "${off_msg}"
    if (msg != "") {
        alert("${off_msg}");
    }
    $('.toggle').click(function () {
        $(this).children('i').toggleClass('fa-pencil');
        $('.form').animate({height: "toggle", 'padding-top': 'toggle', 'padding-bottom': 'toggle', opacity: "toggle"}, "slow");
    });
</script>
</html>