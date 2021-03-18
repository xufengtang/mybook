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
var userCode = '${SearchUserForm.userCodeSearch}';
var userName = '${SearchUserForm.userNameSearch}';
var departId = '${SearchUserForm.departIdSearch}';
var totalPage = '${SearchUserForm.totalPage}';
function employeeAdd(){
	document.forms[0].action= "<%=request.getContextPath()%>/initEditUser.do?method=initEditUser";
	document.forms[0].submit();
}

function employeeEdit(id){
	document.forms[0].action= "<%=request.getContextPath()%>/initEditUser.do?method=initEditUser&editId=" + id;
	document.forms[0].submit();	
}

function employeeDelete(id){
	if(confirm("确定删除")){
		document.forms[0].action= "<%=request.getContextPath()%>/deleteUser.do?method=deleteUser&editId=" + id;
		document.forms[0].submit();
	}
}

function employeeSearch(){
	$("#currentPage").val(1);
	document.forms[0].action= "<%=request.getContextPath()%>/searchUser.do?method=searchUser";
	document.forms[0].submit();
}
function prePage(){
	$("#currentPage").val(parseInt($("#currentPage").val())-1);
	$("#userCode").val(userCode);
	$("#userName").val(userName);
	$("#departId").val(departId);
	document.forms[0].action= "<%=request.getContextPath()%>/searchUser.do?method=searchUser";
	document.forms[0].submit();
}
function nextPage(){
	$("#currentPage").val(parseInt($("#currentPage").val())+1);
	$("#userCode").val(userCode);
	$("#userName").val(userName);
	$("#departId").val(departId);
	document.forms[0].action= "<%=request.getContextPath()%>/searchUser.do?method=searchUser";
	document.forms[0].submit();
}
function gotoPage(){
	if(isNotPageNum($("#goPage").val())){
		$("#errorMsg").html("请输入正确页码");
		return;
	}
	if($("#goPage").val()>totalPage){
		$("#errorMsg").html("请输入正确页码");
		return;
	}
	$("#currentPage").val($("#goPage").val());
	$("#userCode").val(userCode);
	$("#userName").val(userName);
	$("#departId").val(departId);
	document.forms[0].action= "<%=request.getContextPath()%>/searchUser.do?method=searchUser";
	document.forms[0].submit();
}
</script>
</head>
<body style="background-color: #E5F5FF;">

<html:form method="post">
<html:hidden styleId="currentPage" name="SearchUserForm" property="currentPage"/>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>员工管理>>员工查询</td>
 </tr>
 <tr>
  <td class='Navigationtdbg'>
  <table style="width: 100%">
	<tr>
		<td width='10%' class='searchTitle'>员工号</td>
		<td width='12%'><html:text styleId="userCode" property="userCodeSearch"/></td>
		<td width='10%' class='searchTitle'>员工姓名</td>
		<td width='12%'><html:text styleId="userName" property="userNameSearch"/></td>
		<td width='10%' class='searchTitle'>部门(属)</td>
		<td width='12%'>
		<html:select styleId="departId" property="departIdSearch" style="width: 170px">
		<html:optionsCollection property="departmentList" label="departmentName" value="id" />
		</html:select>
		</td>
		<td width='15%'><html:button styleClass="button" property="" value="查询" onclick="employeeSearch();"></html:button><html:button styleClass="button" property="" value="新增" onclick="employeeAdd();"></html:button></td>
		<td width='19%'></td>
    </tr>
  </table>
  </td>
 </tr>
</table>
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
<tr class='title'>
   <td width='8%'><b>员工号</b></td>
   <td width='10%'><b>姓名</b></td>
   <td width='10%'><b>职务</b></td>
   <td width='13%'><b>部门(属)</b></td>
   <td width='25%'><b>部门(兼)</b></td>
   <td width='25%'><b>角色</b></td>
   <td width='9%'><b>操作</b></td>
</tr>
<logic:notEmpty name="SearchUserForm" property="userList">
<logic:iterate id="user" name="SearchUserForm" property="userList" indexId="indexid">
	<tr class='titletdbg'>
	   <td width='8%'><bean:write name="user" property="userCode"/></td>
	   <td width='10%'><bean:write name="user" property="userName"/></td>
	   <td width='10%'><bean:write name="user" property="postName"/></td>
	   <td width='13%'><bean:write name="user" property="departName"/></td>
	   <td width='25%'><bean:write name="user" property="userDepartName"/></td>
	   <td width='25%'><bean:write name="user" property="roleName"/></td>
	   <td width='9%'><a href="#"  style="color: blue;padding-right: 5px" onclick="employeeEdit('${user.id}');">修改</a><a href="#"  style="color: blue" onclick="employeeDelete('${user.id}');">删除</a></td>  
	</tr>
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="SearchUserForm" property="userList">
<tr>
 <td style="background-color: white;text-align: center"  colspan="7">
<logic:equal name="SearchUserForm" property="currentPage" value="1">
<html:button styleClass="button" property="" value="上一页" onclick="" disabled="true"></html:button>
</logic:equal>
<logic:notEqual name="SearchUserForm" property="currentPage"  value="1">
<html:button styleClass="button" property="" value="上一页" onclick="prePage();" disabled="false"></html:button>
</logic:notEqual>
&nbsp;&nbsp; <bean:write name="SearchUserForm" property="currentPage"/>/<bean:write name="SearchUserForm" property="totalPage"/>
<logic:equal  name="SearchUserForm" property="currentPage" value="${SearchUserForm.totalPage}">
<html:button styleClass="button" property="" value="下一页" onclick="" disabled="true"></html:button>
</logic:equal>
<logic:notEqual name="SearchUserForm" property="currentPage" value="${SearchUserForm.totalPage}">
<html:button styleClass="button" property="" value="下一页" onclick="nextPage();" disabled="false"></html:button>
</logic:notEqual>

&nbsp;<html:text styleId="goPage" property="goPage" size="3" maxlength="3"></html:text><html:button styleClass="button" property="" value="跳转" onclick="gotoPage();"></html:button>
 </td>
</tr>
</logic:notEmpty>
</table>
</html:form>
</body>
</html>