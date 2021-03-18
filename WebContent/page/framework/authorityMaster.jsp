<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="cn.ewb.cq.com.SysConstant" %>
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
function saveAuthority(){
	if(confirm("确定修改")){
		document.forms[0].action= "saveAuthority.do?method=saveAuthority";
		document.forms[0].submit();	
	}
}

function changeAuthority(){
	document.forms[0].action= "searchAuthority.do?method=searchAuthority";
	document.forms[0].submit();	
}

</script>
</head>
<body style="background-color: #E5F5FF;" >

<html:form method="post">
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>系统管理>>权限管理</td>
 </tr>
</table>
<br>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center style="background-color: #E5F5FF;">
  <tr>
	<td width='15%'  class='title'><b>角色名</b></td>
    <td>
    <html:select property="selectRole" style="width: 120px" onchange="changeAuthority();">
        <html:optionsCollection property="roleList" label="roleName" value="id"/>
    </html:select>
<!-- 
	<select style="width: 120px">
      <option value="">管理员</option>
	  <option value="">财务经理</option>
    </select> -->
    </td> 
  </tr>
</table>
<br/>
<br/>
<br/>
<table>
 <tr>
  <td>
	<span id="infoMsg" style="color: blue;">
	<html:messages id="infoMsg" message="true">
	<bean:write name="infoMsg"/>
	</html:messages>
	</span>
  </td>
 </tr>
</table>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center style="background-color: #E5F5FF;">
<logic:notEmpty name="AuthorityForm" property="authorityList">
	<logic:iterate id="authorityList"  name="AuthorityForm" property="authorityList" indexId="indexid">
	<tr>
	   <td width='15%' class='title'>
	   <b><bean:write name="authorityList" property="menuName"/></b></td>
	   <td width='40%'>
			<html:hidden name="authorityList" property="menuId"  indexed="true"/>
			<html:hidden name="authorityList" property="menuName"  indexed="true"/>
		    <html:select name="authorityList" property="menuValue"  indexed="true" style="width: 120px" >
				<logic:notEqual  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_3%>">
				<logic:notEqual  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_4%>">
				<logic:notEqual  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_5%>">
				<logic:notEqual  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_6%>">
				<logic:notEqual  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_7%>">
				<logic:notEqual  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_8%>">
				<logic:notEqual  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_9%>">
				<logic:notEqual  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_10%>">
				<logic:notEqual  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_11%>">
				<logic:notEqual  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_12%>">
				<logic:notEqual  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_13%>">
				<logic:notEqual  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_14%>">
				<html:option value="1">可用</html:option>
				<html:option value="0">不可用</html:option>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEqual>
				<logic:equal  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_3%>">
				<html:option value="0">不可用</html:option>
				<html:option value="1">本人</html:option>
				<html:option value="2">本部门</html:option>
				<html:option value="3">全行</html:option>
				</logic:equal>
				<logic:equal  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_4%>">
				<html:option value="0">不可用</html:option>
				<html:option value="1">本部门</html:option>
				<html:option value="2">全行</html:option>
				</logic:equal>
				<logic:equal  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_5%>">
				<html:option value="0">不可用</html:option>
				<html:option value="1">本部门</html:option>
				<html:option value="2">全行</html:option>
				</logic:equal>
				<logic:equal  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_6%>">
				<html:option value="0">不可用</html:option>
				<html:option value="1">本部门</html:option>
				<html:option value="2">全行</html:option>
				</logic:equal>
				<logic:equal  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_7%>">
				<html:option value="0">不可用</html:option>
				<html:option value="1">本人</html:option>
				<html:option value="2">本部门</html:option>
				<html:option value="3">全行</html:option>
				</logic:equal>
				<logic:equal  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_8%>">
				<html:option value="0">不可用</html:option>
				<html:option value="1">本部门</html:option>
				<html:option value="2">全行</html:option>
				</logic:equal>
				<logic:equal  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_9%>">
				<html:option value="0">不可用</html:option>
				<html:option value="1">本部门</html:option>
				<html:option value="2">全行</html:option>
				</logic:equal>
				<logic:equal  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_10%>">
				<html:option value="0">不可用</html:option>
				<html:option value="1">本人</html:option>
				<html:option value="2">本部门</html:option>
				<html:option value="3">全行</html:option>
				</logic:equal>
				<logic:equal  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_11%>">
				<html:option value="0">不可用</html:option>
				<html:option value="1">本部门</html:option>
				<html:option value="2">全行</html:option>
			    </logic:equal>
				<logic:equal  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_12%>">
				<html:option value="0">不可用</html:option>
				<html:option value="1">本人</html:option>
				<html:option value="2">全行</html:option>
			    </logic:equal>
				<logic:equal  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_13%>">
				<html:option value="0">不可用</html:option>
				<html:option value="1">本人</html:option>
				<html:option value="2">本部门</html:option>
				<html:option value="3">全行</html:option>
			    </logic:equal>
				<logic:equal  name = "authorityList"  property="menuName"  value="<%=SysConstant.WORK_FLOW_REPORT_14%>">
				<html:option value="0">不可用</html:option>
				<html:option value="1">本人</html:option>
				<html:option value="2">本部门</html:option>
				<html:option value="3">全行</html:option>
			    </logic:equal>
		    </html:select> 
	   </td>
	</tr>
	</logic:iterate>
</logic:notEmpty>
</table>
<table align=center style="background-color: #E5F5FF;">
  <tr>
    <td>
	<html:button styleClass="button" value="保存" property="" onclick="saveAuthority();"></html:button>
	</td> 
  </tr>
</table>
</html:form>
</body>
</html>