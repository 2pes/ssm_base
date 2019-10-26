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
