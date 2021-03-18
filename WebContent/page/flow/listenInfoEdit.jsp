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
function saveListenInfo(){
	if(isNull($("#listenName").val())){
		$("#errorMsg").html("听力名称不能为空");
		return;
	}

	if(isNull($("#orderNum").val())){
		$("#errorMsg").html("排序号不能为空");
		return;
	}
	if(isNull($("#listenUrl").val())){
		$("#errorMsg").html("URL不能为空");
		return;
	}
	
	document.forms[0].action= "saveListenInfo.do?method=saveListenInfo";
	document.forms[0].submit();	
}

function goback(){
	document.forms[0].action= "<%=request.getContextPath()%>/searchListenInfo.do?method=searchListenInfo";
	document.forms[0].submit();
	
}
</script>
</head>
<body style="background-color: #E5F5FF;" >
<html:form method="post">
<html:hidden styleId="editId" property="editId"/>
<html:hidden styleId="levelIdSearch" property="levelIdSearch"/>
<html:hidden styleId="currentPage" property="currentPage"/>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>听力录音管理>>听力录音编辑</td>
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
    <td width='10%' class='myEdit'>听力种类</td>
    <td width='10%'  >
		<html:select styleId="levelId" property="levelId"  name="ListenInfoEditForm"  style="width: 170px">
		<html:optionsCollection name="ListenInfoEditForm"  property="levelList"  label="dictName" value="dictCode" />	    
		</html:select><span style="color: red;">*</span>
    </td>
    <td class='myEdit' width='10%' >听力名称</td>
    <td width='30%' >
       <html:text styleId="listenName" property="listenName" maxlength="100"  size="50"></html:text><span style="color: red;">*</span>
    </td>
    <td class='myEdit' width='10%' >排序号</td>
    <td width='10%' >
       <html:text styleId="orderNum" property="orderNum" ></html:text><span style="color: red;">*</span>
    </td>

  </tr>
  <tr>
    <td class='myEdit' width='10%' >听力URL</td>
    <td width='10%' colspan="5">
      <html:text styleId="listenUrl" property="listenUrl"    maxlength="500" size="250"></html:text><span style="color: red;">*</span>
    </td>
   </tr>
  <tr>
	  <td width='10%' ><html:button styleClass="button" value="保存" property="" onclick="saveListenInfo();"></html:button><html:button styleClass="button" value="返回" property="" onclick="goback();"></html:button></td>
	  <td width='90%' colspan="7"></td>
  </tr>

</table>
</html:form>
</body>
</html>