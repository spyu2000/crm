<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
  	<script type="text/javascript">
  		$(document).ready(function() {
  	  		
  			$('#perms').datagrid({
  				width:500,
  				height:215,
  				nowrap: false,
  				striped: true,
  				collapsible:true,
  				url:'perm/role',
  				idField:'permId',
  				queryParams:{roleId : ${roleId}},
  				columns:[[
  					{field:'permName',title:'权限功能',width:80},
  					{field:'ck',title:'是否拥有',checkbox:true}
  				]],
  				onLoadSuccess:function() {
					var rows = $('#perms').datagrid('getRows');
					$.each(rows, function(i, row) {
						if(row.ck == "true") {
							$('#perms').datagrid('selectRow', i);
						}
					});
				}
  			});
  		});
  		
		function addRolePerm() {
			$.messager.confirm('提示','确定要修改吗?',function(result){
				if(result) {
					var rows = $('#perms').datagrid('getSelections');
		        	var ps = "?roleId=" + ${roleId};
		        	$.each(rows,function(i,n){
		        		ps += "&perms="+n.permId;
		        	});
		        	$.ajax({
						type: 'get',
						url : 'perm/rolePerm' + ps,
						beforeSend:function(){
							$("#permWin").mask("正在处理中...");
		        		},
					    success: function(data) {
		        			$("#permWin").unmask();
		        			if (data && data.success == "true") {
		        				$('#permWin').window('close');
		        				$('#quanxian').datagrid('reload');
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
    	<div id="perms"></div>
    	<div style="text-align: right;margin-top:5px">
    		<a href="javascript:void(0);" id="btn-back" onclick="$('#permWin').window('close');" class="easyui-linkbutton" iconCls="icon-back">返回</a>
			<a href="javascript:void(0);" id="btn-add" onclick="addRolePerm();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
    	</div>
	</div>
  </body>
</html>
