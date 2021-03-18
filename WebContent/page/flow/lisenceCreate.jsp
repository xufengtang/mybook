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
var successFlg = '${LisenceCreateForm.successFlg}';
var totalPage = '${LisenceCreateForm.totalPage}';


$(document).ready(function(){
	// 保存成功跳转到查询页面
	if(successFlg=="success"){
		alert("激活码生成成功");
	}
});

function lisenceCreate(){
	document.forms[0].action= "<%=request.getContextPath()%>/lisenceCreate.do?method=lisenceCreate";
	document.forms[0].submit();
}
function lisenceCodeSearch(){
	document.forms[0].action= "<%=request.getContextPath()%>/lisenceCodeSearch.do?method=lisenceCodeSearch";
	document.forms[0].submit();
}



function prePage(){
	$("#currentPage").val(parseInt($("#currentPage").val())-1);
	//$("#userCode").val(userCode);
	//$("#userName").val(userName);
	//$("#departId").val(departId);
	document.forms[0].action= "<%=request.getContextPath()%>/lisenceCodeSearch.do?method=lisenceCodeSearch";
	document.forms[0].submit();
}


function nextPage(){
	$("#currentPage").val(parseInt($("#currentPage").val())+1);
	//$("#userCode").val(userCode);
	//$("#userName").val(userName);
	//$("#departId").val(departId);
	document.forms[0].action= "<%=request.getContextPath()%>/lisenceCodeSearch.do?method=lisenceCodeSearch";
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
	//$("#userCode").val(userCode);
	//$("#userName").val(userName);
	//$("#departId").val(departId);
	document.forms[0].action= "<%=request.getContextPath()%>/lisenceCodeSearch.do?method=lisenceCodeSearch";
	document.forms[0].submit();
}

</script>
</head>
<body style="background-color: #E5F5FF;">
<html:form method="post" action="lisenceCreate.do">
<html:hidden styleId="currentPage" name="LisenceCreateForm" property="currentPage"/>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>激活码管理>>激活码生成</td>
 </tr>
 <tr>
  <td class='Navigationtdbg'>
  <table style="width: 100%">
	<tr>		
		<td width='15%' class='searchTitle'>激活码状态</td>
		<td width='15%'>
		<html:select styleId="userStatusSearch" property="userStatusSearch" style="width: 170px" >
	     <html:option value="1">未使用</html:option>
	     <html:option value="2">已使用</html:option>
		</html:select>
		</td>
		<td width='15%' class='searchTitle'>微信ID</td>
		<td width='15%'>
			<html:text property="openIdSearch"  styleId="openIdSearch"></html:text>
		</td>
		<td width='10%'><html:button styleClass="button" property="" value="查询" onclick="lisenceCodeSearch();"></html:button></td>
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
   <td width='10%'><b>序号</b></td>
   <td width='30%'><b>激活码</b></td>
   <td width='20%'><b>微信ID</b></td>
   <td width='20%'><b>使用日期</b></td>
   <td width='20%'><b>生成日期</b></td>

</tr>
<logic:notEmpty name="LisenceCreateForm" property="lisenceInfoList">
<logic:iterate id="lisenceInfo" name="LisenceCreateForm" property="lisenceInfoList" indexId="indexid">
	<tr class='titletdbg'>
       <td>${indexid+1}</td>
	   <td><bean:write name="lisenceInfo" property="lisenceCode"/></td>
	   <td><bean:write name="lisenceInfo" property="openId"/></td>
	   <td><bean:write name="lisenceInfo" property="useDate"/></td> 
		<td><bean:write name="lisenceInfo" property="createDate"/></td> 
	</tr>
</logic:iterate>
</logic:notEmpty>


<logic:notEmpty name="LisenceCreateForm" property="lisenceInfoList">
<tr>
 <td style="background-color: white;text-align: center"  colspan="7">
<logic:equal name="LisenceCreateForm" property="currentPage" value="1">
<html:button styleClass="button" property="" value="上一页" onclick="" disabled="true"></html:button>
</logic:equal>
<logic:notEqual name="LisenceCreateForm" property="currentPage"  value="1">
<html:button styleClass="button" property="" value="上一页" onclick="prePage();" disabled="false"></html:button>
</logic:notEqual>
&nbsp;&nbsp; <bean:write name="LisenceCreateForm" property="currentPage"/>/<bean:write name="LisenceCreateForm" property="totalPage"/>
<logic:equal  name="LisenceCreateForm" property="currentPage" value="${LisenceCreateForm.totalPage}">
<html:button styleClass="button" property="" value="下一页" onclick="" disabled="true"></html:button>
</logic:equal>
<logic:notEqual name="LisenceCreateForm" property="currentPage" value="${LisenceCreateForm.totalPage}">
<html:button styleClass="button" property="" value="下一页" onclick="nextPage();" disabled="false"></html:button>
</logic:notEqual>
&nbsp;<html:text styleId="goPage" property="goPage" size="3" maxlength="3"></html:text><html:button styleClass="button" property="" value="跳转" onclick="gotoPage();"></html:button>
 </td>
</tr>
</logic:notEmpty>


</table>


<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
<tr>
<td><html:text  name="LisenceCreateForm" property="lisenceCode"></html:text><html:button styleClass="button" property="" value="生成" onclick="lisenceCreate();" ></html:button></td>
</tr>
</table>
</html:form>
</body>
</html>