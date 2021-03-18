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
var parentId = '${CheckItemSearchForm.parentIdSearch}';
var checkItemName = '${CheckItemSearchForm.checkItemNameSearch}';
var totalPage = '${CheckItemSearchForm.totalPage}';
function checkItemAdd(){
	document.forms[0].action= "<%=request.getContextPath()%>/initEditCheckItem.do?method=initEditCheckItem";
	document.forms[0].submit();
}

function checkItemEdit(id){
	document.forms[0].action= "<%=request.getContextPath()%>/initEditCheckItem.do?method=initEditCheckItem&editId=" + id;
	document.forms[0].submit();	
}

function checkItemDelete(id){
	if(confirm("确定删除")){
		document.forms[0].action= "<%=request.getContextPath()%>/deleteCheckItem.do?method=deleteCheckItem&editId=" + id;
		document.forms[0].submit();
	}
}

function checkItemSearch(){
	$("#currentPage").val(1);
	document.forms[0].action= "<%=request.getContextPath()%>/searchCheckItem.do?method=searchCheckItem";
	document.forms[0].submit();
}
function prePage(){
	$("#currentPage").val(parseInt($("#currentPage").val())-1);
	$("#parentId").val(parentId);
	$("#checkItemName").val(checkItemName);

	document.forms[0].action= "<%=request.getContextPath()%>/searchCheckItem.do?method=searchCheckItem";
	document.forms[0].submit();
}
function nextPage(){
	$("#currentPage").val(parseInt($("#currentPage").val())+1);
	$("#parentId").val(parentId);
	$("#checkItemName").val(checkItemName);

	document.forms[0].action= "<%=request.getContextPath()%>/searchCheckItem.do?method=searchCheckItem";
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
	$("#parentId").val(parentId);
	$("#checkItemName").val(checkItemName);

	document.forms[0].action= "<%=request.getContextPath()%>/searchCheckItem.do?method=searchCheckItem";
	document.forms[0].submit();
}
</script>
</head>
<body style="background-color: #E5F5FF;">

<html:form method="post">
<html:hidden styleId="currentPage" name="CheckItemSearchForm" property="currentPage"/>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>营业机构考核管理>>考核项目管理</td>
 </tr>
 <tr>
  <td class='Navigationtdbg'>
  <table style="width: 100%">
	<tr>
		<td width='10%' class='searchTitle'>项目大类</td>
  		<td width='12%'>
		<html:select styleId="parentId" property="parentIdSearch" style="width: 170px">
        <html:option value="">请选择</html:option>
		<html:optionsCollection property="checkBigItemList" label="itemName" value="id" />
		</html:select>
        </td>
		<td width='10%' class='searchTitle'>细分项目</td>
		<td width='12%'><html:text styleId="checkItemName" property="checkItemNameSearch"/></td>
		<td width='15%'><html:button styleClass="button" property="" value="查询" onclick="checkItemSearch();"></html:button><html:button styleClass="button" property="" value="新增" onclick="checkItemAdd();"></html:button></td>
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
   <td width='10%'><b>项目编号</b></td>
   <td width='10%'><b>项目大类</b></td>
   <td width='10%'><b>细分项目</b></td>
   <td width='30%'><b>评分标准</b></td>
   <td width='10%'><b>是否考核营业室经理</b></td>
   <td width='10%'><b>是否考核经办柜员</b></td>
   <td width='10%'><b>是否考核机构</b></td>
   <td width='10%'><b>操作</b></td>
</tr>
<logic:notEmpty name="CheckItemSearchForm" property="checkItemList">
<logic:iterate id="checkItem" name="CheckItemSearchForm" property="checkItemList" indexId="indexid">
	<tr class='titletdbg'>
	   <td><bean:write name="checkItem" property="itemCode"/></td>
	   <td><bean:write name="checkItem" property="itemBigName"/></td>
	   <td><bean:write name="checkItem" property="itemSmallName"/></td>
	   <td><bean:write name="checkItem" property="checkContent"/></td>
	   <td><bean:write name="checkItem" property="managerFlg"/></td>
	   <td><bean:write name="checkItem" property="employeeFlg"/></td>
	   <td><bean:write name="checkItem" property="orgFlg"/></td>
	   <td><a href="#"  style="color: blue;padding-right: 5px" onclick="checkItemEdit('${checkItem.itemId}');">修改</a><a href="#"  style="color: blue" onclick="checkItemDelete('${checkItem.itemId}');">删除</a></td>  
	</tr>
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="CheckItemSearchForm" property="checkItemList">
<tr>
 <td style="background-color: white;text-align: center"  colspan="8">
<logic:equal name="CheckItemSearchForm" property="currentPage" value="1">
<html:button styleClass="button" property="" value="上一页" onclick="" disabled="true"></html:button>
</logic:equal>
<logic:notEqual name="CheckItemSearchForm" property="currentPage"  value="1">
<html:button styleClass="button" property="" value="上一页" onclick="prePage();" disabled="false"></html:button>
</logic:notEqual>
&nbsp;&nbsp; <bean:write name="CheckItemSearchForm" property="currentPage"/>/<bean:write name="CheckItemSearchForm" property="totalPage"/>
<logic:equal  name="CheckItemSearchForm" property="currentPage" value="${CheckItemSearchForm.totalPage}">
<html:button styleClass="button" property="" value="下一页" onclick="" disabled="true"></html:button>
</logic:equal>
<logic:notEqual name="CheckItemSearchForm" property="currentPage" value="${CheckItemSearchForm.totalPage}">
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