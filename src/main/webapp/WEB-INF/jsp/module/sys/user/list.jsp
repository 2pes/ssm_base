<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<%--
<div class="easyui-layout  " data-options="fit:true">
    <div class=" " data-options="region:'west'">
        <div class="easyui-panel" title="部门列表" data-options="fit:true">
            <div class="toolbar ">
                <div class="ctrl-div ">
                    <a href="#" class="easyui-linkbutton " data-type="add" project-dlg-width="600" project-dlg-height="400" data-content="dept"
                       plain="true">新增</a>
                </div>
            </div>
            <div>
                <ul id="admin_sys_dept_tree" class="project-sys-tree">
                </ul>
            </div>
        </div>
    </div>
    <div class="project-tab-content-center" data-options="region:'center',border:false">
        <div class="project-tab-content-north" data-options="region:'north'">
            <div class="toolbar project-dg-toolbar" id="admin_user_tb">
                <div class="search-div left">
                    <input class="easyui-searchbox" data-options="prompt:'关键字',searcher:common.search" id="admin_user_param"/>
                    <a href="javascript:keySearch();" class="easyui-linkbutton" style="margin-top: 3px" data-type="search"
                       data-content="user" iconCls="icon-search">搜索</a>
                </div>
                <a href="javascript:batchDelete();" class="easyui-linkbutton " style="margin:5px 10px"
                   data-type="batchDel" data-content="user" plain="true">批量删除</a>
                <a href="#" class="easyui-linkbutton " data-type="add" style="margin:5px 10px" project-dlg-width="600"
                   project-dlg-height="400" data-content="user"
                   plain="true">新增</a>
            </div>
        </div>
        <div style="height: calc(100% - 72px);width: 100%">
            <table id="dg_user"></table>
        </div>
    </div>
    <div id="admin_sys_dept_tree_mm" class="easyui-menu" style="width:120px;">
        <div onclick="append()" data-options="iconCls:'icon-add'">添加</div>
        <div onclick="edit(this)" data-type="edit" project-dlg-size="sm" data-content="dept">编辑</div>
        <div onclick="removeit()" data-options="iconCls:'icon-remove'">移除</div>
        <div class="menu-sep"></div>
        <div onclick="rename()">重命名</div>
    </div>
</div>

--%>
<div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',split:true,border:false" style="height:50px">
            aaa
        </div>
        <div data-options="region:'west',split:true,border:false" style="width:100px">
            aaa
        </div>
        <div data-options="region:'center',border:false">
            <table id="dg_user"></table>
        </div>
</div>
<script type="text/javascript">
    $.parser.parse();
    function usercolumns(value, row, index) {
        var toolbtn =
            <shiro:hasPermission name="user:update">
            '<a class="project-common-btn" data-type="edit" data-id="' + row.id + '" project-dlg-size="lm" project-dlg-width="600" project-dlg-height="250" data-content="user" plain="true" href="javascript:void(0);" style="color: #48a2ff">修改</a>'
            </shiro:hasPermission>
            ' | <a  href="javascript:void(0);" data-id="' + row.id + '" data-content="user" style="color: #48a2ff" onclick="deleteId(\''+row.id+'\')">删除</a>'+
            '<a></a>';
        return toolbtn;
    }
    $('#dg_' + 'user').datagrid({
        method: 'post',
        url: ctx + '/module/sys/user/list',
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
            {field: 'id', title: 'id', width: 100},
            {field: 'usercode', title: '账号', width: 100},
            {field: 'username', title: '姓名', width: 100, align: 'right'},
            {field: 'salt', title: '盐', width: 100, align: 'right'},
            {field: 'locked', title: '锁定', width: 100, align: 'right'},
            {field: 'OPTIONS', title: '操作',  formatter: usercolumns}
        ]],
        singleSelect: true,
        pagination: true,
        toolbar: '#tb',
        footer: '#ft',
        scrollbarSize :0,
    });

</script>