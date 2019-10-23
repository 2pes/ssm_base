<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table id="dg_item"></table>
<script type="text/javascript">
	$('#dg_' + 'item').datagrid({
		method : 'post',
		url : ctx + '/module/mall/items/list',
		queryParams : {
			pageSize : 1,
			pageNum : 10,
			params : {}
		},
		loadFilter : function(data) {
			if (data.code===200 && data.data) {
				return data.data;
			} else {
				return data;
			}
		},
		columns:[[
	        {field:'id',title:'id',width:100},
	        {field:'name',title:'商品名称',width:100},
	        {field:'price',title:'商品定价',width:100,align:'right'},
	        {field:'detail',title:'商品描述',width:100,align:'right'},
	        {field:'pic',title:'商品图片',width:100,align:'right'},
	        {field:'createtime',title:'生产日期',width:100,align:'right'}
	    ]]
	});
</script>