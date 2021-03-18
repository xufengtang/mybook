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
<script language="javascript">
function  saveTelephone(){
	if(confirm("请确认手机号码正确与否，否则无法接收短信通知")){
		if(!doCheck()){
			document.forms[0].action= "saveUserInfoEdit.do?method=saveUserInfoEdit";
			document.forms[0].submit();	
		}else{
			alert($("#errorMsg").html());
		}
	}
}

function doCheck(){
	var ret = false;
	//标题非空检查
	if(isNotNum($("#telephone").val())){
		$("#errorMsg").html("请填写正确的手机号码");
		ret = true;
		return ret;
	}
	return ret;
}
</script>

</head>
<body style="background-color: #E5F5FF;" >
<html:form method="post">
<html:hidden styleId="editId" name="UserInfoEditForm" property="editId"/>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>个人信息>>手机信息修改</td>
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


<table cellpadding='2' cellspacing='1' border='0' class='border' align=center style="background-color: #E5F5FF;">
  <tr>
    <td width='12%' class='myEdit'>手机号码</td>
		<td><html:text styleId="telephone" property="telephone" maxlength="20"></html:text></td>
  </tr>
</table>
<table align=center style="background-color: #E5F5FF;">
  <tr>
    <td>
	<html:button property="" styleClass="button" value="保存" onclick="saveTelephone();"></html:button>
	</td> 
  </tr>
</table>
</html:form>
</body>
</html>