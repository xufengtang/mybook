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
function editRole(roleId,roleName){
	$("#roleName").val(roleName);
	$("#editId").val(roleId);
}
function clearRole(){
	$("#roleName").val("");
	$("#editId").val("");
	$("#infoMsg").html("");
	$("#errorMsg").html("");
}

function saveRole(){
	if(isNull($("#roleName").val())){
		$("#errorMsg").html("角色名不能为空");
		return;
	}
	document.forms[0].action= "saveRole.do?method=saveRole";
	document.forms[0].submit();	
}

function delRole(roleId){
	if(confirm("确认删除")){
		$("#editId").val(roleId);
		document.forms[0].action= "delRole.do?method=delRole";
		document.forms[0].submit();	
	}
}

</script>
</head>
<body style="background-color: #E5F5FF;" >
<html:form method="post">
<html:hidden styleId="editId" property="editId"/>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>系统管理>>角色管理</td>
 </tr>
</table>
<br>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
<tr class='title'>
   <td width='15%'><b>角色名</b></td>
   <td width='21%'><b>操作</b></td>
</tr>
	<logic:iterate id="role" name="RoleForm" property="roleList" indexId="indexid">
	<tr class='titletdbg'>
 		<td width='15%'><bean:write name="role" property="roleName"/></td>
		<td width='21%'><a href="#"  style="color: blue;padding-right: 5px" onclick="editRole('${role.id}','${role.roleName}');" >修改</a><a href="#"  style="color: blue" onclick="delRole('${role.id}');">删除</a></td>  
	</tr>
	</logic:iterate>

</table>
<br>
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

<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'></td>
 </tr>
</table>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center style="background-color: #E5F5FF;">
  <tr>
    <td width='12%' class='myEdit'>角色名</td>
    <td><html:text styleId="roleName" property="roleName" maxlength="10"></html:text><span style="color: red;">*</span><html:button styleClass="button" value="保存" property="" onclick="saveRole();"></html:button><html:button styleClass="button" value="重置" property="" onclick="clearRole();"></html:button></td> 
  </tr>
</table>
</html:form>
</body>
</html>