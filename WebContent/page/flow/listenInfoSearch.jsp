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
var totalPage = '${ListenInfoSearchForm.totalPage}';
function listenInfoSearch(){
	document.forms[0].action= "<%=request.getContextPath()%>/searchListenInfo.do?method=searchListenInfo";
	document.forms[0].submit();
}


function prePage(){
	$("#currentPage").val(parseInt($("#currentPage").val())-1);
	document.forms[0].action= "<%=request.getContextPath()%>/searchListenInfo.do?method=searchListenInfo";
	document.forms[0].submit();
}

function nextPage(){
	$("#currentPage").val(parseInt($("#currentPage").val())+1);
	document.forms[0].action= "<%=request.getContextPath()%>/searchListenInfo.do?method=searchListenInfo";
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
	document.forms[0].action= "<%=request.getContextPath()%>/searchListenInfo.do?method=searchListenInfo";
	document.forms[0].submit();
}

function listenInfoAdd(){
	document.forms[0].action= "<%=request.getContextPath()%>/listenInfoEditInit.do?method=listenInfoEditInit";
	document.forms[0].submit();
}

function listenInfoEdit(editId){
	document.forms[0].action= "<%=request.getContextPath()%>/listenInfoEditInit.do?method=listenInfoEditInit&editId=" + editId;
	document.forms[0].submit();
}

function listenInfoDel(editId){
	if(confirm("确定删除")){
		document.forms[0].action= "<%=request.getContextPath()%>/listenInfoDel.do?method=listenInfoDel&editId=" + editId;
		document.forms[0].submit();
	}

}



</script>
</head>
<body style="background-color: #E5F5FF;">

<html:form method="post">
<html:hidden styleId="currentPage" name="ListenInfoSearchForm" property="currentPage"/>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>听力录音管理>>听力录音查询</td>
 </tr>
 <tr>
  <td class='Navigationtdbg'>
  <table style="width: 100%">
	<tr>
		<td width='15%' class='searchTitle'>听力种类</td>
		<td width='15%'>

		<html:select styleId="levelIdSearch" property="levelIdSearch"  name="ListenInfoSearchForm"  style="width: 170px">
		<html:optionsCollection name="ListenInfoSearchForm"  property="levelList"  label="dictName" value="dictCode" />	    
		</html:select>
		</td>
		<td width='15%'><html:button styleClass="button" property="" value="查询" onclick="listenInfoSearch();"></html:button><html:button styleClass="button" property="" value="新增" onclick="listenInfoAdd();"></html:button></td>
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
   <td width='30%'><b>听力名称</b></td>
   <td width='50%'><b>URL地址</b></td>
   <td width='10%'><b>排序号</b></td>
   <td width='10%'><b>操作</b></td>
</tr>
<logic:notEmpty name="ListenInfoSearchForm" property="listenInfoList">
<logic:iterate id="listenInfo" name="ListenInfoSearchForm" property="listenInfoList" indexId="indexid">
	<tr class='titletdbg'>
	   <td><bean:write name="listenInfo" property="listenName"/></td>
	   <td><bean:write name="listenInfo" property="listenUrl"/></td>
	   <td><bean:write name="listenInfo" property="orderNum"/></td>
	   <td><a href="#"  style="color: blue;padding-right: 5px" onclick="listenInfoEdit('${listenInfo.id}');">修改</a><a href="#"  style="color: blue" onclick="listenInfoDel('${listenInfo.id}');">删除</a></td>  
	</tr>
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="ListenInfoSearchForm" property="listenInfoList">
<tr>
 <td style="background-color: white;text-align: center"  colspan="7">
<logic:equal name="ListenInfoSearchForm" property="currentPage" value="1">
<html:button styleClass="button" property="" value="上一页" onclick="" disabled="true"></html:button>
</logic:equal>
<logic:notEqual name="ListenInfoSearchForm" property="currentPage"  value="1">
<html:button styleClass="button" property="" value="上一页" onclick="prePage();" disabled="false"></html:button>
</logic:notEqual>
&nbsp;&nbsp; <bean:write name="ListenInfoSearchForm" property="currentPage"/>/<bean:write name="ListenInfoSearchForm" property="totalPage"/>
<logic:equal  name="ListenInfoSearchForm" property="currentPage" value="${ListenInfoSearchForm.totalPage}">
<html:button styleClass="button" property="" value="下一页" onclick="" disabled="true"></html:button>
</logic:equal>
<logic:notEqual name="ListenInfoSearchForm" property="currentPage" value="${ListenInfoSearchForm.totalPage}">
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