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
					data-options="plain:true,iconCls:'icon-save'">用户操作</a> <a href="#"
					class="easyui-linkbutton"
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
	<div id="dd"></div>
</body>

<script type="text/javascript">
	var sidemenudata = [ {
		text : '系统管理',
		iconCls : 'icon-sum',
		state : 'open',
		children : [ {
			text : '人员管理'
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
		$('#dg').datagrid('reload');
	}
	function toggledialog() {
		$('#dd').dialog({
			title : 'My Dialog',
			width : 400,
			height : 200,
			closed : false,
			cache : false,
			href : ctx + '/sys/dialog',
			modal : true
		});
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
</script>
</html>