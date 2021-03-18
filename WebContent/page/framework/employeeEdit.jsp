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
function saveUser(){
	var userCode = $("#userCode").val();
	var userName = $("#userName").val();
	var roleId = $("#roleId").val();
	var editId = $("#editId").val();
	if(isNotCharMinMax(userCode,1,8)){
		$("#errorMsg").html("员工号:请输入1-8位英文数字");
		return;
	}
	if(isNull(userName)){
		$("#errorMsg").html("员工名必须输入");
		return;
	}
	if(isNull(roleId)){
		$("#errorMsg").html("角色必须选择");
		return;
	}

	if(isNull(editId)){
		document.forms[0].action= "addUser.do?method=addUser";
	}else{
		document.forms[0].action= "editUser.do?method=editUser";
	}	
	document.forms[0].submit();	
}

function pwdReset(){
	if(confirm("确定重置密码")){
		document.forms[0].action= "pwdReset.do?method=pwdReset";
		document.forms[0].submit();	
	}
}

function pageback(){
	document.forms[0].action= "<%=request.getContextPath()%>/searchUser.do?method=searchUser";
	document.forms[0].submit();
}
</script>
</head>
<body style="background-color: #E5F5FF;" >
<html:form method="post">
<html:hidden styleId="editId" name="UserEditForm" property="editId"/>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>员工管理>>员工新增</td>
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
    <td width='12%' class='myEdit'>员工号</td>
		<td>
			<logic:notEmpty name="UserEditForm" property="editId">
				<html:hidden name="UserEditForm" property="userCode"/>
				<html:text styleId="userCode" property="userCode" maxlength="8" disabled="true"></html:text>
			</logic:notEmpty>
			<logic:empty name="UserEditForm" property="editId">
				<html:text styleId="userCode" property="userCode" maxlength="8"></html:text>
			</logic:empty>
		</td>
		<td><span style="color: red;">*</span></td><td width='30%'>&nbsp;</td>
	<td width='12%' class='myEdit'>员工姓名</td><td><html:text styleId="userName" property="userName" maxlength="10"></html:text></td><td><span style="color: red;">*</span></td><td width='30%'>&nbsp;</td> 
  </tr>
  <tr>
    <td width='12%' class='myEdit'>职务</td>
	<td>
		<html:select property="postId" style="width: 125px">
			<html:optionsCollection property="postList" label="postName" value="id"/>
		</html:select>
    </td>
	<td><span style="color: red;">*</span></td><td width='30%'>&nbsp;</td> 
    <td width='12%' class='myEdit'>部门(属)</td>
	<td>
		<html:select property="departId" style="width: 170px">
			<html:optionsCollection property="departmentList" label="departmentName" value="id"/>
		</html:select>
	</td>
	<td><span style="color: red;">*</span></td><td width='30%'>&nbsp;</td>
  </tr>
  <tr>
	<td width='12%' class='myEdit'>角色</td>
	<td rowspan="5">
		<html:select styleId="roleId" property="roleId" style="width: 125px;height: 110px" multiple="multiple">
			<html:optionsCollection property="roleList" label="roleName" value="id"/>
		</html:select>
	</td>
	<td><span style="color: red;">*</span></td><td width='30%'>&nbsp;</td>
	<td width='12%' class='myEdit'>部门(兼)</td>
    <td rowspan="5" colspan="2">
      	<html:select property="userDepartId" style="width: 170px;height: 110px" multiple="multiple">
			<html:optionsCollection property="departmentList" label="departmentName" value="id"/>
		</html:select>
    </td>
    <td width='30%'>&nbsp;</td>
  </tr>
  
  <tr>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
  </tr>
  <tr>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
  </tr>
  <tr>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
  </tr>
  <tr>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
  </tr>
</table>
<table align=center style="background-color: #E5F5FF;">
  <tr>
    <td>
	<html:button property="" styleClass="button" value="保存" onclick="saveUser();"></html:button>
	<logic:notEmpty name="UserEditForm" property="editId">
	<html:button property="" styleClass="button" value="密码重置" onclick="pwdReset();"></html:button>
	</logic:notEmpty>
	<html:button property="" styleClass="button" value="返回" onclick="pageback();"></html:button>
	</td> 
  </tr>
</table>
</html:form>
</body>
</html>