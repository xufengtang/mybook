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
var noticeName = '${EmployeeIndexForm.searchNoticeName}';
var keyword = '${EmployeeIndexForm.searchKeyword}';
var totalPage = '${EmployeeIndexForm.totalPage}';
function pubNoticeAdd(){
	document.forms[0].action= "<%=request.getContextPath()%>/initEditPub.do?method=initUpdatePub&fromIndex=fromIndexSearch";
	document.forms[0].submit();
}

function updatePub(id){
	document.forms[0].action= "<%=request.getContextPath()%>/initEditPub.do?method=initUpdatePub&id=" + id + "&fromIndex=fromIndexSearch";
	document.forms[0].submit();	
}

function showPub(id){
	document.forms[0].action= "<%=request.getContextPath()%>/showPub.do?method=initUpdatePub&id=" + id + "&fromIndex=fromIndexSearch";
	document.forms[0].submit();	
}

function pubDelete(id){
	if(confirm("确定删除")){
		document.forms[0].action= "<%=request.getContextPath()%>/searchIndexPub.do?method=indexDeletePub&editId=" + id+ "&isSearch=yes";
		document.forms[0].submit();
	}
}

function pubSearch(){
	$("#currentPage").val(1);
	document.forms[0].action= "<%=request.getContextPath()%>/searchIndexPub.do?method=searchIndexPub&isSearch=yes";
	document.forms[0].submit();
}
function prePage(){
	$("#currentPage").val(parseInt($("#currentPage").val())-1);
	$("#searchNoticeName").val(noticeName);
	$("#searchKeyword").val(keyword);
	document.forms[0].action= "<%=request.getContextPath()%>/searchIndexPub.do?method=searchIndexPub&isSearch=yes";
	document.forms[0].submit();
}
function nextPage(){
	$("#currentPage").val(parseInt($("#currentPage").val())+1);
	$("#searchNoticeName").val(noticeName);
	$("#searchKeyword").val(keyword);
	document.forms[0].action= "<%=request.getContextPath()%>/searchIndexPub.do?method=searchIndexPub&isSearch=yes";
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
	$("#noticeName").val(noticeName);
	$("#keyWord").val(keyword);
	document.forms[0].action= "<%=request.getContextPath()%>/searchIndexPub.do?method=searchIndexPub";
	document.forms[0].submit();
}
</script>
</head>
<body style="background-color: #E5F5FF;">

<html:form method="post">
<html:hidden styleId="currentPage" name="EmployeeIndexForm" property="currentPage"/>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>通知公告>>通知管理</td>
 </tr>
 <tr>
  <td class='Navigationtdbg'>
  <table style="width: 100%">
	<tr>
		<td width='10%' class='searchTitle'>公告名称</td>
		<td width='12%'><html:text styleId="noticeName" property="searchNoticeName"/></td>
		<td width='10%' class='searchTitle'>关键字</td>
		<td width='12%'><html:text styleId="keyWord" property="searchKeyword"/></td>
		<td width='15%'>
			<html:button styleClass="button" property="" value="查询" onclick="pubSearch();"></html:button>
		</td>
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
   <td width='10%'><b>公告名称</b></td>
   <td width='20%'><b>关键字</b></td>
<td width='10%'><b>重要程度</b></td>
<td width='10%'><b>是否全部通知</b></td>
<td width='10%'><b>是否发布</b></td>
	<td width='20%'><b>操作</b></td>
</tr>
<logic:notEmpty name="EmployeeIndexForm" property="pubList">
<logic:iterate id="user" name="EmployeeIndexForm" property="pubList" indexId="indexid">
	<tr class='titletdbg'>
	   <td width='8%'><bean:write name="user" property="noticeName"/></td>
	   <td width='10%'><bean:write name="user" property="noticeKeyword"/></td>
		<td width='10%'><bean:write name="user" property="noticeimportanceDegree"/></td>
		<td width='10%'><bean:write name="user" property="noticeallFlgName"/></td>
		<td width='10%'><bean:write name="user" property="noticereleaseName"/></td>
	   <td width='9%'>
			<logic:equal name="user" property="isRightNoticeCreater" value="1">
				<a href="#"  style="color: blue;padding-right: 5px" onclick="showPub('${user.noticeId}');">详情</a>
				<!-- <a href="#"  style="color: blue;padding-right: 5px" onclick="updatePub('${user.noticeId}');">修改</a> -->
				<!--<a href="#"  style="color: blue" onclick="pubDelete('${user.noticeId}');">删除</a>  -->
			</logic:equal>
			<logic:equal name="user" property="isRightNoticeCreater" value="0">
				<a href="#"  style="color: blue;padding-right: 5px" onclick="showPub('${user.noticeId}');">详情</a>
			</logic:equal>
		</td>  

	</tr>
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="EmployeeIndexForm" property="pubList">
<tr>
 <td style="background-color: white;text-align: center"  colspan="7">
<logic:equal name="EmployeeIndexForm" property="currentPage" value="1">
<html:button styleClass="button" property="" value="上一页" onclick="" disabled="true"></html:button>
</logic:equal>
<logic:notEqual name="EmployeeIndexForm" property="currentPage"  value="1">
<html:button styleClass="button" property="" value="上一页" onclick="prePage();" disabled="false"></html:button>
</logic:notEqual>
&nbsp;&nbsp; <bean:write name="EmployeeIndexForm" property="currentPage"/>/<bean:write name="EmployeeIndexForm" property="totalPage"/>
<logic:equal  name="EmployeeIndexForm" property="currentPage" value="${EmployeeIndexForm.totalPage}">
<html:button styleClass="button" property="" value="下一页" onclick="" disabled="true"></html:button>
</logic:equal>
<logic:notEqual name="EmployeeIndexForm" property="currentPage" value="${EmployeeIndexForm.totalPage}">
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