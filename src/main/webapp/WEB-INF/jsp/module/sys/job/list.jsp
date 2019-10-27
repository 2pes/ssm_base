<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',split:true,border:false" style="height:50px">
        aaa
    </div>
    <div data-options="region:'center',border:false">
        <table id="dg_job"></table>
    </div>
</div>
<script type="text/javascript">
    $.parser.parse();
    function jobcolumns(value, row, index) {

        var toolbtn =
            '<a  onclick="edit(\'' + row.jobName + '\',\'' + row.jobGroup + '\');" style="color: #48a2ff">修改</a>' +
            ' | <a  onclick="pauseJob(\'\'+row.jobName+\'\',\'\'+row.jobGroup+\'\');" style="color: #48a2ff">暂停</a>' +
            ' | <a  onclick="resumeJob(\'\'+row.jobName+\'\',\'\'+row.jobGroup+\'\');" style="color: #48a2ff">恢复</a>' +
            ' | <a  onclick="deleteJob(\'' + row.jobName + '\',\'' + row.jobGroup + '\',\'' + row.triggerName + '\',\'' + row.triggerName + '\');" style="color: #48a2ff">删除</a>';
        return toolbtn;
    }
    $('#dg_' + 'job').datagrid({
        method: 'post',
        url: ctx + '/module/sys/job/list',
        queryParams: {
            pageSize: 1,
            pageNum: 10,
            params: {}
        },
        loadFilter: function (data) {
            if (data.code === 200 && data.data) {
                return data.data;
            } else {
                return data;
            }
        },
        columns: [[
            // {field: 'id', title: 'id', width: 100},
            {field: 'jobGroup', title: '任务组名称'  },
            {field: 'jobName', title: '定时任务名称'   },
            {field: 'triggerGroupName', title: '触发器组名称'   },
            {field: 'triggerName', title: '触发器名称'   },
            {field: 'cronExpr', title: '时间表达式',  },
            {field: 'previousFireTime', title: '上次运行时间',  formatter: function (value, row, index) {
                    return timestampToTime(value);
                }},
            {field: 'nextFireTime', title: '下次运行时间',  formatter: function (value, row, index) {
                    return timestampToTime(value);
                }},
            {field: 'jobStatus', title: '任务状态',  formatter: function (value, row, index) {
                var status='';
                if (value== 'NONE') {
                    status='未知';
                }else if (value== 'NORMAL') {
                    status='正常运行';
                }else if (value== 'PAUSED') {
                    status='暂停状态';
                }else if (value== 'COMPLETE') {
                    status='完成状态';
                }else if (value== 'ERROR') {
                    status='错误状态';
                }else if (value== 'BLOCKED') {
                    status='锁定状态';
                }
                    return status;
                }},
            {field: 'runTimes', title: '已经运行时间',  formatter: function (value, row, index) {
                    return timestampToTime(value);
                }},
            {field: 'duration', title: '持续运行时间',  formatter: function (value, row, index) {
                    return timestampToTime(value);
                }},
            {field: 'startTime', title: '开始时间',  formatter: function (value, row, index) {
                    return timestampToTime(value);
                }},
            {field: 'endTime', title: '结束时间',  formatter: function (value, row, index) {
                    return timestampToTime(value);
                }},
            {field: 'jobClass', title: '任务类名',  },
            {field: 'jobMethod', title: '方法名称',  },
            {field: 'jobObject', title: 'jobObject',  },
            {field: 'count', title: '运行次数',  },
            {field: 'OPTIONS', title: '操作',  formatter: jobcolumns}
        ]],
        singleSelect: true,
        pagination: true,
        toolbar: '#tb',
        footer: '#ft'
    });

</script>

<div style="width: 90%;margin: 0 auto;text-align: center;margin-top: 25px;">
    <button type="button" onclick="add();" class="btn">新增任务</button>
</div>
<script type="text/javascript">
    function add(){
        window.location.href = ctx + "/module/sys/job/toAdd";
    }

    function edit(jobName,jobGroup){
        window.location.href = ctx + "/module/sys/job/toEdit?jobName="+jobName+"&jobGroup="+jobGroup;
    }

    //暂停任务
    function pauseJob(jobName,jobGroupName){
        $.post(ctx + "/module/sys/job/pauseJob",{"jobName":jobName,"jobGroupName":jobGroupName},function(data){
            if(data.status = 'success'){
                window.location.href = window.location.href;
            }else{
                alert("操作失败，请刷新重新！");
            }
        });
    }

    //恢复任务
    function resumeJob(jobName,jobGroupName){
        $.post(ctx + "/module/sys/job/resumeJob",{"jobName":jobName,"jobGroupName":jobGroupName},function(data){
            if(data.status = 'success'){
                window.location.href = window.location.href;
            }else{
                alert("操作失败，请刷新重新！");
            }
        });
    }
    //删除
    function deleteJob(jobName,jobGroupName,triggerName,triggerGroupName){
        $.post(ctx + "/module/sys/job/deleteJob",{"jobName":jobName,"jobGroupName":jobGroupName,"triggerName":triggerName,"triggerGroupName":triggerGroupName},
            function(data){
                if(data.status = 'success'){
                    window.location.href = window.location.href;
                }else{
                    alert("操作失败，请刷新重新！");
                }
            });
    }

    /* //执行任务
    function triggerJob(a,b){
        var url = "triggerJob";
        var d = {jobName:a,jobGroupName:b};
        $.post(url,d,function(data){
            if(data.status = 'ok'){
                window.location.href = window.location.href;
            }
        });
    } */
</script>
</body>
</html>