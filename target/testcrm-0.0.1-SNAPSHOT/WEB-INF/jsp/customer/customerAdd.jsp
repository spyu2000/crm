<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
  	<script type="text/javascript">
		
		function addOrUpdateUser(){
			var r = $('#userForm').form('validate');
			if(!r) {
				return false;
			}
			$.postJSON(
				'customer/add',
				$("#userForm").serializeObject(),
				function(data) {
					if (data && data.success == "true") {
						$('#MyPopWindow').window('close');
						$('#userTable').datagrid('reload');  
						$.messager.alert('提示','添加成功','info');
					} else {
						$.messager.alert('错误提示','服务器繁忙，请稍候重试！','error');
					}
				}
			);
		}
		
	</script>
  </head>
  
  <body>
		
		<div style="background:#fafafa;padding:10px;width:500;height:100px;">
    <form id="userForm" method="post">
    	<input type="hidden" id="customerId" name="customerId" value=""/>
    	<table align="center">
    		<tr height="30" >
    			<td>姓名：</td>
    			<td><input class="easyui-validatebox" type="text" name="name" required="true"></input></td>
    			<td>Email：</td>
    			<td><input class="easyui-validatebox" type="text" name="email" validType="email"></input></td>
    		</tr>
    		<tr height="30" >
    			<td>地址：</td>
    			<td><input class="easyui-validatebox" type="text" name="address"></input></td>
    			<td>联系电话：</td>
    			<td><input class="easyui-validatebox" type="text" name="mobile"></input></td>
    		</tr>
    		<tr height="30" >
    			<td colspan="4" align="right"><a href="javascript:void(0);" id="btn-back" onclick="$('#MyPopWindow').window('close');" class="easyui-linkbutton" iconCls="icon-back">返回</a>
		<a href="javascript:void(0);" id="btn-add" onclick="addOrUpdateUser();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
		</td>
    			
    		</tr>
    	</table>
        
    </form>
</div>
		
		
  </body>
</html>
