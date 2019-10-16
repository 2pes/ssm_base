<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fluid Layout - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css" href="${ctx }/webjars/easyui-186/1.8.6/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx }/webjars/easyui-186/1.8.6/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx }/webjars/easyui-186/1.8.6/demo.css">
<script type="text/javascript" src="${ctx }/webjars/easyui-186/1.8.6/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/webjars/easyui-186/1.8.6/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'North Title',split:true" style="height: 100px;"></div>
	<div data-options="region:'east',title:'East',split:true" style="width: 100px;"></div>
	<div data-options="region:'west',title:'West'" style="width: 205px;">
		<div style="margin: 20px 0;">
			<a href="javascript:;" class="easyui-linkbutton" onclick="toggle()">Toggle</a>
		</div>
		<div id="sm" class="easyui-sidemenu" data-options="data:data"></div>
		<script type="text/javascript">
			var data = [ {
				text : 'Item1',
				iconCls : 'icon-sum',
				state : 'open',
				children : [ {
					text : 'Option1'
				}, {
					text : 'Option2'
				}, {
					text : 'Option3',
					children : [ {
						text : 'Option31'
					}, {
						text : 'Option32'
					} ]
				} ]
			}, {
				text : 'Item2',
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
		</script>
	</div>
	<div data-options="region:'center',title:'center title'" style="padding: 5px; background: #eee;">
		<table class="easyui-datagrid" style="width: 100%; height: 100%"
			data-options="rownumbers:true,singleSelect:true,url:'static/js/mock/datagrid_data.json',method:'get',toolbar:'#tb',footer:'#ft'">
			<thead>
				<tr>
					<th data-options="field:'itemid',width:80">Item ID</th>
					<th data-options="field:'productid',width:100">Product</th>
					<th data-options="field:'listprice',width:80,align:'right'">List Price</th>
					<th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
					<th data-options="field:'attr1',width:240">Attribute</th>
					<th data-options="field:'status',width:60,align:'center'">Status</th>
				</tr>
			</thead>
		</table>
		<div id="tb" style="padding: 2px 5px;">
			Date From:
			<input class="easyui-datebox" style="width: 110px">
			To:
			<input class="easyui-datebox" style="width: 110px">
			Language:
			<select class="easyui-combobox" panelHeight="auto" style="width: 100px">
				<option value="java">Java</option>
				<option value="c">C</option>
				<option value="basic">Basic</option>
				<option value="perl">Perl</option>
				<option value="python">Python</option>
			</select>
			<a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
		</div>
		<div id="ft" style="padding: 2px 5px;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
		</div>
	</div>
</body>
</html>