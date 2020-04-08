<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="easyui-panel"  data-options="border:false" style="width:586px;max-width:600px;padding:30px 60px;margin:0 auto;">
    <form id="ff_permission" method="post">
        <input type="hidden" name="id">
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="name" style="width:100%" data-options="label:'名称:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="type" style="width:100%" data-options="label:'类型:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="status" style="width:100%" data-options="label:'状态:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="url" style="width:100%" data-options="label:'路径:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="percode" style="width:100%" data-options="label:'权限码:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="parenid" style="width:100%" data-options="label:'父级:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="sortstring" style="width:100%" data-options="label:'排序号:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <select class="easyui-combobox" name="language" label="Language" style="width:100%">
                <option value="ar">Arabic</option>
                <option value="bg">Bulgarian</option>
                <option value="ca">Catalan</option>
                <option value="zh-cht">Chinese Traditional</option>
                <option value="cs">Czech</option>
                <option value="da">Danish</option>
                <option value="nl">Dutch</option>
                <option value="en" selected="selected">English</option>
                <option value="et">Estonian</option>
                <option value="fi">Finnish</option>
                <option value="fr">French</option>
                <option value="de">German</option>
                <option value="el">Greek</option>
                <option value="ht">Haitian Creole</option>
                <option value="he">Hebrew</option>
                <option value="hi">Hindi</option>
                <option value="mww">Hmong Daw</option>
                <option value="hu">Hungarian</option>
                <option value="id">Indonesian</option>
                <option value="it">Italian</option>
                <option value="ja">Japanese</option>
                <option value="ko">Korean</option>
                <option value="lv">Latvian</option>
                <option value="lt">Lithuanian</option>
                <option value="no">Norwegian</option>
                <option value="fa">Persian</option>
                <option value="pl">Polish</option>
                <option value="pt">Portuguese</option>
                <option value="ro">Romanian</option>
                <option value="ru">Russian</option>
                <option value="sk">Slovak</option>
                <option value="sl">Slovenian</option>
                <option value="es">Spanish</option>
                <option value="sv">Swedish</option>
                <option value="th">Thai</option>
                <option value="tr">Turkish</option>
                <option value="uk">Ukrainian</option>
                <option value="vi">Vietnamese</option>
            </select>
        </div>
        <div style="margin-bottom:20px">
            <label for="accept" class="textbox-label">Accept:</label>
            <input id="accept" type="checkbox" name="accept" value="true">
        </div>
    </form>
    <div style="text-align:center;padding:5px 0">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm('permission')" style="width:80px">重置</a>
    </div>
</div>
<script type="text/javascript">
    function loadLocal() {
        $('#ff_' + 'permission').form('load', {
            name: 'myname',
            email: 'mymail@gmail.com',
            subject: 'subject',
            message: 'message',
            language: 'en',
            accept: true
        });
    }

    function loadRemote() {
        $.ajax({
            type: 'post',
            url: ctx + '/sys/permission/detail',
            data: {id: editRow.objId},
            dataType: 'json',
            success: function (data, status) {
                if (data && data.code && data.code == '200') {
                    $('#ff_' + 'permission').form('load', data.data);
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

    function submitForm() {
        $('#ff_' + 'permission').form('submit');
    }
    ;!function () {
        if (editRow.status !== 'add') {
            loadRemote();
        }
    }();

</script>