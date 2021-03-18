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
function saveGrammar(){
	if(isNull($("#levelValue").val())){
		$("#errorMsg").html("等级名不能为空");
		return;
	}

	if(isNull($("#unitValue").val())){
		$("#errorMsg").html("单元名不能为空");
		return;
	}

	if(isNull($("#titleName").val())){
		$("#errorMsg").html("标题不能为空");
		return;
	}
	if(isNull($("#orderNum").val())){
		$("#errorMsg").html("排序号不能为空");
		return;
	}
	
	if(isNull($("#followValue").val())){
		$("#errorMsg").html("接续不能为空");
		return;
	}
	if(isNull($("#explainValue").val())){
		$("#errorMsg").html("解释不能为空");
		return;
	}
	if(isNull($("#example1").val())){
		$("#errorMsg").html("例句1不能为空");
		return;
	}
	
	document.forms[0].action= "saveGrammar.do?method=saveGrammar";
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
			$("#unitValue option").remove();
			var unitList = obj.unitList;
			for(var i=0;i<unitList.length;i++){
				$("#unitValue").append("<option value='"+unitList[i].unitId+"'>"+ unitList[i].unitName +"</option>");   
			}
		}
	});       
}

function goback(){
	document.forms[0].action= "<%=request.getContextPath()%>/grammarSearch.do?method=grammarSearch";
	document.forms[0].submit();
	
}
</script>
</head>
<body style="background-color: #E5F5FF;" >
<html:form method="post">
<html:hidden styleId="editId" property="editId"/>
<html:hidden styleId="levelValueSearch" property="levelValueSearch"/>
<html:hidden styleId="unitValueSearch" property="unitValueSearch"/>
<html:hidden styleId="grammarTitleSearch" property="grammarTitleSearch"/>
<html:hidden styleId="currentPage" property="currentPage"/>


<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>系统管理>>角色管理</td>
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
    <td width='10%' class='myEdit'>级别</td>
    <td width='10%'  >
	    <html:select property="levelValue" styleId="levelValue" onchange="getUnitList(this.value);">
	     <html:option value="1">一级语法</html:option>
	     <html:option value="2">二级语法</html:option>
		 <html:option value="3">三级语法</html:option>
	     <html:option value="4">四级语法</html:option>
	     <html:option value="5">五级语法</html:option>
	    </html:select><span style="color: red;">*</span>
    </td>
    <td width='10%' class='myEdit'>单元名</td>
    <td width='10%' >
   	<html:select property="unitValue" styleId="unitValue">
		<html:optionsCollection property="unitList" label="unitName" value="id"/>
    </html:select>
    <span style="color: red;">*</span>
    </td> 
     <td class='myEdit' width='10%' >标题</td>
     <td width='30%' >
       <html:text styleId="titleName" property="titleName" maxlength="100"  size="50"></html:text><span style="color: red;">*</span>
     </td>
     <td class='myEdit' width='10%' >排序号</td>
     <td width='10%' >
       <html:text styleId="orderNum" property="orderNum" ></html:text><span style="color: red;">*</span>
     </td>
  </tr>
  <tr></tr><tr></tr><tr></tr><tr></tr>
  <tr>
      <td class='myEdit' width='10%' >接续</td>
	  <td width='90%' colspan="7">
         <html:textarea  styleId="followValue"  property="followValue"  rows="3"  cols="100"></html:textarea>
      </td>
  </tr>
   <tr></tr><tr></tr><tr></tr><tr></tr>
  <tr>
      <td class='myEdit' width='10%' >解释</td>
	  <td width='90%' colspan="7">
         <html:textarea  styleId="explainValue"  property="explainValue"  rows="3"  cols="100"></html:textarea>
      </td>
  </tr>
  <tr></tr><tr></tr><tr></tr><tr></tr>
  <tr>
      <td class='myEdit' width='10%' >例句一</td>
	  <td width='90%' colspan="7">
         <html:textarea  styleId="example1"  property="example1"  rows="3"  cols="100"></html:textarea>
      </td>
  </tr>
  <tr></tr><tr></tr><tr></tr><tr></tr>
  <tr>
      <td class='myEdit' width='10%' >例句二</td>
	  <td width='90%' colspan="7">
         <html:textarea  styleId="example1"  property="example2"  rows="3"  cols="100"></html:textarea>
      </td>
  </tr>
  <tr></tr><tr></tr><tr></tr><tr></tr>
  <tr>
      <td class='myEdit' width='10%' >例句三</td>
	  <td width='90%' colspan="7">
         <html:textarea  styleId="example1"  property="example3"  rows="3"  cols="100"></html:textarea>
      </td>
  </tr>
  <tr></tr><tr></tr><tr></tr><tr></tr>
  <tr>
      <td class='myEdit' width='10%' >例句四</td>
	  <td width='90%' colspan="7">
         <html:textarea  styleId="example1"  property="example4"  rows="3"  cols="100"></html:textarea>
      </td>
  </tr>
  <tr></tr><tr></tr><tr></tr><tr></tr>
  <tr>
      <td class='myEdit' width='10%' >例句五</td>
	  <td width='90%' colspan="7">
         <html:textarea  styleId="example1"  property="example5"  rows="3"  cols="100"></html:textarea>
      </td>
  </tr>
  <tr></tr><tr></tr><tr></tr><tr></tr>
  <tr>
      <td class='myEdit' width='10%' >例句六</td>
	  <td width='90%' colspan="7">
         <html:textarea  styleId="example1"  property="example6"  rows="3"  cols="100"></html:textarea>
      </td>
  </tr>
  <tr></tr><tr></tr><tr></tr><tr></tr>
  <tr>
      <td class='myEdit' width='10%' >备注</td>
	  <td width='90%' colspan="7">
         <html:textarea  styleId="content"  property="content"  rows="3"  cols="100"></html:textarea>
      </td>
  </tr>
  <tr></tr><tr></tr><tr></tr><tr></tr>
  <tr>
	  <td width='10%' ><html:button styleClass="button" value="保存" property="" onclick="saveGrammar();"></html:button><html:button styleClass="button" value="返回" property="" onclick="goback();"></html:button></td>
	  <td width='90%' colspan="7"></td>
  </tr>

</table>
</html:form>
</body>
</html>