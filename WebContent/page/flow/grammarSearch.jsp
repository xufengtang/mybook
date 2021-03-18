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
var totalPage = '${GrammarSearchForm.totalPage}';

function grammarAdd(){
	var levelValue=$("#levelValueSearch").val();
	var unitValue =$("#unitValueSearch").val();  
	document.forms[0].action= "<%=request.getContextPath()%>/initGrammar.do?method=initGrammar" + "&unitValue="+ unitValue + "&levelValue="+levelValue;
	document.forms[0].submit();
}

function grammarEdit(id){
	document.forms[0].action= "<%=request.getContextPath()%>/initGrammar.do?method=initGrammar&editId=" + id;
	document.forms[0].submit();	
}

function grammarDelete(id){
	if(confirm("确定删除")){
		document.forms[0].action= "<%=request.getContextPath()%>/deleteGrammar.do?method=deleteGrammar&editId=" + id;
		document.forms[0].submit();
	}
}

function grammarSearch(){
	
	$("#currentPage").val(1);
	document.forms[0].action= "<%=request.getContextPath()%>/grammarSearch.do?method=grammarSearch";
	document.forms[0].submit();
}


function prePage(){
	$("#currentPage").val(parseInt($("#currentPage").val())-1);
	//$("#userCode").val(userCode);
	//$("#userName").val(userName);
	//$("#departId").val(departId);
	document.forms[0].action= "<%=request.getContextPath()%>/grammarSearch.do?method=grammarSearch";
	document.forms[0].submit();
}

function nextPage(){
	$("#currentPage").val(parseInt($("#currentPage").val())+1);
	//$("#userCode").val(userCode);
	//$("#userName").val(userName);
	//$("#departId").val(departId);
	document.forms[0].action= "<%=request.getContextPath()%>/grammarSearch.do?method=grammarSearch";
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
	document.forms[0].action= "<%=request.getContextPath()%>/grammarSearch.do?method=grammarSearch";
	document.forms[0].submit();
}


function getUnitList(levelId){
	$.ajax({
		url:"getUnitList.do?method=getUnitList",
		data:"levelId=" + levelId,
		type:"post",
		dataType:"text",
		success: function(result){
			var obj = eval('(' + result + ')');
			$("#unitValueSearch option").remove();
			var unitList = obj.unitList;
			$("#unitValueSearch").append("<option value=''>请选择</option>");   
			for(var i=0;i<unitList.length;i++){
				$("#unitValueSearch").append("<option value='"+unitList[i].unitId+"'>"+ unitList[i].unitName +"</option>");   
			}
		}
	});       
}

</script>
</head>
<body style="background-color: #E5F5FF;">

<html:form method="post">
<html:hidden styleId="currentPage" name="GrammarSearchForm" property="currentPage"/>
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
		<html:select styleId="levelValueSearch" property="levelValueSearch" style="width: 170px" onchange="getUnitList(this.value);">
	     <html:option value="1">一级语法</html:option>
	     <html:option value="2">二级语法</html:option>
		 <html:option value="3">三级语法</html:option>
	     <html:option value="4">四级语法</html:option>
	     <html:option value="5">五级语法</html:option>
		</html:select>
		</td>
		<td width='15%' class='searchTitle'>单元名</td>
		<td width='15%'>
	   	<html:select property="unitValueSearch" styleId="unitValueSearch">
            <html:option value="">请选择</html:option>
			<html:optionsCollection property="unitList" label="unitName" value="id"/>
	    </html:select>
		</td>
		<td width='15%' class='searchTitle'>语法标题</td>
		<td width='15%'>
			<html:text property="grammarTitleSearch"  styleId="grammarTitleSearch"></html:text>

		</td>
		<td width='10%'><html:button styleClass="button" property="" value="查询" onclick="grammarSearch();"></html:button><html:button styleClass="button" property="" value="新增" onclick="grammarAdd();"></html:button></td>
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
   <td width='25%'><b>标题</b></td>
   <td width='15%'><b>排序</b></td>
   <td width='10%'><b>操作</b></td>
</tr>
<logic:notEmpty name="GrammarSearchForm" property="grammarBeanList">
<logic:iterate id="grammarBean" name="GrammarSearchForm" property="grammarBeanList" indexId="indexid">
	<tr class='titletdbg'>
	   <td><bean:write name="grammarBean" property="levelName"/></td>
	   <td><bean:write name="grammarBean" property="unitName"/></td>
	   <td><bean:write name="grammarBean" property="grammarTitle"/></td>
       <td><bean:write name="grammarBean" property="orderNum"/></td>
	   <td><a href="#"  style="color: blue;padding-right: 5px" onclick="grammarEdit('${grammarBean.id}');">修改</a><a href="#"  style="color: blue" onclick="grammarDelete('${grammarBean.id}');">删除</a></td>  
	</tr>
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="GrammarSearchForm" property="grammarBeanList">
<tr>
 <td style="background-color: white;text-align: center"  colspan="7">
<logic:equal name="GrammarSearchForm" property="currentPage" value="1">
<html:button styleClass="button" property="" value="上一页" onclick="" disabled="true"></html:button>
</logic:equal>
<logic:notEqual name="GrammarSearchForm" property="currentPage"  value="1">
<html:button styleClass="button" property="" value="上一页" onclick="prePage();" disabled="false"></html:button>
</logic:notEqual>
&nbsp;&nbsp; <bean:write name="GrammarSearchForm" property="currentPage"/>/<bean:write name="GrammarSearchForm" property="totalPage"/>
<logic:equal  name="GrammarSearchForm" property="currentPage" value="${GrammarSearchForm.totalPage}">
<html:button styleClass="button" property="" value="下一页" onclick="" disabled="true"></html:button>
</logic:equal>
<logic:notEqual name="GrammarSearchForm" property="currentPage" value="${GrammarSearchForm.totalPage}">
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