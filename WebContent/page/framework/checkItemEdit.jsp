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
var status = '${CheckItemEditForm.status}';

$(document).ready(function(){
    if(status=="success"){
  		alert("保存成功");
  	}
});

function saveCheckItem(){
	var editId = $("#editId").val();
    var itemCode = $("#itemCode").val();
    var itemName = $("#itemName").val();
    var itemType = $("#itemType").val();
    
    var parentId = $("#parentId").val();
    var employeeFlg = $("#employeeFlg").val();
    var orgFlg = $("#orgFlg").val();
    var managerFlg = $("#managerFlg").val();
	
	if(isNull(itemCode)){
		$("#errorMsg").html("项目编号必须输入");
		alert("项目编号必须输入");
		return;
	}
	if(isNull(itemName)){
		$("#errorMsg").html("项目名称必须输入");
		alert("项目名称必须输入");
		return;
	}

	if(isNull(employeeFlg)){
		$("#errorMsg").html("是否考核 经办柜员必须输入");
		alert("是否考核 经办柜员必须输入");
		return;
	}

	if(isNull(orgFlg)){
		$("#errorMsg").html("是否考核营业机构必须输入");
		alert("是否考核 经办柜员必须输入");
		return;
	}

	if(isNull(managerFlg)){
		$("#errorMsg").html("是否考核营业室经理必须输入");
		alert("是否考核营业室经理必须输入");
		return;
	}

	if(itemType=='2'&&isNull(parentId)){
		$("#errorMsg").html("父项目必须输入");
		alert("父项目必须输入");
		return;
	}
	
	if(isNull(editId)){
		document.forms[0].action= "addCheckItem.do?method=addCheckItem";
	}else{
		document.forms[0].action= "editCheckItem.do?method=editCheckItem";
	}	
	document.forms[0].submit();	
}

function changeType(value){
	if(value=='1'){
		$("#parentId").attr("disabled","true");
	}else{
		$("#parentId").removeAttr("disabled");
	}
}


function pageback(){
	document.forms[0].action= "<%=request.getContextPath()%>/searchCheckItem.do?method=searchCheckItem";
	document.forms[0].submit();
}
</script>
</head>
<body style="background-color: #E5F5FF;" >
<html:form method="post">
<html:hidden styleId="editId" name="CheckItemEditForm" property="editId"/>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>营业机构考核管理>>考核项目新增</td>
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
    <td width='12%' class='myEdit'>项目编号</td>
	<td><html:text styleId="itemCode" property="itemCode" maxlength="50"></html:text><span style="color: red;">*</span></td>
	<td width='12%' class='myEdit'>项目名称</td>
    <td><html:text styleId="itemName" property="itemName"  size="50" maxlength="200"></html:text><span style="color: red;">*</span></td> 
  </tr>
  <tr>
    <td width='12%' class='myEdit'>项目等级</td>
	<td>
		<logic:notEqual  name="CheckItemEditForm"  property="editId" value="">
        <html:select styleId="itemType" property="itemType" style="width: 170px"   onchange="changeType(this.value);"  >
		  <html:option value="2">2级</html:option>
 		  <html:option value="1">1级</html:option>
		</html:select>
        </logic:notEqual>
		<logic:equal  name="CheckItemEditForm"  property="editId" value="">
        <html:select styleId="itemType" property="itemType" style="width: 170px"   onchange="changeType(this.value);" >
		  <html:option value="2">2级</html:option>
 		  <html:option value="1">1级</html:option>
		</html:select>
		</logic:equal>
    <span style="color: red;">*</span>
    </td>
	<td width='12%' class='myEdit'>父级项目</td>
    <td>
	    <html:select styleId="parentId" property="parentId" style="width: 170px"  >
		<html:optionsCollection property="checkBigItemList" label="itemName" value="id" />
		</html:select>
	<span style="color: red;">*</span>
    </td> 
  </tr>


  <tr>
    <td width='12%' class='myEdit'>是否考核 经办柜员</td>
	<td>
        <html:select styleId="employeeFlg" property="employeeFlg" style="width: 170px">
          <html:option value="">请选择</html:option>
		  <html:option value="1">是</html:option>
 		  <html:option value="2">否</html:option>
		</html:select>
	<span style="color: red;">*</span>
    </td>
    <td width='12%' class='myEdit'>是否考核营业室经理</td>
	<td>
        <html:select styleId="managerFlg" property="managerFlg" style="width: 170px">
          <html:option value="">请选择</html:option>
		  <html:option value="1">是</html:option>
 		  <html:option value="2">否</html:option>
		</html:select>
	<span style="color: red;">*</span>
    </td>
  </tr>
  <tr>
    <td width='12%' class='myEdit'>是否考核营业机构</td>
	<td>
        <html:select styleId="orgFlg" property="orgFlg" style="width: 170px">
          <html:option value="">请选择</html:option>
		  <html:option value="1">是</html:option>
 		  <html:option value="2">否</html:option>
		</html:select>

	<span style="color: red;">*</span>
    </td>
    <td></td>
	<td></td>
  </tr>
  <tr>
    <td width='12%' class='myEdit'>评分标准</td>
	<td colspan="3">
	   <html:textarea styleId="checkContent" property="checkContent"  rows="6"  cols="50" >
	   </html:textarea>
    </td>
  </tr>
</table>
<table align=center style="background-color: #E5F5FF;">
  <tr>
    <td>
	<html:button property="" styleClass="button" value="保存" onclick="saveCheckItem();"></html:button>
	<html:button property="" styleClass="button" value="返回" onclick="pageback();"></html:button>
	</td> 
  </tr>
</table>
</html:form>
</body>
</html>