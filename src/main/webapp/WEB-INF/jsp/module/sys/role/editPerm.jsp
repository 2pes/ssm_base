<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="easyui-panel" data-options="border:false" style="width:486px;max-width:500px;padding:30px 60px;margin:0 auto;">
    <form id="ff_role" method="post">
        <input type="hidden" name="id">
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="name" style="width:100%" data-options="label:'名称:',required:true" readonly>
        </div>
        <div style="margin-bottom:20px">
            <label class="textbox-label">状态:</label>
            <input type="radio" name="available" value="1" checked="checked">可用
            <input type="radio" name="available" value="0">不可用
        </div>
        <div style="margin-bottom:20px">
            <label class="textbox-label">权限:</label>
            <div class="easyui-panel">
                <ul id="tt_role_permission"></ul>
            </div>

            <input type="hidden" name="permissions">
        </div>
    </form>
    <div id="dlg_role_buttons" style="text-align:center;padding:5px 0">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm('role')" style="width:80px">重置</a>
    </div>
</div>
<script type="text/javascript">


    function loadRemote() {
        $.ajax({
            type: 'post',
            url: ctx + '/module/sys/role/detail',
            data: {id: editRow.objId},
            dataType: 'json',
            success: function (data, status) {
                if (data && data.code && data.code == '200') {
                    $('#ff_' + 'role').form('load', data.data);
                } else {
                    message_alert(data.message);
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
            }
        });

    }

    function loadPermissionRemote() {
        $('#tt_' + 'role_permission').tree({
            method: 'post',
            queryParams: {id: editRow.objId},
            url: ctx + '/module/sys/permission/getMenusByUser',
            loadFilter: function (data) {

                if (data.data) {
                    return data.data;
                } else {
                    return data;
                }
            }, lines: true,
            onLoadSuccess: function (data) {
                $('#tt_' + 'role_permission').tree('collapseAll');
            }, checkbox: true
        });

    }

    function submitForm() {
        $('#ff_' + 'role').form('submit');
    }
    ;!function () {
        if (editRow.status !== 'add') {
            loadRemote();
            loadPermissionRemote();
        }
    }();

</script>