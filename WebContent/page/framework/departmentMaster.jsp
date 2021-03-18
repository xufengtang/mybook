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
function editDepart(departId,departCode,departName,bankCode){
	$("#departmentCode").val(departCode);
	$("#departmentName").val(departName);
	$("#bankCode").val(bankCode);
	$("#departmentCode").attr("disabled","true");
	$("#bankCode").attr("disabled","true");
	$("#editId").val(departId);
}

function clearDepart(){
	$("#bankCode").val("");
	$("#departmentCode").val("");
	$("#departmentName").val("");
	$("#departmentCode").removeAttr("disabled");
	$("#bankCode").removeAttr("disabled");
	$("#editId").val("");
	$("#infoMsg").html("");
	$("#errorMsg").html("");
}

function saveDepart(){
	if(isNull($("#bankCode").val())){
		$("#errorMsg").html("金融机构编号不能为空");
		return;
	}
	if(isNull($("#departmentCode").val())){
		$("#errorMsg").html("部门编号不能为空");
		return;
	}
	if(isNull($("#departmentName").val())){
		$("#errorMsg").html("部门名不能为空");
		return;
	}
	$("#departmentCode").removeAttr("disabled");
	$("#bankCode").removeAttr("disabled");
	document.forms[0].action= "saveDepart.do?method=saveDepart";
	document.forms[0].submit();	
}

function delDepart(departId){
	if(confirm("确认删除")){
		$("#editId").val(departId);
		document.forms[0].action= "delDepart.do?method=delDepart";
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
  <td class='Navigation'>系统管理>>部门管理</td>
 </tr>
</table>
<br>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
<tr class='title'>
   <td width='12%'><b>金融机构编码</b></td>
   <td width='12%'><b>部门编号</b></td>
   <td width='15%'><b>部门名</b></td>
   <td width='21%'><b>操作</b></td>
</tr>
<logic:notEmpty name="DepartmentForm" property="departList">
	<logic:iterate id="depart" name="DepartmentForm" property="departList" indexId="indexid">
		<tr class='titletdbg'>
            <td width='12%'><bean:write name="depart" property="bankCode"/></td>
   			<td width='12%'><bean:write name="depart" property="departmentCode"/></td>
   			<td width='15%'><bean:write name="depart" property="departmentName"/></td>
  			<td width='21%'><a href="#"  style="color: blue;padding-right: 5px" onclick="editDepart('${depart.id}','${depart.departmentCode}','${depart.departmentName}','${depart.bankCode}');" >修改</a><a href="#"  style="color: blue" onclick="delDepart('${depart.id}');">删除</a></td>  
		</tr>
	</logic:iterate>
</logic:notEmpty>
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
    <td width='12%' class='myEdit'>金融机构编码</td>
    <td><html:text styleId="bankCode" property="bankCode" maxlength="10"></html:text><span style="color: red;">*</span></td>
  </tr>
  <tr>
    <td width='12%' class='myEdit'>部门编号</td>
    <td><html:text styleId="departmentCode" property="departmentCode" maxlength="10"></html:text><span style="color: red;">*</span><html:button styleClass="button" value="保存" property="" onclick="saveDepart();"></html:button></td>
  </tr>
  <tr>
    <td width='12%' class='myEdit'>部门名</td>
    <td><html:text styleId="departmentName" property="departmentName" maxlength="10"></html:text><span style="color: red;">*</span><html:button styleClass="button" value="重置" property="" onclick="clearDepart();"></html:button></td> 
  </tr>
</table>
</html:form>
</body>
</html>