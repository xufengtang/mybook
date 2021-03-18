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
var levelName = '${UnitSearchForm.levelNameSearch}';
function unitAdd(){
	document.forms[0].action= "<%=request.getContextPath()%>/initUnit.do?method=initUnit";
	document.forms[0].submit();
}

function unitEdit(id){
	document.forms[0].action= "<%=request.getContextPath()%>/initUnit.do?method=initUnit&editId=" + id;
	document.forms[0].submit();	
}

function unitDelete(id){
	if(confirm("确定删除")){
		document.forms[0].action= "<%=request.getContextPath()%>/deleteUnit.do?method=deleteUnit&editId=" + id;
		document.forms[0].submit();
	}
}

function unitSearch(){
	document.forms[0].action= "<%=request.getContextPath()%>/searchUnit.do?method=searchUnit";
	document.forms[0].submit();
}
</script>
</head>
<body style="background-color: #E5F5FF;">

<html:form method="post">
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>员工管理>>员工查询</td>
 </tr>
 <tr>
  <td class='Navigationtdbg'>
  <table style="width: 100%">
	<tr>
		<td width='15%' class='searchTitle'>日语等级</td>
		<td width='15%'>
		<html:select styleId="levelNameSearch" property="levelNameSearch" style="width: 170px">
	     <html:option value="1">一级语法</html:option>
	     <html:option value="2">二级语法</html:option>
		 <html:option value="3">三级语法</html:option>
	     <html:option value="4">四级语法</html:option>
	     <html:option value="5">五级语法</html:option>
		</html:select>
		</td>
		<td width='15%'><html:button styleClass="button" property="" value="查询" onclick="unitSearch();"></html:button><html:button styleClass="button" property="" value="新增" onclick="unitAdd();"></html:button></td>
		<td width='55%'></td>
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
   <td width='25%'><b>日语等级</b></td>
   <td width='25%'><b>单元名称</b></td>
   <td width='25%'><b>排序号</b></td>
   <td width='25%'><b>操作</b></td>
</tr>
<logic:notEmpty name="UnitSearchForm" property="unitBeanList">
<logic:iterate id="unitBean" name="UnitSearchForm" property="unitBeanList" indexId="indexid">
	<tr class='titletdbg'>
	   <td><bean:write name="unitBean" property="levelName"/></td>
	   <td><bean:write name="unitBean" property="unitName"/></td>
	   <td><bean:write name="unitBean" property="orderNum"/></td>
	   <td><a href="#"  style="color: blue;padding-right: 5px" onclick="unitEdit('${unitBean.id}');">修改</a><a href="#"  style="color: blue" onclick="unitDelete('${unitBean.id}');">删除</a></td>  
	</tr>
</logic:iterate>
</logic:notEmpty>
</table>
</html:form>
</body>
</html>