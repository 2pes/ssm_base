<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fluid Layout - jQuery EasyUI Demo</title>

<%@ include file="/WEB-INF/jsp/commons/head.jsp"%>
<body class="easyui-layout">
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
		<div id="sm"></div>

	</div>
	<div data-options="region:'center'"
		style="padding: 5px; background: #eee;">
		<div id="contentDiv">
			<table class="easyui-datagrid" style="width: 100%; height: 100%"
				data-options="rownumbers:true,singleSelect:true,pagination:true,url:'static/js/mock/datagrid_data.json',method:'get',toolbar:'#tb',footer:'#ft'">
				<thead>
					<tr>
						<th data-options="field:'itemid',width:80">Item ID</th>
						<th data-options="field:'productid',width:100">Product</th>
						<th data-options="field:'listprice',width:80,align:'right'">List
							Price</th>
						<th data-options="field:'unitcost',width:80,align:'right'">Unit
							Cost</th>
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
					onclick="toggledialog()"></a> <a href="javascript:void(0)"
					class="easyui-linkbutton" iconCls="icon-save" plain="true"
					onclick="toggledialog()"></a> <a href="javascript:void(0)"
					class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true"></a>
			</div>
		</div>
	</div>
	<div id="dd"></div>
</body>

<script type="text/javascript">
	var sidemenudata = [ {
		text : '商品管理',
		iconCls : 'icon-more',
		children : [ {
			text : '商品管理',
			url : '/module/mall/items'
		}, {
			text : 'Option5'
		}, {
			text : 'Option6'
		} ]
	}, {
		text : '系统管理',
		iconCls : 'icon-sum',
		state : 'open',
		children : [ {
			text : '人员管理',
			url : '/module/sys/user'
		}, {
			text : '角色管理'
		}, {
			text : '菜单管理'
		}, {
			text : '日志管理'
		}, {
			text : '字典管理'
		}, {
			text : '任务管理',
			children : [ {
				text : '任务列表'
			}, {
				text : '任务日志'
			} ]
		} ]
	}, {
		text : '参数管理',
		iconCls : 'icon-more',
		children : [ {
			text : 'Option4'
		}, {
			text : 'Option5'
		}, {
			text : 'Option6'
		} ]
	} ];

	function toggle() {
		var opts = $('#sm').sidemenu('options');
		$('#sm').sidemenu(opts.collapsed ? 'expand' : 'collapse');
		opts = $('#sm').sidemenu('options');
		$('#sm').sidemenu('resize', {
			width : opts.collapsed ? 60 : 200
		})
	}
	function selectHandler(e, t, i) {
		debugger;
		$('#contentDiv').empty();
		console.log(e.url);
		$('#dg').datagrid(e);
	}
	function toggledialog() {
		$('#dd').dialog({
			title : 'My Dialog',
			width : 800,
			height : 400,
			closed : false,
			cache : false,
			href : ctx + '/sys/dialog',
			modal : true
		});
	};

	//统一封装方法，作用：提示信息
	function message_alert(data) {
		//从返回的json数据中获取结果信息
		var data_v = data.result;

		//提交结果类型
		var type = data_v.type;
		//结果提示信息
		var message = data_v.message;
		//alert(message);
		if (type == 0) {
			//如果type等于0表示失败，调用 jquery easyui的信息提示组件
			$.messager.alert('提示信息', message, 'error');
		} else if (type == 1) {
			$.messager.alert('提示信息', message, 'success');
		} else if (type == 2) {
			$.messager.alert('提示信息', message, 'warning');
		} else if (type == 3) {
			$.messager.alert('提示信息', message, 'info');
		}
	};

	$(function() {
		$('#sm').sidemenu({
			data : sidemenudata,
			onSelect : selectHandler
		})
		var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
		pager.pagination({
			buttons : [ {
				iconCls : 'icon-search',
				handler : function() {
					alert('search');
				}
			}, {
				iconCls : 'icon-add',
				handler : function() {
					alert('add');
				}
			}, {
				iconCls : 'icon-edit',
				handler : function() {
					alert('edit');
				}
			} ]
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
			dataType : ("undefined" != dataType && null != dataType) ? dataType
					: "json",
			success : function(responseText) {
				if (param === null) {
					callbackFn(responseText);
				} else {
					callbackFn(responseText, param);
				}
			}
		};
		var sendData = {
			"studentId" : 1234567890
		};
		$.ajax({
			url : ctx + "/book/1003/appoint",//交互地址
			type : "post",//方法
			//dataType:"json",
			//dataType : "application/json",//头部
			data : sendData,//数据
			success : function(backData) {
				var responseText = backData;
				if (param === null) {
					callbackFn(responseText);
				} else {
					callbackFn(responseText, backData.resultInfo.data.bookId);
				}

			},
			error : function(r, d, i) {
			}
		})
		/* formObj.ajaxSubmit(options); */
	};
	//退出系统方法
	function logout() {
		_confirm('您确定要退出本系统吗?', null, function() {
			location.href = '${ctx }/logout';
		})
	}

	//帮助
	function showhelp() {
		window.open('${ctx }/help.html', '帮助文档');
	}
	var _confirm = function(question, method_cancel, method_ok) {
		$.messager.confirm('系统提示', question, function(r) {
			if (r) {
				if (method_ok) {
					method_ok();
				}
			} else {
				if (method_cancel) {
					method_cancel();
				}
			}
		});

	};
</script>
</html>