<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/common.jsp" %>
	
	<script>
		$(document).ready(function(){
			$('#quanxian').datagrid({
				nowrap: false,
				striped: true,
				collapsible:true,
				singleSelect:true,
				columns:[[
					{field:'permName',title:'权限名',width:80}
				]]
			});
			$('#renyuan').datagrid({
				nowrap: false,
				striped: true,
				collapsible:true,
				singleSelect:true,
				columns:[[
					{field:'loginName',title:'用户名',width:80}
				]]
			});

			$('#roles').tree({
				onClick:function(node){
					$('#quanxian').datagrid({
						url: "<%=basePath%>role/perm?roleId=" + node.id
					});
					$('#renyuan').datagrid({
						url: "<%=basePath%>user/crew?roleId=" + node.id
					});
				}
			});
		});
		
		function roleWin() {
			$("#roleForm").form('clear');
			$("#roleWin").window('open');
		}
		function updateRole() {
			var node = $('#roles').tree('getSelected');
			if(node) {
				$("#roleWin").window('open');
				$("#roleForm").form('load', {'roleId':node.id,'roleName':node.text});
			}
		}
		function delRole() {
			var node = $('#roles').tree('getSelected');
			if(node) {
				var roleId = node.id;
				$.messager.confirm('提示','确定要删除吗?',function(result){
					if(result) {
						$.get('role/del?rid='+roleId, function(data){
							if (data && data.success == "true") {
								$('#roles').tree('remove', node.target);
							} else {
								$.messager.alert('错误提示','服务器繁忙，请稍候重试！','error');
							}
						});
					}
				});
			}
		}
		
		function addRole() {
			var r = $('#roleForm').form('validate');
			if(!r) {
				return false;
			}
			
			$.postJSON(
					'role/add',
					$("#roleForm").serializeObject(),
					function(data) {
						if (data && data.success == "true") {
							closeRoleWin();
							window.location.href=window.location.href;
						} else {
							$.messager.alert('错误提示','服务器繁忙，请稍候重试！','error');
						}
					}
			);
		}
		function closeRoleWin() {
			$('#roleWin').window('close');
		}
	
		function permWin() {
			var node = $('#roles').tree('getSelected');
			if(node) {
				var roleId = node.id;
				$("#permWin").window({
					title:'设置角色权限',
					href:'<%=basePath%>role/permWin?roleId='+roleId,
					width:550,
					height:300
				});
			} else {
				$.messager.alert('提示','请先选择要设置权限的角色！','warning');
			}
		}
		function userWin() {
			var node = $('#roles').tree('getSelected');
			if(node) {
				var roleId = node.id;
				$("#permWin").window({
					title:'设置角色用户',
					href:'<%=basePath%>role/userWin?roleId='+roleId,
					width:550,
					height:300
				});
			} else {
				$.messager.alert('提示','请先选择要设置权限的角色！','warning');
			}
		}
	</script>
  </head>
  
  <body  class="easyui-layout" >
  		<div region="north" title="角色管理" style="height:63px; overflow: hidden">
  			<div style="padding:5px; width:98%">
			<a href="javascript:roleWin()" class="easyui-linkbutton" plain="true" iconCls="icon-add">添加角色</a>
			<a href="javascript:updateRole()" class="easyui-linkbutton" plain="true" iconCls="icon-edit">修改角色</a>
			<a href="javascript:delRole()" class="easyui-linkbutton" plain="true" iconCls="icon-remove">删除角色</a>
			<a href="javascript:permWin();" class="easyui-linkbutton" plain="true" iconCls="icon-quanxian">设置角色权限</a>
			<a href="javascript:userWin();" class="easyui-linkbutton" plain="true" iconCls="icon-renyuan">设置角色人员</a>
			</div>
  		</div>
   		<div region="west" title="角色名称" style="width:215px;">
   				<ul id="roles"  class="easyui-tree" style="padding:2px" animate="true">
   					<c:forEach items="${roles}" var="role">
   						<li id="${role.roleId }">${role.roleName }</li>
   					</c:forEach>
   				</ul>
   		</div>
   		<div region="center" style="">
   			<div class="easyui-panel" title="该角色拥有的权限" style="height:350px">
   				<div id="quanxian"></div>
   			</div>
   			<div class="easyui-panel" title="该角色拥有的用户">
   				<div id="renyuan"></div>
   			</div>
   		</div>
   		
   <div id="roleWin" modal="true" class="easyui-window" closed="true"  title="添加角色" style="width:300px;height:180px;">
    <form id="roleForm" name="roleForm" style="padding:10px 20px 10px 40px;">
    	<input id="roleId" name="roleId" type="hidden"/>
        <p>角色名: <input class="easyui-validatebox" required="true"  name="roleName" type="text"></p>
        <div style="padding:5px;text-align:center;">
            <a href="javascript:void(0)" onclick="addRole()" class="easyui-linkbutton" icon="icon-ok">确定</a>
            <a href="javascript:void(0)" onclick="closeRoleWin()" class="easyui-linkbutton" icon="icon-cancel">关闭</a>
        </div>
    </form>
	</div>
	
	<div id="permWin" modal="true" shadow="false" minimizable="false" cache="false" maximizable="false" collapsible="false" resizable="false" style="margin: 0px;padding: 0px;overflow: auto;"></div>

  </body>
</html>
