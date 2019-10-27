<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',split:true,border:false" style="height:50px">
        查询、添加
    </div>
    <div data-options="region:'center',border:false">
        <table id="tt_permission"></table>
    </div>
</div>

<script type="text/javascript">
    $.parser.parse();
    function getEdit(id){
        debugger;
        var row = $('#tt_' + 'permission').treegrid("getSelected");
        return row;
    };

    ;!function () {

        var permissioncolumns = function (value, row, index) {
            var toolbtn = '<a plain="true" href="javascript:void(0);" style="color: #48a2ff" onclick="openDialog(\'/module/sys/permission/edit\',\'600_600\',\'tt_permission\','+row.id+',getEdit())">修改</a>' +
                ' | <a plain="true"  href="javascript:void(0);" style="color: #48a2ff" onclick="openWindow(\'' + value + '\',\'' + index + '\')">删除</a>'
            return toolbtn;
        };

        $('#tt_' + 'permission').treegrid({
            url: ctx + '/module/sys/permission/treeGrid',
            title: '菜单列表',
            method: 'post',
            idField: 'id',
            treeField: 'name',
            fitColumns: true,
            fit:true,
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'id', title: '序号', width: 1, hidden: true},
                {field: 'name', title: '权限名称', width: 150, align: 'left'},
                {field: 'icon', title: '图标', width: 100, align: 'left'},
                {
                    field: 'type', title: '类型', width: 40, align: 'left',
                    formatter: function (value, row, index) {
                        var type = '<span style="color: #f80">按钮</span>';
                        if (value == "1") {
                            type = '<span style="color: rgba(59,155,76,0.99)">菜单</span>';
                        }
                        return type;
                    }
                }, {
                    field: 'status', title: '状态', width: 40, align: 'left',
                    formatter: function (value, row, index) {
                        var tag = '<span style="color: #f80">禁用</span>';
                        if (value == "1") {
                            tag = '<span style="color: rgba(59,155,76,0.99)">正常</span>';
                        }
                        return tag;
                    }
                }, {field: 'url', title: 'URL', width: 250, align: 'left'},
                {
                    field: 'OPTIONS', title: '操作', align: 'center', resizable: false,
                    formatter: permissioncolumns
                }
            ]],
            onLoadSuccess: function () {
                $('#tt_' + 'permission').treegrid('expandAll');
            },
            loadFilter: function (data, parentId) {
//            debugger;
//            console.log(data);
                return data.data;
            }
        });
    }();
</script>