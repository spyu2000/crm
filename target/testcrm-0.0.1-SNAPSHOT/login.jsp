<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%;width:100%;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common.jsp" %>
<title>jQuery EasyUI</title>

	<style type="text/css" >
		
	.x-form-item-input{
		 width: 220px;
	} 
	</style>
	<script type="text/javascript" >
	if(self!=top){
		top.location=self.location;
	}
		$(document).ready(function(){
			$('#win').window({
				title: 'CRM2011',
				width: 350,
				modal: true,
				resizable:false,
				shadow: false,
				closed: false,
				height: 225,
				
				maximizable:false,
				minimizable:false
			});
			
		});
		
		function doCheck(){
			//window.alert("系统故障，正在抢修中,请稍后再试");
		  //return false;
		  if(document.frmLogin.username.value=="" || document.frmLogin.username.value.length < 5){
			$.messager.alert('错误提示','请输入正确的用户名！','error');
				document.frmLogin.username.focus();
		      return;
		  }
		  if(document.frmLogin.passwd.value=="" || document.frmLogin.passwd.value.length < 5){
			  $.messager.alert('错误提示','请输入验证码！','error');
				document.frmLogin.passwd.focus();
		      return;
		  }
		  if(document.frmLogin.authCode.value=="" || document.frmLogin.authCode.value.length < 4){
			  $.messager.alert('错误提示','请输入验证码！','error');
				document.frmLogin.authCode.focus();
		      return;
		  }   
		  $("#ff").mask("登录中...");
		  document.frmLogin.submit();
		}
		function resets() {
			document.frmLogin.reset();
		}
		function refresh() {
			setTimeout("_refresh()", 700);
		}	

		function _refresh() {
			var src = document.getElementById("authImg").src;
			document.getElementById("authImg").src = src + "?now=" + new Date().getTime(); 
		}
	</script>
</head>
<body style="height:90%;width:90%;overflow:hidden;border:none;" >
	
	<div id="win"  iconCls="icon-mima" style="padding:5px;background: #fafafa;">
		<div class="easyui-layout" fit="true">
			
				<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
					<form id="ff" action="<%=basePath %>login" name="frmLogin" method="post">
						<table align="center" >
							<c:if test="${!empty message}">
							<tr>
							<td colspan="2" align="center"><div class="errormsg">${message}</div></td>
							</tr>
							</c:if>
							<tr>
								<td>用户名：</td>
								<td><input class="easyui-validatebox x-form-item-input" type="text" name="username" required="true"></input></td>
							</tr>	
							<tr>
								<td align="right">密码：</td>
								<td><input class="easyui-validatebox x-form-item-input" type="password" name="passwd" required="true"></input></td>
							</tr>	
							<tr>
								<td ><br>验证码：</td>
								<td align="right">
								<img title="看不清?点击刷新" onclick="refresh();" id="authImg" src="authimg"  />
								<input style="width:140px" class="easyui-validatebox" type="text" name="authCode" required="true"></input></td>
							</tr>	
						</table>
	      			</form>
				</div>
				
				<div region="south" border="false" style="margin-top:2px;text-align:right;height:30px;line-height:30px;">
					<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="doCheck()">登录</a>
					<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="resets()">重置</a>
				</div>
			
			</div>
	</div>
	
</body>
</html>