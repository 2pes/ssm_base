<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<table id="dg_item"></table>
<div id="tb_item" style="padding: 2px 5px;">
    Date From: <input class="easyui-datebox" style="width: 110px">
    To: <input class="easyui-datebox" style="width: 110px">
    Language: <select class="easyui-combobox" panelHeight="auto"
                      style="width: 100px">
    <option value="java">Java</option>
    <option value="c">C</option>
    <option value="basic">Basic</option>
    <option value="perl">Perl</option>
    <option value="python">Python</option>
</select> <a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
</div>
<div id="ft_item" style="padding: 2px 5px;">
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-add" plain="true"></a> <a href="javascript:void(0)"
                                               class="easyui-linkbutton" iconCls="icon-edit" plain="true"
                                               onclick="toggledialog()"></a> <a href="javascript:void(0)"
                                                                                class="easyui-linkbutton" iconCls="icon-save" plain="true"
                                                                                onclick="toggledialog()"></a> <a href="javascript:void(0)"
                                                                                                                 class="easyui-linkbutton" iconCls="icon-cut"
                                                                                                                 plain="true"></a> <a
        href="javascript:void(0)" class="easyui-linkbutton"
        iconCls="icon-remove" plain="true"></a>
</div>
<script type="text/javascript">
    $.parser.parse();

    function itemcolumns(value, row, index) {
        var toolbtn =
            <shiro:hasPermission name="item:update">
            '<a class="paoding-common-btn" data-type="edit" data-id="' + row.id + '" paoding-dlg-size="lm" paoding-dlg-width="600" paoding-dlg-height="250" data-content="user" plain="true" href="javascript:void(0);" style="color: #48a2ff">修改</a>'
            </shiro:hasPermission>
            <shiro:hasPermission name="item:delete">
            ' | <a  href="javascript:void(0);" data-id="' + row.id + '" data-content="user" style="color: #48a2ff" onclick="deleteId(\'' + row.id + '\')">删除</a>';
            </shiro:hasPermission>
        return toolbtn;
    }

    $('#dg_' + 'item').datagrid({
        method: 'post',
        url: ctx + '/module/mall/items/list',
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
            {field: 'name', title: '商品名称', width: 100},
            {field: 'price', title: '商品定价', width: 100, align: 'right'},
            {field: 'detail', title: '商品描述', width: 100, align: 'right'},
            {field: 'pic', title: '商品图片', width: 100, align: 'right'},
            {field: 'createtime', title: '生产日期', width: 100, align: 'right'},
            {field: 'OPTIONS', title: '操作', formatter: itemcolumns}
        ]],
        singleSelect: true,
        pagination: true,
        toolbar: '#tb_item',
        footer: '#ft_item'
    });

    function updateItem(id) {
        $.ajax({
            url: ctx + "/module/sys/permission/getMenusByUser",
            type: "post",
            dataType: "json",
            //dataType : "application/json",//头部
            success: function (backData) {
                if (backData.code === 200) {
                    $('#sm').sidemenu({
                        data: sidemenudata,
                        onSelect: selectHandler
                    })
                } else {
                    message_alert(backData)
                }

            },
            error: function (r, d, i) {
            }
        })
    }
</script>