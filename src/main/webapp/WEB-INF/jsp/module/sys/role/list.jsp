<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false,title:'角色管理',collapsible:false" style="height:65px;margin-bottom: 5px;">
        <div id="tb_role" style="padding: 2px 5px;">
            <form id="ff_role_search">
                名称: <input type="text" name="name" class="easyui-textbox">
                状态: <select class="easyui-combobox" id="sff_available" panelHeight="auto"
                            style="width: 100px">
                <option value="1" selected="selected">可用</option>
                <option value="0">不可用</option>
            </select>
                <a href="javascript:void(0);" class="easyui-linkbutton project-search-btn" iconCls="icon-search">查询</a>
                <a href="javascript:void(0);" class="easyui-linkbutton project-add-btn" iconCls="icon-add" style="float: right;">添加</a></form>
        </div>
    </div>
    <div data-options="region:'center',border:false">
        <table id="tt_role"></table>
    </div>
</div>

<script type="text/javascript">
    $.parser.parse();

    function getEdit(id) {
        var row = $('#tt_' + 'role').datagrid("getSelected");
        return row;
    };

    function detletRow() {
        var row = $('#tt_' + 'role').datagrid("getSelected");
        return row;
    };


    function search() {

        var $searchform = $('#ff_role_search');
        $('#tt_' + 'role').datagrid('load', {
            // pageSize: 1,
            // pageNum: 10,
            params: {
                name: $searchform.find('input[name=\'name\']').val().trim(),
                available: $('#sff_available').combobox('getValue')
            }
        });
        $("#ff_role_search")[0].reset();
    };

    var roleDialogButtons = function (value, row, index) {
        var toolbtn = '<div id="dlg_role_buttons" style="text-align:center;padding:5px 0">\n' +
            '    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交</a>\n' +
            '    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm(\'role\')" style="width:80px">重置</a>\n' +
            '</div>'; return toolbtn;
    };
    ;!function () {
        $('.project-search-btn').bind('click', function () {
            search();
        });
        $('.project-add-btn').bind('click', function () {
            editRow = '';
            openDialog('/sys/role/edit', '400_300', null, -1, null, 'add');
        });
        var rolecolumns = function (value, row, index) {
            var toolbtn = '<a plain="true" href="javascript:void(0);" style="color: #48a2ff" onclick="openDialog(\'/sys/role/edit\',\'400_300\',\'role\',\'' + row.id + '\',getEdit,\'edit\')">修改</a>' +
                ' | <a plain="true"  href="javascript:void(0);" style="color: #48a2ff" onclick="openDialog(\'/sys/role/editPerm\',\'500_500\',\'role\',\'' + row.id + '\',getEdit,\'edit\')">权限分配</a>'
                ' | <a plain="true"  href="javascript:void(0);" style="color: #48a2ff" onclick="_confirm(\'确认删除【' + row.name + '\】\',null,detletRow)">删除</a>'
            return toolbtn;
        };


        $('#tt_' + 'role').datagrid({
            url: ctx + '/sys/role/list',
            method: 'post',
            fitColumns: true,
            fit: true,
            singleSelect: true,
            pagination: true,
            scrollbarSize: 0,
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'id', title: '序号', width: 1, hidden: true},
                {field: 'name', title: '名称', width: 150, align: 'left'}, {
                    field: 'available', title: '状态', width: 40, align: 'left',
                    formatter: function (value, row, index) {
                        var tag = '';
                        if (value === '1') {
                            tag = '<span style="color: rgba(59,155,76,0.99)">可用</span>';
                        } else {
                            tag = '<span style="color: #f80">不可用</span>';
                        }
                        return tag;
                    }
                }, {
                    field: 'options', title: '操作', align: 'center', resizable: false,
                    formatter: rolecolumns
                }
            ]],
            onLoadSuccess: function () {
            },
            loadFilter: function (data) {
                if (data.code === 200 && data.data) {
                    return data.data;
                } else {
                    return data;
                }
            }
        });
    }();
</script>