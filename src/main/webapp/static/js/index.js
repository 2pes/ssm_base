var editRow = '';//编辑的行对象

/**
 * ajax封装
 * @param url
 * @param params
 * @param callback
 * @returns {*}
 */
function ajaxPost(url, params, callback) {
    var result = null;
    var headers = {};
    headers['CSRFToken'] = $("#csrftoken").val();

    $.ajax({
        type: 'post',
        async: false,
        url: url,
        data: params,
        dataType: 'json',
        headers: headers,
        success: function (data, status) {
            result = data;
            if (data && data.code && data.code == '101') {
                modals.error("操作失败，请刷新重试，具体错误：" + data.message);
                return false;
            }
            if (callback) {
                callback.call(this, data, status);
            }
        },
        error: function (err, err1, err2) {
            console.log("ajaxPost发生异常，请仔细检查请求url是否正确，如下面错误信息中出现success，则表示csrftoken更新，请忽略");
            console.log(err.responseText);
            if (err && err.readyState && err.readyState == '4') {
                var sessionstatus = err.getResponseHeader("session-status");
                if (sessionstatus == "timeout") {
                    //如果超时就处理 ，指定要跳转的页面
                    window.location.href = basePath + "/";
                }
                else {//csrf异常
                    var responseBody = err.responseText;
                    if (responseBody) {
                        responseBody = "{'retData':" + responseBody;
                        var resJson = eval('(' + responseBody + ')');
                        $("#csrftoken").val(resJson.csrf.CSRFToken);
                        this.success(resJson.retData, 200);
                    }
                    return;
                }
            }
            modals.error({
                text: JSON.stringify(err) + '<br/>err1:' + JSON.stringify(err1) + '<br/>err2:' + JSON.stringify(err2),
                large: true
            });
        }
    });

    return result;
}

//日期格式化显示
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
    var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
    var m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
    var s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
    return Y + M + D + h + m + s;
}

//退出系统方法
function logout() {
    _confirm('您确定要退出本系统吗?', null, function () {
        location.href = ctx + '/logout';
    })
}

//帮助
function showhelp() {
    window.open(ctx + '/help.html', '帮助文档');
}

/**
 * 确认窗口
 * @param question
 * @param method_cancel
 * @param method_ok
 * @private
 */
var _confirm = function (question, method_cancel, method_ok) {
    $.messager.confirm('系统提示', question, function (r) {
        if (r) {
            if (method_ok) {
                method_ok();
            }
        } else {
            if (method_cancel) {
                method_cancel();
            }
        }
    });

};

/**
 * 左侧菜单伸缩
 */
function toggle() {
    var opts = $('#sm').sidemenu('options');
    $('#sm').sidemenu(opts.collapsed ? 'expand' : 'collapse');
    opts = $('#sm').sidemenu('options');
    $('#sm').sidemenu('resize', {
        width: opts.collapsed ? 60 : 200
    })
}

/**
 * 打开编辑窗口
 * @param url
 * @param size
 * @param id
 * @param res
 * @param fn
 */
function openDialog(url, size, id, res, fn, status) {
    var split = ['800', '400'];
    editRow = {objId: res, status: status};//obj为编辑项id,status编辑状态，为add
    var row = '';
    try {
        fn = eval(fn);
    } catch (e) {
        console.log(fn + '方法不存在！');
    }
    if (typeof fn === 'function') {
        row = fn.call(res);
    }
    if (size) {
        split = size.split('_');
    }
    $('#dd').dialog({
        title: '编辑窗口',
        width: split[0],
        height: split[1],
        closed: false,
        cache: false,
        href: ctx + url,
        modal: true//, buttons: eval(id+'DialogButtons').call(this)
    });
};

//统一封装方法，作用：提示信息
function message_alert(data) {
    //从返回的json数据中获取结果信息
    var data_v = data.result ? data.result : data;

    //提交结果类型
    var type = data_v.type;
    //结果提示信息
    var message = data_v.message;
    //alert(message);
    if (type == 0) {
        //如果type等于0表示失败，调用 jquery easyui的信息提示组件
        $.messager.alert('提示信息', message, 'error');
    } else if (type == 1) {
        $.messager.alert('提示信息', message, 'success');
    } else if (type == 2) {
        $.messager.alert('提示信息', message, 'warning');
    } else if (type == 3) {
        $.messager.alert('提示信息', message, 'info');
    }
};

/**
 * 清除form表单
 * @param id
 */
function clearForm(id) {
    $('#ff_' + id).form('clear');
};