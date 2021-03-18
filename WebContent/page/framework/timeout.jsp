<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet type=text/css href="<%=request.getContextPath()%>/css/HX_Style.css">
<link rel=stylesheet type=text/css href="<%=request.getContextPath()%>/css/Css.css">
<title>错误页面</title>
<script type="text/javascript">
function goLogin(){
	if(window.parent!=null){
		window.parent.location = "http://localhost:8081/MYBOOK";
	}else{
		window.location = "http://localhost:8081/MYBOOK";
	}
	
}
</script>
</head>
<body style="background-color: #E5F5FF;">
<form>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>错误页面</td>
 </tr>
</table>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center style="background-color: #E5F5FF;">
<tr><td align="center" style="color: red;font-size: large">连接已经超时，请重新登陆系统</td></tr>
<tr><td align="center"><input type="button" Class="button" value="跳转到登录" onclick="goLogin();"/></td></tr>
</table>


</form>


</body>
</html>