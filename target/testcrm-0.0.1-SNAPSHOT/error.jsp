<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% 
response.setHeader( "Cache-Control",   "no-store"); 
response.setHeader( "Pragma",   "no-cache"); 
response.setDateHeader( "Expires",   0); 
%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML><HEAD><TITLE>提示信息 </TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<STYLE type=text/css>
BODY {
	PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FONT-SIZE: 12px; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-TOP: 0px
}
A {
	COLOR: #1b72af; TEXT-DECORATION: none
}
A:hover {
	TEXT-DECORATION: underline
}
OL {
	MARGIN-LEFT: 24px; COLOR: #888; LIST-STYLE-TYPE: decimal
}
.fl {
	FLOAT: left
}
.fr {
	FLOAT: right
}
.tar {
	TEXT-ALIGN: right
}
.mt10 {
	MARGIN-TOP: 10px
}
.mr20 {
	MARGIN-RIGHT: 20px
}
.mb20 {
	MARGIN-BOTTOM: 20px
}
.lh2 {
	LINE-HEIGHT: 2
}
.vt {
	VERTICAL-ALIGN: top
}
.tip-page {
	MARGIN: 100px auto 30px; WIDTH: 600px
}
.tip-table {
	BACKGROUND: #ffffff; MARGIN: 0px 1px; WIDTH: 598px
}
.tip-top {
	BACKGROUND: url(<%=basePath%>images/tip-top.png) no-repeat center 50%; HEIGHT: 50px
}
.tip-bgA {
	BACKGROUND: #1b72af
}
.tip-bgB {
	BACKGROUND: #1b72af
}
.tip-bgC {
	BACKGROUND: #1b72af
}
.tip-bgC {
	BACKGROUND: #1b72af
}
.tip-bgA {
	OVERFLOW: hidden; HEIGHT: 1px
}
.tip-bgB {
	OVERFLOW: hidden; HEIGHT: 1px
}
.tip-bgA {
	MARGIN: 0px 2px
}
.tip-bgB {
	MARGIN: 0px 1px
}
.tip-bgC {
	PADDING-TOP: 1px
}
.tip-content {
	PADDING-RIGHT: 0px; PADDING-LEFT: 67px; PADDING-BOTTOM: 0px; PADDING-TOP: 0px
}
.tip-content TR TD {
	PADDING-RIGHT: 10px; PADDING-LEFT: 0px; PADDING-BOTTOM: 5px; LINE-HEIGHT: 25px; PADDING-TOP: 5px
}
.show-back {
	PADDING-RIGHT: 25px; DISPLAY: block; BACKGROUND: url(<%=basePath%>images/tip-top.png) no-repeat right 0px; FLOAT: left; OVERFLOW: hidden; LINE-HEIGHT: 22px; HEIGHT: 20px; TEXT-DECORATION: underline
}
.show-back:hover {
	BACKGROUND-POSITION: right -20px
}
.btn {
	BORDER-LEFT-COLOR: #1b72af; BACKGROUND: #1b72af; BORDER-BOTTOM-COLOR: #1b72af; COLOR: #fff; BORDER-TOP-COLOR: #1b72af; MARGIN-RIGHT: 1em; BORDER-RIGHT-COLOR: #1b72af
}
.btn {
	BORDER-TOP-WIDTH: 1px; PADDING-RIGHT: 1em; PADDING-LEFT: 1em; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 9pt; BORDER-BOTTOM-WIDTH: 1px; PADDING-BOTTOM: 0px; OVERFLOW: visible; CURSOR: pointer; LINE-HEIGHT: 130%; PADDING-TOP: 0px; BORDER-RIGHT-WIDTH: 1px
}
.bt {
	BORDER-TOP-WIDTH: 1px; PADDING-RIGHT: 1em; PADDING-LEFT: 1em; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 9pt; BORDER-BOTTOM-WIDTH: 1px; PADDING-BOTTOM: 0px; OVERFLOW: visible; CURSOR: pointer; LINE-HEIGHT: 130%; PADDING-TOP: 0px; BORDER-RIGHT-WIDTH: 1px
}
.bt {
	BORDER-LEFT-COLOR: #e4e4e4; BACKGROUND: url(images/wind/btn.png) #f7f7f7 repeat-x 0px -52px; BORDER-BOTTOM-COLOR: #cccccc; VERTICAL-ALIGN: middle; CURSOR: pointer; BORDER-TOP-COLOR: #e4e4e4; BORDER-RIGHT-COLOR: #cccccc
}
INPUT {
	PADDING-RIGHT: 3px; PADDING-LEFT: 3px; MARGIN-BOTTOM: 1px; PADDING-BOTTOM: 0px; FONT: 12px Arial; VERTICAL-ALIGN: middle; PADDING-TOP: 1px
}
.input {
	BORDER-RIGHT: #ededed 1px solid; PADDING-RIGHT: 0px; BORDER-TOP: #c0c0c0 1px solid; PADDING-LEFT: 1px; FONT-SIZE: 1em; PADDING-BOTTOM: 2px; VERTICAL-ALIGN: middle; BORDER-LEFT: #c0c0c0 1px solid; COLOR: #000; PADDING-TOP: 2px; BORDER-BOTTOM: #ededed 1px solid
}
</STYLE>


</HEAD>
<BODY>
<DIV class=tip-page>
<DIV class=tip-bgA></DIV>
<DIV class=tip-bgB></DIV>
<DIV class=tip-bgC>
<DIV class=tip-top></DIV>
<TABLE class=tip-table cellSpacing=0 cellPadding=0>
  <TBODY>
  <TR>
    <TD height=170>
      <DIV class=tip-content>
      

      <DIV>对不起！您没有登录或者您没有权限访问此页面，可能有如下几个原因:</DIV>
      <DIV class=mt10>
      <OL>
        
        <LI>读取数据错误,原因：您要访问的链接无效,可能链接不完整,或数据已被删除! 
        <LI>您还不是站点会员,请先登录站点<BR></LI></OL></DIV><br><DIV class="tar cc mb20">
        <A class=mr20 tabIndex=20 
      href="javascript:history.go(-1)">返回</A>
        <A class=mr20 id=showindex tabIndex=20 
      href="login.jsp" target="top">登录</A> 
</DIV></DIV></TD></TR></TBODY></TABLE></DIV>
<DIV class=tip-bgB></DIV></DIV></BODY></HTML>
