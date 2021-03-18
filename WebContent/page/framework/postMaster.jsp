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
function savePost(){
	if(isNull($("#postCode").val())){
		$("#errorMsg").html("职务编号不能为空");
		return;
	}
	if(isNull($("#postName").val())){
		$("#errorMsg").html("职务名不能为空");
		return;
	}
	if(isNotNumLen($("#postCode").val(),3)){
		$("#errorMsg").html("职务编号:请输入3位数字");
		return;	
	}
	document.forms[0].action= "savePost.do?method=savePost";
	document.forms[0].submit();	
}

function editPost(postId,postCode,postName){
	$("#postCode").val(postCode);
	$("#postName").val(postName);
	$("#postCode").attr("disabled","true");
	$("#editId").val(postId);
}

function delPost(postId){
	if(confirm("确认删除")){
		$("#editId").val(postId);
		document.forms[0].action= "delPost.do?method=delPost";
		document.forms[0].submit();	
	}
}

function clearPost(){
	$("#postCode").val("");
	$("#postName").val("");
	$("#postCode").removeAttr("disabled");
	$("#editId").val("");
	$("#infoMsg").html("");
	$("#errorMsg").html("");
}
</script>
</head>
<body style="background-color: #E5F5FF;" >
<html:form method="post">
<html:hidden styleId="editId" property="editId"/>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>系统管理>>职务管理</td>
 </tr>
</table>
<br>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
<tr class='title'>
   <td width='12%'><b>职务编号</b></td>
   <td width='15%'><b>职务名</b></td>
   <td width='21%'><b>操作</b></td>
</tr>
<logic:notEmpty name="PostForm" property="postList">
	<logic:iterate id="post" name="PostForm" property="postList" indexId="indexid">
		<tr class='titletdbg'>
			<td width='12%'><bean:write name="post" property="postCode"/></td>
			<td width='15%'><bean:write name="post" property="postName"/></td>
		    <td width='21%'><a href="#"  style="color: blue;padding-right: 5px" onclick="editPost('${post.id}','${post.postCode}','${post.postName}');" >修改</a><a href="#"  style="color: blue" onclick="delPost('${post.id}');">删除</a></td>  
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
    <td width='12%' class='myEdit'>职务编号</td>
    <td><html:text styleId="postCode" property="postCode" maxlength="10"></html:text><span style="color: red;">*</span><html:button styleClass="button" value="保存" property="" onclick="savePost();"></html:button></td>
  </tr>
  <tr>
    <td width='12%' class='myEdit'>职务名</td>
    <td><html:text styleId="postName" property="postName" maxlength="10"></html:text><span style="color: red;">*</span><html:button styleClass="button" value="重置" property="" onclick="clearPost();"></html:button></td> 
  </tr>
</table>
</html:form>
</body>
</html>