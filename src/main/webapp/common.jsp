<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String cpath = request.getContextPath();
String cbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cpath+"/";
%>

<link rel="stylesheet" type="text/css" href="<%=cbasePath%>css/msg.css">
<link rel="stylesheet" type="text/css" href="<%=cbasePath%>js/loadmask/jquery.loadmask.css">
<link rel="stylesheet" type="text/css" href="<%=cbasePath%>js/jquery-easyui-1.2.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=cbasePath%>js/jquery-easyui-1.2.2/themes/icon.css">
<script type="text/javascript" src="<%=cbasePath%>js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=cbasePath%>js/json.min.js"></script>
<script type="text/javascript" src="<%=cbasePath%>js/loadmask/jquery.loadmask.js"></script>
<script type="text/javascript" src="<%=cbasePath%>js/jquery-easyui-1.2.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=cbasePath%>js/jquery-easyui-1.2.2/locale/easyui-lang-zh_CN.js"></script>

