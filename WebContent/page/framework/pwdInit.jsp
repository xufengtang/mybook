<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet type=text/css href="<%=request.getContextPath()%>/css/HX_Style.css">
<link rel=stylesheet type=text/css href="<%=request.getContextPath()%>/css/Css.css">
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript">
var status = '${PwdInitForm.status}';
function pwdChange(){
	document.forms[0].submit();
}

$(document).ready(function(){
	// 保存成功跳转到查询页面
	if(status=="success"){
		alert("初始密码修改成功，请重新登录!");
		document.forms[0].action= "<%=request.getContextPath()%>/login.jsp";
		document.forms[0].submit();
		//window.location = "";
	}
});
</script>

</head>
<body style="background-color: #E5F5FF;" >
<html:form method="post" action="pwdInit.do">
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>个人信息>>密码修改</td>
 </tr>
</table>
<p style="color: blue;font-size: medium;"><b>&nbsp;&nbsp;需修改初始密码才能访问系统</b></p>
<br>
<table>
 <tr>
  <td>
	<span id="infoMsg" style="color: blue;">
	<html:messages id="infoMsg" message="true">
	<bean:write name="infoMsg"/>
	</html:messages>
	</span>
	<span id="errorMsg" style="color: red;">
	<html:errors/>
	</span>
  </td>
 </tr>
</table>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center style="background-color: #E5F5FF;">
  <tr>
	<td width='15%'  class='title'><b>旧密码</b></td>
    <td>
	  <html:password styleId="oldPwd" property="oldPwd" maxlength="15"></html:password><span style="color: red">*</span>
    </td> 
  </tr>
  <tr>
	<td width='15%'  class='title'><b>新密码</b></td>
    <td>
	  <html:password  styleId="newPwd" property="newPwd" maxlength="15"></html:password><span style="color: red">*</span>
    </td> 
  </tr>
  <tr>
	<td width='15%'  class='title'><b>确认密码</b></td>
    <td>
	  <html:password styleId="surePwd" property="surePwd" maxlength="15"></html:password><span style="color: red">*</span>
    </td> 
  </tr>
</table>
<table align=center style="background-color: #E5F5FF;">
  <tr>
    <td>
    <html:button styleClass="button" property="" value="修改" onclick="pwdChange();"></html:button>
	</td> 
  </tr>
</table>
</html:form>
</body>
</html>