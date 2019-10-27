<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false,title:'菜单管理',collapsible:false" style="height:65px;margin-bottom: 5px;">
        <div id="tb_permission" style="padding: 2px 5px;">
            <form id="ff_permission_search">
                名称: <input type="text" name="name" class="easyui-textbox">
                类型: <select class="easyui-combobox" id="sff_type" panelHeight="auto"
                            style="width: 100px">
                <option value="menu" selected="selected">菜单</option>
                <option value="permission">按钮</option>
            </select> 状态: <select class="easyui-combobox" id="sff_available" panelHeight="auto"
                                  style="width: 100px">
                <option value="1" selected="selected">可用</option>
                <option value="0">不可用</option>
            </select>
                <a href="javascript:void(0);"   class="easyui-linkbutton project-search-btn" iconCls="icon-search">查询</a>
                <a href="javascript:void(0);" class="easyui-linkbutton project-add-btn" iconCls="icon-add" style="float: right;">添加</a></form>
        </div>
    </div>
    <div data-options="region:'center',border:false">
        <table id="tt_permission"></table>
    </div>
</div>

<script type="text/javascript">
    $.parser.parse();

    function getEdit(id) {
        var row = $('#tt_' + 'permission').treegrid("getSelected");
        return row;
    };

    function detletRow() {
        var row = $('#tt_' + 'permission').treegrid("getSelected");
        return row;
    };


    function search() {

        var $searchform = $('#ff_permission_search');
        $('#tt_' + 'permission').treegrid('load',{
            // pageSize: 1,
            // pageNum: 10,
            params: {
                name: $searchform.find('input[name=\'name\']').val().trim(),
                type: $('#sff_type').combobox('getValue'),
                available: $('#sff_available').combobox('getValue')
            }
        });
        $("#ff_permission_search")[0].reset();
    };


    ;!function () {
        $('.project-search-btn').bind('click', function(){
            search();
        });
        $('.project-add-btn').bind('click', function(){
            editRow='';
            openDialog('/module/sys/permission/edit','600_600',null,-1,null,'add');
        });
        var permissioncolumns = function (value, row, index) {
            var toolbtn = '<a plain="true" href="javascript:void(0);" style="color: #48a2ff" onclick="openDialog(\'/module/sys/permission/edit\',\'600_600\',\'tt_permission\',\'' + row.id + '\',getEdit,\'edit\')">修改</a>' +
                ' | <a plain="true"  href="javascript:void(0);" style="color: #48a2ff" onclick="_confirm(\'确认删除【' + row.name + '\】\',null,detletRow)">删除</a>'
            return toolbtn;
        };

        $('#tt_' + 'permission').treegrid({
            url: ctx + '/module/sys/permission/treeGrid',
            method: 'post',
            idField: 'id',
            treeField: 'name',
            fitColumns: true,
            fit: true,
            scrollbarSize: 0,
            queryParams: {

            },
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'id', title: '序号', width: 1, hidden: true},
                {field: 'name', title: '资源名称', width: 150, align: 'left'},
                {field: 'icon', title: '图标', width: 100, align: 'left'},
                {
                    field: 'type', title: '类型', width: 40, align: 'left',
                    formatter: function (value, row, index) {
                        var type = '';
                        if (value === 0) {
                            type = '<span style="color: rgba(59,155,76,0.99)">菜单</span>';
                        } else {
                            type = '<span style="color: #f80">按钮</span>';
                        }
                        return type;
                    }
                }, {
                    field: 'status', title: '状态', width: 40, align: 'left',
                    formatter: function (value, row, index) {
                        var tag = '';
                        if (value === 1) {
                            tag = '<span style="color: rgba(59,155,76,0.99)">可用</span>';
                        } else {
                            tag = '<span style="color: #f80">不可用</span>';
                        }
                        return tag;
                    }
                }, {field: 'url', title: 'URL', width: 250, align: 'left'},
                {field: 'percode', title: '权限码', width: 150, align: 'left'},
                {
                    field: 'options', title: '操作', align: 'center', resizable: false,
                    formatter: permissioncolumns
                }
            ]],
            onLoadSuccess: function () {
                $('#tt_' + 'permission').treegrid('expandAll');
            },
            loadFilter: function (data, parentId) {
//
//            console.log(data);
                return data.data;
            }
        });
    }();
</script>