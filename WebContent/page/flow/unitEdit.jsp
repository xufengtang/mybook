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
function saveUnit(){
	if(isNull($("#unitName").val())){
		$("#errorMsg").html("单元名不能为空");
		return;
	}
	if(isNull($("#orderNum").val())){
		$("#errorMsg").html("排序号不能为空");
		return;
	}
	if(isNotNum($("#orderNum").val())){
		$("#errorMsg").html("排序号必须数字");
		return;
	}
	document.forms[0].action= "saveUnit.do?method=saveUnit";
	document.forms[0].submit();	
}
function goback(){
	document.forms[0].action= "<%=request.getContextPath()%>/searchUnitInit.do?method=searchUnitInit";
	document.forms[0].submit();
	
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
    <td width='10%' class='myEdit'>级别</td>
    <td width='10%'  >
	    <html:select property="levelName" styleId="levelName">
	     <html:option value="1">一级语法</html:option>
	     <html:option value="2">二级语法</html:option>
		 <html:option value="3">三级语法</html:option>
	     <html:option value="4">四级语法</html:option>
	     <html:option value="5">五级语法</html:option>
	    </html:select><span style="color: red;">*</span>
    </td>
    <td width='10%' class='myEdit'>单元名</td>
    <td width='10%' ><html:text styleId="unitName" property="unitName" maxlength="20"></html:text><span style="color: red;">*</span></td> 
     <td class='myEdit' width='10%' >排序号</td>
     <td width='10%' ><html:text styleId="orderNum" property="orderNum" maxlength="20"></html:text><span style="color: red;">*</span></td> 
     <td width='10%' ><html:button styleClass="button" value="保存" property="" onclick="saveUnit();"></html:button><html:button styleClass="button" value="返回" property="" onclick="goback();"></html:button></td>
	 <td width='20%' ></td>
  </tr>
</table>
</html:form>
</body>
</html>