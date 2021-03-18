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
function saveMovieInfo(){
	if(isNull($("#movieName").val())){
		$("#errorMsg").html("视频名称不能为空");
		return;
	}

	if(isNull($("#orderNum").val())){
		$("#errorMsg").html("排序号不能为空");
		return;
	}
	if(isNull($("#movieUrl").val())){
		$("#errorMsg").html("URL不能为空");
		return;
	}
	
	document.forms[0].action= "saveMovieInfo.do?method=saveMovieInfo";
	document.forms[0].submit();	
}

function goback(){
	document.forms[0].action= "<%=request.getContextPath()%>/searchMovieInfo.do?method=searchMovieInfo";
	document.forms[0].submit();
	
}
</script>
</head>
<body style="background-color: #E5F5FF;" >
<html:form method="post">
<html:hidden styleId="editId" property="editId"/>
<html:hidden styleId="movieTypeSearch" property="movieTypeSearch"/>
<html:hidden styleId="currentPage" property="currentPage"/>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>视频管理>>视频编辑</td>
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
    <td width='10%' class='myEdit'>视频种类</td>
    <td width='10%'  >
		<html:select styleId="movieType" property="movieType"  name="MovieInfoEditForm"  style="width: 170px">
		<html:optionsCollection name="MovieInfoEditForm"  property="movieTypeList"  label="dictName" value="dictCode" />	    
		</html:select><span style="color: red;">*</span>
    </td>
    <td class='myEdit' width='10%' >视频名称</td>
    <td width='30%' >
       <html:text styleId="movieName" property="movieName" maxlength="100"  size="50"></html:text><span style="color: red;">*</span>
    </td>
    <td class='myEdit' width='10%' >排序号</td>
    <td width='10%' >
       <html:text styleId="orderNum" property="orderNum" ></html:text><span style="color: red;">*</span>
    </td>

  </tr>
  <tr>
    <td class='myEdit' width='10%' >视频URL</td>
    <td width='10%' colspan="5">
      <html:text styleId="movieUrl" property="movieUrl"    maxlength="500" size="250"></html:text><span style="color: red;">*</span>
    </td>
   </tr>
  <tr>
	  <td width='10%' ><html:button styleClass="button" value="保存" property="" onclick="saveMovieInfo();"></html:button><html:button styleClass="button" value="返回" property="" onclick="goback();"></html:button></td>
	  <td width='90%' colspan="7"></td>
  </tr>

</table>
</html:form>
</body>
</html>