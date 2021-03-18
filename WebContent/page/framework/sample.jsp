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
<script type="text/javascript">
function test1(){
	window.showModalDialog("employeeDialog.jsp","","dialogHeight:400px;dialogWidth:600px;scroll:no;");
}
function test2(){
	window.showModalDialog("uploadFile.jsp","","dialogHeight:400px;dialogWidth:600px;scroll:no;");
}
</script>
</head>
<body style="background-color: #E5F5FF;">

<html:form method="post" action="sample.do?method=test">
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>员工查询</td>
 </tr>
 <tr>
  <td class='Navigationtdbg'>
  员工姓名<input type="text" /><input type="submit" Class="button"  value="查询">
<input type="button" value="员工选择" onclick="test1();"/>
<input type="button" value="文件上传" onclick="test2();"/>
  </td>
 </tr>
</table>
<br>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
<tr class='title'>
   <td width='12%'><b>工号</b></td>
   <td width='15%'><b>姓名</b></td>
   <td width='8%'><b>性别</b></td>
   <td width='10%'><b>年龄</b></td>
   <td width='34%'><b>部门及职务</b></td>
   <td width='21%'><b>操作</b></td>
</tr>
<tr class='titletdbg'>
   <td width='12%'>ID001</td>
   <td width='15%'>小王</td>
   <td width='8%'>男</td>
   <td width='10%'>33</td>
   <td width='34%'>经理(财务部)</td>
   <td width='21%'><a href="#"  style="color: blue;padding-right: 5px">详细</a><a href="#"  style="color: blue;padding-right: 5px">修改</a><a href="#"  style="color: blue">删除</a></td>  
</tr>
<tr class='titletdbg'>
   <td width='12%'>ID001</td>
   <td width='15%'>小王</td>
   <td width='8%'>男</td>
   <td width='10%'>33</td>
   <td width='34%'>经理(财务部)</td>
   <td width='21%'><a href="#"  style="color: blue;padding-right: 5px">详细</a><a href="#"  style="color: blue;padding-right: 5px">修改</a><a href="#"  style="color: blue">删除</a></td>  
</tr>
<tr class='titletdbg'>
   <td width='12%'>ID001</td>
   <td width='15%'>小王</td>
   <td width='8%'>男</td>
   <td width='10%'>33</td>
   <td width='34%'>经理(财务部)</td>
   <td width='21%'><a href="#"  style="color: blue;padding-right: 5px">详细</a><a href="#"  style="color: blue;padding-right: 5px">修改</a><a href="#"  style="color: blue">删除</a></td>  
</tr>
<tr class='titletdbg'>
   <td width='12%'>ID001</td>
   <td width='15%'>小王</td>
   <td width='8%'>男</td>
   <td width='10%'>33</td>
   <td width='34%'>经理(财务部)</td>
   <td width='21%'><a href="#"  style="color: blue;padding-right: 5px">详细</a><a href="#"  style="color: blue;padding-right: 5px">修改</a><a href="#"  style="color: blue">删除</a></td>  
</tr>
<tr class='titletdbg'>
   <td width='12%'>ID001</td>
   <td width='15%'>小王</td>
   <td width='8%'>男</td>
   <td width='10%'>33</td>
   <td width='34%'>经理(财务部)</td>
   <td width='21%'><a href="#"  style="color: blue;padding-right: 5px">详细</a><a href="#"  style="color: blue;padding-right: 5px">修改</a><a href="#"  style="color: blue">删除</a></td>  
</tr>
<tr>
 <td style="background-color: white;text-align: center"  colspan="6">
<input type="submit" Class="button"  value="上一页">&nbsp;&nbsp; 1/10<input type="submit" Class="button"  value="下一页">&nbsp<input type="text" size="3" maxlength="3"/><input type="submit" Class="button"  value="跳转">
 </td>
</tr>
</table>
</html:form>
</body>
</html>