<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
  	<script type="text/javascript">
  		$(document).ready(function() {
  	  		
  			$('#users').datagrid({
  				width:500,
  				height:215,
  				nowrap: false,
  				striped: true,
  				collapsible:true,
  				url:'user/role',
  				idField:'userId',
  				queryParams:{roleId : ${roleId}},
  				columns:[[
  					{field:'loginName',title:'用户名',width:80},
  					{field:'ck',title:'是否拥有',checkbox:true}
  				]],
  				onLoadSuccess:function() {
					var rows = $('#users').datagrid('getRows');
					$.each(rows, function(i, row) {
						if(row.ck == "true") {
							$('#users').datagrid('selectRow', i);
						}
					});
				}
  			});
  		});
  		
		function addRoleUser() {
			$.messager.confirm('提示','确定要修改吗?',function(result){
				if(result) {
					var rows = $('#users').datagrid('getSelections');
		        	var ps = "?roleId=" + ${roleId};
		        	$.each(rows,function(i,n){
		        		ps += "&users="+n.userId;
		        	});
		        	$.ajax({
						type: 'get',
						url : 'user/roleUser' + ps,
						beforeSend:function(){
							$("#permWin").mask("正在处理中...");
		        		},
					    success: function(data) {
		        			$("#permWin").unmask();
		        			if (data && data.success == "true") {
		        				$('#permWin').window('close');
		        				$('#renyuan').datagrid('reload');
		            		} else {
		            			$.messager.alert('错误提示','服务器繁忙，请稍候重试！','error');
		            		}
					    }
					});
				}
			});
		}
		
	</script>
  </head>
  
  <body>
	<div style="background:#fafafa;padding:10px;width:500;">
    	<div id="users"></div>
    	<div style="text-align: right;margin-top:5px">
    		<a href="javascript:void(0);" id="btn-back" onclick="$('#permWin').window('close');" class="easyui-linkbutton" iconCls="icon-back">返回</a>
			<a href="javascript:void(0);" id="btn-add" onclick="addRoleUser();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
    	</div>
	</div>
  </body>
</html>
