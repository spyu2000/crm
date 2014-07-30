<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客服关系管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<%@ include file="/common.jsp" %>

<script>

function addTab(title, href){   
    var tt = $('#main-center');
    if(tt.tabs('exists', title)){
    	tt.tabs('select', title);  
    	$("body").unmask();
    } else {   
	    if (href){   
	    var content = '<iframe onLoad="$(window.parent.document.body).unmask()" scrolling="yes" frameborder="0"  src="<%=basePath%>'+href+'" style="width:100%;height:100%;"></iframe>';   
	    } else {   
	    var content = '未实现';   
	    }
	    tt.tabs('add',{
	    title:title,   
	    closable:true,   
	    content:content,
	    onBeforeOpen : function() {
	    	$("body").mask("正在处理中...");
	    }
	    }); 
	    
    }   
}  

$(document).ready(function(){
	$('#tt2').tree({
		checkbox: false,
		animate:true,
		onClick:function(node){
			$(this).tree('toggle', node.target);
			var b = $(this).tree('isLeaf', node.target);
			if(b) {
				//自定义属性
				//addTab(node.text, node.attributes.url);
				addTab(node.text, 'role');
			}
		}
	});
	
	$('#userTable').datagrid({
		title:'客户资料',
		iconCls:'icon-bumen',
		method:'post',
		width:665,
		height:450,
		nowrap: false,
		striped: true,
		collapsible:false,
		url: '<%=basePath%>customer/list',
		idField:'customerId',
		columns:[[
			{field:'ck',checkbox:true,width:2}, 
			{field:'name',title:'联系人',width:120},
			{field:'mobile',title:'联系电话',width:150},
			{field:'address',title:'地址',width:137},
			{field:'email',title:'Email',width:150}
		]],
		pagination:true,
		pageSize:20,
		rownumbers:true,
		toolbar:[{
			id:'btnadd',
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				addrow();
			}
		},'-',{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				deleteRow();
			}
		},'-',{
			id:'btnedit',
			text:'更新',
			iconCls:'icon-edit',
			handler:function(){
				updaterow(null);
			}
		}],
		onLoadSuccess:function(){
			$('#userTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		onDblClickRow:function(rowIndex, rowData) {
			updaterow(rowData);
		}
	});
	
});
//新增
function addrow(){
	showWin(function() {
		$('#userForm').form('clear');
	});
}

function showWin(onLoad) {
	$("#MyPopWindow").window({
		title:'增加客户信息',
		href:'<%=basePath%>customer/addWin',
		width:550,
		height:200,
		onLoad: onLoad
	});
}
//更新
function updaterow(data){
	if(data == null) {
		var rows = $('#userTable').datagrid('getSelections');
		//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要更新的客户",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一位用户进行更新",'info');
			return;
		}
		showWin(function() {
			$("#userForm").form('load', rows[0]);
		});
	} else {
		showWin(function() {
			$("#userForm").form('load', data);
		});
	}
}

	
//删除
function deleteRow(){
		$.messager.confirm('提示','确定要删除吗?',function(result){
        if (result){
        	var rows = $('#userTable').datagrid('getSelections');
        	var ps = "";
        	$.each(rows,function(i,n){
        		if(i==0) 
        			ps += "?cid="+n.customerId;
        		else
        			ps += "&cid="+n.customerId;
        	});
        	$.ajax({
				type: 'get',
				url : 'customer/del' + ps,
				beforeSend:function(){
					$("body").mask("正在处理中...");
        		},
			    success: function(data) {
        			$("body").unmask();
        			if (data && data.success == "true") {
            			var rows = $('#userTable').datagrid('getSelections');
                    	var ps = "";
                    	$.each(rows,function(i,n){
                    		var index = $('#userTable').datagrid('getRowIndex', n);
            				$('#userTable').datagrid('deleteRow', index);
                    	});
            		} else {
            			$.messager.alert('错误提示','服务器繁忙，请稍候重试！','error');
            		}
			    }
			});
        	
        }
    });
}
//表格查询
function searchUser(){
	var params = $('#userTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
	var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
	
	$.each( fields, function(i, field){
		params[field.name] = field.value; //设置查询参数
	}); 
	
	$('#userTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
}
//清空查询条件
function clearForm(){
	$('#queryForm').form('clear');
	searchUser();
}


</script>

<style type="text/css">

</style>
  </head>
  
<body class="easyui-layout">  
<div region="north" style="background:#e3e9ff;height:40px">  
    <div style="color:#5F7006;font-size:16px;float:left;font-weight:bold;width:100px;padding:10px 0 0 10px;">CRM2011</div>
</div>  
<div region="west" title="导航菜单" split="true" style="width:200px;">  
    <!-- 树形菜单 -->
     <div style="margin-top:3px">
     <ul id="tt2">
     	<li iconCls="icon-system">
     		<span>系统管理</span>
     		<ul>
     			<li iconCls="icon-group"><span>角色管理</span></li>
     		</ul>
     	</li>
     </ul>
     </div>
     <div style="width:99%;position: absolute; bottom: 0px">
					<div id="p" iconCls="icon-yonghu" class="easyui-panel" title="资讯" style="padding:10px" collapsible="true">
						${user.loginName }&nbsp;<a href="<%=basePath %>login">注销</a>
					</div>
	  </div>
</div>  

<div region="center" style="overflow:auto;">  
    <div id="main-center" class="easyui-tabs" fit="true" border="true">  
        <div  title="首页" style="padding:20px;">  
    <form id="queryForm" style="margin:10;">
		<table width="100%">
			<tr>
			<td style="font-size:18px" width="210px">名字：<input name="name" style="height: 20px">
			</td>
			<td>
			<a href="javascript:void(0)" onclick="searchUser();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
			<a href="javascript:void(0)" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
			</td>
			</tr>
		</table>
	</form>
	           <div style="padding:10;float:left;">
			   	<table id="userTable"></table>
			   </div>
			   
         </div>
     </div>      
</div> 
<div id="MyPopWindow" modal="true" shadow="false" minimizable="false" cache="false" maximizable="false" collapsible="false" resizable="false" style="margin: 0px;padding: 0px;overflow: auto;"></div>
  </body>
</html>
