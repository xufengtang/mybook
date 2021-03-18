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
 function saveUrl(){
	var nhkUrl = $("#urlName").val();
	if(isNull(nhkUrl)){
		alert("地址不能为空");
		return;
	}
	document.forms[0].action= "<%=request.getContextPath()%>/saveUrl.do?method=saveUrl";
	document.forms[0].submit();
}
</script>
</head>
<body style="background-color: #E5F5FF;" >
<html:form method="post">
<html:hidden styleId="editId" property="editId"/>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>系统管理>>NHK管理</td>
 </tr>
</table>
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

<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>

<tr class='title'>
   <td width='30%'><b>ID</b></td>
   <td width='40%'><b>地址</b></td>
   <td width='30%'><b>操作</b></td>
</tr>
 <tr>
    <td>
    <bean:write  name="NHKInfoForm" property="editId" />
    </td>
    <td>
    <html:text styleId="urlName"   name="NHKInfoForm"  property="urlName"  size="200" maxlength="500"></html:text><span style="color: red;">*</span>
    </td>
	<td>
	<html:button styleClass="button" property="" value="更新" onclick="saveUrl();"></html:button>
	</td>
 </tr>
</table>
</html:form>
</body>
</html>