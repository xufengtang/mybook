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
function changeRank(value){
	if(value=='1'){
		$("#parentMenu").attr("disabled","true");
		$("#url").attr("disabled","true");
	}else{
		$("#parentMenu").removeAttr("disabled");
		$("#url").removeAttr("disabled");
	}
}
function editMenu(menuId,orderNo,menuName,rank,parentId,url){
	$("#orderNo").val(orderNo);
	$("#menuName").val(menuName);
	$("#rank").val(rank);
	$("#parentMenu").val(parentId);
	$("#url").val(url);
	if(rank=='1'){
		$("#parentMenu").attr("disabled","true");
		$("#url").attr("disabled","true");
	}else{
		$("#parentMenu").removeAttr("disabled");
		$("#url").removeAttr("disabled");
	}
	$("#editId").val(menuId);
}

function saveMenu(){
	if(isNull($("#orderNo").val())){
		$("#errorMsg").html("顺序号不能为空");
		return;		
	}else if(isNotNumMinMax($("#orderNo").val(),1,3)){
		$("#errorMsg").html("顺序号为3位以下数字");
		return;		
	}
	if(isNull($("#menuName").val())){
		$("#errorMsg").html("菜单名不能为空");
		return;		
	}
	if($("#rank").val()=="2" && isNull($("#url").val())){
		$("#errorMsg").html("2级时,URL不能为空");
		return;		
	}
	document.forms[0].action= "saveMenu.do?method=saveMenu";
	document.forms[0].submit();	
}

function clearMenu(){
	$("#orderNo").val("");
	$("#menuName").val("");
	$("#rank").val("1");
	//alert($("#parentMenu").options[0]);
	//document.getElementById("parentMenu").options[0].selected = true;
	$("#parentMenu").attr("selectedindex",0);
	$("#parentMenu").attr("disabled","true");
	$("#url").attr("disabled","true");
	$("#url").val("");
	$("#editId").val("");
	$("#infoMsg").html("");
	$("#errorMsg").html("");
}

function delMenu(menuId){
	if(confirm("确认删除")){
		$("#editId").val(menuId);
		document.forms[0].action= "delMenu.do?method=delMenu";
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
  <td class='Navigation'>系统管理>>菜单管理</td>
 </tr>
</table>
<br>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
<tr class='title'>
   <td width='5%'><b>顺序号</b></td>
   <td width='12%'><b>菜单名</b></td>
   <td width='10%'><b>级别</b></td>
   <td width='12%'><b>父级</b></td>
   <td width='21%'><b>URL</b></td>
   <td width='21%'><b>操作</b></td>
</tr>
	<logic:notEmpty name="MenuForm" property="menuList">
		<logic:iterate id="menu" name="MenuForm" property="menuList" indexId="indexid">
			<logic:equal name="menu" property="rank" value="1">
			<tr class='titletdbg' style="background-color: yellow;">
		  		<td width='5%'><bean:write name="menu" property="orderNo"/></td>
		  		<td width='12%'><bean:write name="menu" property="menuName"/></td>
				<td width='10%'><bean:write name="menu" property="rank"/>级</td>
				<td width='12%'><bean:write name="menu" property="parentName"/></td>
				<td width='21%'><bean:write name="menu" property="url"/></td>
		 		<td width='21%'><a href="#"  style="color: blue;padding-right: 5px" onclick="editMenu('${menu.menuId}','${menu.orderNo}','${menu.menuName}','${menu.rank}','${menu.parentId}','${menu.url}');" >修改</a><a href="#"  style="color: blue" onclick="delMenu('${menu.menuId}');">删除</a></td>  
			</tr>
			</logic:equal>
			<logic:equal name="menu" property="rank" value="2">
			<tr class='titletdbg'>
		  		<td width='5%'><bean:write name="menu" property="orderNo"/></td>
		  		<td width='12%'><bean:write name="menu" property="menuName"/></td>
				<td width='10%'><bean:write name="menu" property="rank"/>级</td>
				<td width='12%'><bean:write name="menu" property="parentName"/></td>
				<td width='21%'><bean:write name="menu" property="url"/></td>
		 		<td width='21%'><a href="#"  style="color: blue;padding-right: 5px" onclick="editMenu('${menu.menuId}','${menu.orderNo}','${menu.menuName}','${menu.rank}','${menu.parentId}','${menu.url}');" >修改</a><a href="#"  style="color: blue" onclick="delMenu('${menu.menuId}');">删除</a></td>  
			</tr>
			</logic:equal>
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
    <td width='12%' class='myEdit'>顺序号</td><td><html:text styleId="orderNo" property="orderNo" maxlength="3"></html:text><span style="color: red;">*</span></td>
	<td width='12%' class='myEdit'>菜单名</td><td><html:text styleId="menuName" property="menuName" size="30" maxlength="15"></html:text><span style="color: red;">*</span></td> 
  </tr>
  <tr>
    <td width='12%' class='myEdit'>级别</td><td>
    <html:select  styleId="rank" property="rank" style="width: 120px" onchange="changeRank(this.value);">
        <html:option value="1">1级</html:option>
		<html:option value="2">2级</html:option>
	 </html:select> 
    </td> 
    <td width='12%' class='myEdit'>父级</td><td>
	    <html:select styleId="parentMenu" property="parentId" style="width: 120px"  disabled="true">
        <html:optionsCollection property="parentMenuList" label="menuName" value="id"/>
    	</html:select>
	</td>
  </tr>
  <tr>
    <td width='12%' class='myEdit'>URL</td><td><html:text styleId="url" property="url" size="40" maxlength="150" disabled="true"></html:text></td>
  </tr>
</table>
<table align=center style="background-color: #E5F5FF;">
  <tr>
    <td>
	<html:button styleClass="button" value="保存" property="" onclick="saveMenu();"></html:button><html:button styleClass="button" value="重置" property="" onclick="clearMenu();"></html:button>
	</td> 
  </tr>
</table>
</html:form>
</body>
</html>