<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理首页</title>
    <%@ include file="/WEB-INF/jsp/commons/head.jsp" %>
    <script type="text/javascript" src="${ctx }/static/js/index.js"></script>
<body class="easyui-layout">
<%@ include file="/WEB-INF/jsp/commons/loadingDiv.jsp" %>
<div data-options="region:'north',split:false" style="height: 80px;">
    <div style="padding-left: 20px;">
        <h2>标题</h2>
    </div>

    <div style="position: absolute; bottom: 0px; right: 0px;">
        <div class="easyui-panel" style="margin: 0 5px 5px 0; border: none;">
            <a href="https://www.jeasyui.com/documentation/index.php"
               target="_blank" class="easyui-linkbutton" data-options="plain:true">easyui
                api</a> <a href="https://www.jeasyui.com/demo/main/index.php"
                           target="_blank" class="easyui-linkbutton" data-options="plain:true">easyui
            demo</a> <a href="#" class="easyui-linkbutton"
                        data-options="plain:true,iconCls:'icon-cancel'">Cancel</a> <a
                href="#" class="easyui-linkbutton"
                data-options="plain:true,iconCls:'icon-reload'">Refresh</a> <a
                href="#" class="easyui-linkbutton"
                data-options="plain:true,iconCls:'icon-search'">Search</a> <a
                href="#" class="easyui-linkbutton" data-options="plain:true">Text
            Button</a> <a href="#" class="easyui-linkbutton"
                          data-options="plain:true,iconCls:'icon-print'">Print</a> <a href="javascript:addItem();" class="easyui-linkbutton"
                                                                                      data-options="plain:true,iconCls:'icon-print'">Print</a> <a
                href="#" class="easyui-linkbutton"
                data-options="plain:true,iconCls:'icon-help'"> </a> <a href="#"
                                                                       class="easyui-linkbutton"
                                                                       data-options="plain:true,iconCls:'icon-save'">用户操作</a> <a
                href="javascript:logout();" class="easyui-linkbutton"
                data-options="plain:true,iconCls:'icon-back'">退出</a>
        </div>
    </div>
</div>
<div data-options="region:'west'" style="width: 202px;">
    <!-- <div style="margin: 20px 0;">
        <a href="javascript:;" class="easyui-linkbutton" onclick="toggle()">Toggle</a>
    </div> -->
    <div id="sm">

    </div>

</div>
<div data-options="region:'center'"
     style="padding: 5px; background: #eee;" id="contentDiv">
    <table id="dg_index">
        <thead>
        <tr>
            <th data-options="field:'itemid',width:80">Item ID</th>
            <th data-options="field:'productid',width:100">Product</th>
            <th data-options="field:'listprice',width:80,align:'right'">List
                Price
            </th>
            <th data-options="field:'unitcost',width:80,align:'right'">Unit
                Cost
            </th>
            <th data-options="field:'attr1',width:240">Attribute</th>
            <th data-options="field:'status',width:60,align:'center'">Status</th>
        </tr>
        </thead>
    </table>
    <div id="tb" style="padding: 2px 5px;">
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
    <div id="ft" style="padding: 2px 5px;">
        <a href="javascript:void(0)" class="easyui-linkbutton"
           iconCls="icon-add" plain="true"></a> <a href="javascript:void(0)"
                                                   class="easyui-linkbutton" iconCls="icon-edit" plain="true"
                                                   onclick="openDialog ('/sys/dialog','800_400')"></a> <a href="javascript:void(0)"
                                                                                    class="easyui-linkbutton" iconCls="icon-save" plain="true"
                                                                                    onclick="openDialog ()"></a> <a href="javascript:void(0)"
                                                                                                                     class="easyui-linkbutton" iconCls="icon-cut"
                                                                                                                     plain="true"></a> <a
            href="javascript:void(0)" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true"></a>
    </div>
</div>
<div id="dd"></div>
</body>

<script type="text/javascript">

    var sidemenudata = [{
        text: '商品管理',
        iconCls: 'icon-more',
        children: [{
            text: '商品管理',
            url: '/module/mall/items'
        }, {
            text: 'Option5'
        }, {
            text: 'Option6'
        }]
    }, {
        text: '系统管理',
        iconCls: 'icon-sum',
        state: 'open',
        children: [{
            text: '人员管理',
            url: '/module/sys/user'
        }, {
            text: '角色管理',
            url: '/module/sys/role'
        }, {
            text: '菜单管理',
            url: '/module/sys/permission'
        }, {
            text: '日志管理',
            url: '/module/sys/log'
        }, {
            text: '字典管理',
            url: '/module/sys/dict'
        }, {
            text: '任务管理',
            children: [{
                text: '任务列表',
                url: '/module/sys/job'
            }, {
                text: '任务日志'
            }]
        }]
    }, {
        text: '参数管理',
        iconCls: 'icon-more',
        children: [{
            text: '数据监控',
            url: '/toDruid'
        }, {
            text: 'Option5'
        }, {
            text: 'Option6'
        }]
    }];


    function selectHandler(e, t, i) {
        $('#contentDiv').empty();
        $('#contentDiv').load(ctx + e.url, function (response, status, xhr) {
                if (status == "success") {
                } else {
                    message_alert(statusText);
                }
            });
    }


    $(function () {
        $('#dg_' + 'index').datagrid({
            url: ctx + '/static/js/mock/datagrid_data.json',
            columns: [[{
                field: 'itemid',
                title: 'itemid',
                width: 80
            }, {
                field: 'productid',
                title: 'productid',
                width: 100
            }, {
                field: 'listprice',
                title: 'listprice',
                width: 100,
                align: 'right'
            }, {
                field: 'unitcost',
                title: 'unitcost',
                width: 80,
                align: 'right'
            }, {
                field: 'attr1',
                title: 'attr1',
                width: 240,
                align: 'right'
            }, {
                field: 'status',
                title: 'status',
                width: 80,
                align: 'center'
            }]],
            rownumbers: true,
            singleSelect: true,
            pagination: true,
            method: 'get',
            toolbar: '#tb',
            footer: '#ft'
        });
        var pager = $('#dg' + '_index').datagrid().datagrid('getPager'); // get the pager of datagrid
        pager.pagination({
            buttons: [{
                iconCls: 'icon-search',
                handler: function () {
                    alert('search');
                }
            }, {
                iconCls: 'icon-add',
                handler: function () {
                    alert('add');
                }
            }, {
                iconCls: 'icon-edit',
                handler: function () {
                    alert('edit');
                }
            }]
        });
    });

    function bookAppoint(id) {
        //准备使用jquery 提供的ajax Form提交方式
        //将form的id传入，方法自动将form中的数据组成成key/value数据，通过ajax提交，提交方法类型为form中定义的method，
        //使用ajax form提交时，不用指定url，url就是form中定义的action
        //此种方式和原始的post方式差不多，只不过使用了ajax方式

        //第一个参数：form的id
        //第二个参数：sysusersave_callback是回调函数，sysusersave_callback当成一个方法的指针
        //第三个参数：传入的参数， 可以为空
        //第四个参数：dataType预期服务器返回的数据类型,这里action返回json
        //根据form的id找到该form的action地址
        //studentId=1234567890
        jquerySubByFId('userform', sysusersave_callback, null, "json");

    }

    //ajax调用的回调函数，ajax请求完成调用此函数，传入的参数是action返回的结果
    function sysusersave_callback(data) {
        message_alert(data);
    };

    /*
     *form提交(post方式)
     *
     *formId form Id
     *callbackfn 回调函数名(要求函数必须有参数且不能多与两个,一个参数时参数为响应文本,两个参数时第一个参数为响应文本)
     *param 回调函数参数(如果为null,那么调用一个参数的回调函数,否则调用两个参数的回调函数)
     */
    function jquerySubByFId(formId, callbackFn, param, dataType) {
        var formObj = $("#" + formId);
        var options = {
            dataType: ("undefined" != dataType && null != dataType) ? dataType
                : "json",
            success: function (responseText) {
                if (param === null) {
                    callbackFn(responseText);
                } else {
                    callbackFn(responseText, param);
                }
            }
        };
        var sendData = {
            "studentId": 1234567890
        };
        $.ajax({
            url: ctx + "/book/1003/appoint",//交互地址
            type: "post",//方法
            //dataType:"json",
            //dataType : "application/json",//头部
            data: sendData,//数据
            success: function (backData) {
                var responseText = backData;
                if (param === null) {
                    callbackFn(responseText);
                } else {
                    callbackFn(responseText, backData.resultInfo.data.bookId);
                }

            },
            error: function (r, d, i) {
            }
        })
        /* formObj.ajaxSubmit(options); */
    };



    function addItem() {
        $.ajax({
            url: ctx + "/module/mall/items/add",//交互地址
            type: "post",//方法
            //dataType:"json",
            //dataType : "application/json",//头部
            data: {id: 3, name: 'itme1'},//数据
            success: function (backData) {
                var responseText = backData;
                if (param === null) {
                    callbackFn(responseText);
                } else {
                    callbackFn(responseText, backData.resultInfo.data.bookId);
                }

            },
            error: function (r, d, i) {
            }
        })
    }

    function getMenusByUser() {
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

    getMenusByUser();
</script>
</html>