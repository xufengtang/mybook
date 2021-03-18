<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
function mouseOver(td){
	td.style.color = "red";
	if(td.className=="css1"){
		td.className = "css2";
	}
}

function mouseOut(td){
	if(td.className=="css2"){
		td.className = "css1";
	}
}

function goDetail(noticeId){
	document.forms[0].action= "<%=request.getContextPath()%>/showPub.do?method=initUpdatePub&id=" + noticeId + "&fromIndex=yes";
	document.forms[0].submit();	
}

function goReportDetail(reportDetailId,taskType){
	if(taskType=='0'){
		document.forms[0].action= "<%=request.getContextPath()%>/searchDetailReport.do?method=searchDetailReport&taskType=0&detailId=" + reportDetailId;
	}else{
		document.forms[0].action= "<%=request.getContextPath()%>/searchDetailReport.do?method=searchDetailReport&detailId=" + reportDetailId;
	}
	
	document.forms[0].submit();	
	
}

function goFlowDetail(flowInstanceMainId,stepProcessType,flowCode){
	if(stepProcessType==""){
		alert("状态初始");
		return;
	}
		document.forms[0].action= "<%=request.getContextPath()%>/flowProgressSearch.do?method=flowProgressSearch" + "&flowInstanceMainId=" + flowInstanceMainId + "&stepProcessType="  + stepProcessType + "&flowCode=" + flowCode + "&waitPageFlg=waitPage";
		document.forms[0].submit();
}

function showMore(){
	var tr = document.getElementById("noticeNone");
	if(tr.style.display=="none"){
		
	}else{
		alert("没有更多数据");
		return;
	}
	document.forms[0].action= "<%=request.getContextPath()%>/searchIndexPub.do?method=searchIndexPub&isSearch=init";
	document.forms[0].submit();	
}

function showReprotDetailMore(){
	var tr = document.getElementById("reportNone");
	if(tr.style.display=="none"){
		
	}else{
		alert("没有更多数据");
		return;
	}
	document.forms[0].action= "<%=request.getContextPath()%>/searchDetailReport.do?method=searchDetailReport&statusParam=0";
	document.forms[0].submit();	
}

function showFlowMore(){
	var tr = document.getElementById("flowNone");
	if(tr.style.display=="none"){
		
	}else{
		alert("没有更多数据");
		return;
	}
	document.forms[0].action= "<%=request.getContextPath()%>/flowWaitSearch.do?method=flowWaitSearch";
	document.forms[0].submit();	
}


window.onload = function(){
	var noticeTable = document.getElementById("noticeTable");
	var flowTable = document.getElementById("flowTable");
	var reportTable = document.getElementById("reportTable");
	var nTrLen = noticeTable.rows.length;
	var fTrLen = flowTable.rows.length;
	var rTrLen = reportTable.rows.length;
	var trs = flowTable.rows;
	for(var i=0;i<trs.length-1;i++){
		var colStatus = trs[i].childNodes[0].innerHTML;
		colStatus=colStatus.substring(0,1);
		if(colStatus=='2'){
			for(var k=1;k<trs[i].childNodes.length;k++){
				trs[i].childNodes[k].childNodes[0].style.backgroundColor = "red";
				//trs[i].childNodes[k].childNodes[0].style.fontWeight = "bold";
			}
		}else if(colStatus=='1'){
			for(var k=1;k<trs[i].childNodes.length;k++){
				trs[i].childNodes[k].childNodes[0].style.backgroundColor = "yellow";
			}
		}
	
	}
	if(nTrLen==1){//没有数据
		document.getElementById("noticeNone").style.display = "block";
	}
	if(fTrLen==1){
		document.getElementById("flowNone").style.display = "block";
	}
	if(rTrLen==1){
		document.getElementById("reportNone").style.display = "block";
	}
	
	
	/*
	if(nTrLen>=1&&nTrLen<11){//数据没有11行
		var dis1 = 11 - nTrLen;//要增加的行数
		for(var j=0;j<dis1;j++){
			var fTr = noticeTable.insertRow(nTrLen);
			fTr.className = "css1";
			fTr.style.height = "17px";
			fTr.onclick = function(){
			}
			var fTd = fTr.insertCell();
			fTd.innerHTML = " ";
		}
	}
	if(fTrLen>=1&&fTrLen<11){//数据没有11行
		var dis1 = 11 - fTrLen;//要增加的行数
		for(var j=0;j<dis1;j++){
			var fTr = flowTable.insertRow(fTrLen);
			fTr.className = "css1";
			/*
			fTr.onmouseover=function(){
				if(this.className=="css1"){
					this.className = "css2";
				}
			};
			fTr.onmouseout = function(){
				if(this.className=="css2"){
					this.className = "css1";
				}
			}
			fTr.style.height = "17px";
			fTr.onclick = function(){
			}
			var fTd = fTr.insertCell();
			fTd.innerHTML = " ";
		}
	}
	*/
}

</script>

<style type="text/css">
.noticeTable{
	
}

div{
}

table{
	font-size: 13px;
}

tr{
	cursor: pointer;
}

.css1{
	background-color:  #E5F5FF;
	color: red;
}
.css2{
	background-color: white;
}
.css3{
	color: red;
}
</style>

</head>
<body style="background-color: #E5F5FF;">
	<html:form method="post"></html:form>
	<div style="width: 100%;">

	<table width="100%" id="container">
		<tr height="250px">
			<td style="vertical-align: top;border: 1px solid #4D9FE0;">
		<table width="100%"  cellpadding="0" cellspacing="0" style="background-color: #4D9FE0;">
			<tr><td><span style="color: white;font-size: 22px;font-weight: bold;margin-left: 20px;margin-top: 10px;cursor: pointer;">我的公告</span></td>
			<td style="text-align: center"><span style="margin-left: 100px;font-size: 14px;font-weight: bold;;margin-top: 10px;cursor: pointer;" onclick="showMore()">查看更多</span></td></tr>
		</table>
		<table width="100%"  class="noticeTable" id="noticeTable" cellpadding="0" cellspacing="0" style="background-color: #E5F5FF;">
			<logic:notEmpty name="EmployeeIndexForm" property="pubList">
			<logic:iterate id="user" name="EmployeeIndexForm" property="pubList" indexId="indexid">
				<tr class="css1" onmouseover="mouseOver(this)" onmouseout="mouseOut(this)" height="20px" >
				   <td width="70%" onclick="goDetail('${user.noticeId}')">
					<font color="blue" style="margin-left: 20px" >
						<bean:write name="user" property="noticeName" />
					</font>
				   </td>
					<td width="30%" onclick="goDetail('${user.noticeId}')" >
					<font color="blue" style="margin-left: 20px" >
						<bean:write name="user" property="noticeTime" />
					</font>
				   </td>
				</tr>
			</logic:iterate>
			</logic:notEmpty>
			<tr style="color: #4D9FE0;text-align: center;display: none;font-size: 20px;height: 30px" id="noticeNone"><td>没有公告</td></tr>
		</table>
			</td>
			<td style="vertical-align: top;border: 1px solid #4D9FE0;">
		<table width="100%"  cellpadding="0" cellspacing="0" style="background-color: #4D9FE0;">
			<tr><td><span style="color: white;font-size: 22px;font-weight: bold;margin-left: 20px;margin-top: 10px;cursor: pointer;">待办任务</span></td>
			<td style="text-align: center"><span style="margin-left: 100px;font-size: 14px;font-weight: bold;;margin-top: 10px;cursor: pointer;" onclick="showFlowMore()">查看更多</span></td></tr>
		</table>
		<table width="100%" class="noticeTable" id="flowTable" cellpadding="0" cellspacing="0" style="background-color: #E5F5FF;">
			<logic:notEmpty name="EmployeeIndexForm" property="flowInstanceMainList">
			<logic:iterate id="user1" name="EmployeeIndexForm" property="flowInstanceMainList" indexId="indexid">
				<tr class="css1" onmouseover="mouseOver(this)" onmouseout="mouseOut(this)" height="20px" >
				 	<td  style="display: none">
						<bean:write name="user1" property="colorStatus" />
				   </td>
					  <td width="70%" onclick="goFlowDetail('${user1.flowInstanceMainId}','${user1.stepProcessType}','${user1.flowCode}')">
					<font color="blue" style="margin-left: 20px;" >
						<bean:write name="user1" property="title" />
					</font>
				   </td>
					<td width="30%" onclick="goFlowDetail('${user1.flowInstanceMainId}','${user1.stepProcessType}','${user1.flowCode}')">
					<font color="blue" style="margin-left: 20px" >
						<bean:write name="user1" property="flowInstanceMainCreateTime" />
					</font>
				   </td>
				</tr>
			</logic:iterate>
			</logic:notEmpty>
			<tr style="text-align: center;display: none;font-size: 20px;height: 30px;color: #4D9FE0" id="flowNone"><td>没有待办任务</td></tr>
		</table>
			</td>
		</tr>
		
		<!-- 合规上报  -->
		<tr height="250px">
		<td style="vertical-align: top;border: 1px solid #4D9FE0;">
		<table width="100%"  cellpadding="0" cellspacing="0" style="background-color: #4D9FE0;">
			<tr><td><span style="color: white;font-size: 22px;font-weight: bold;margin-left: 20px;margin-top: 10px;cursor: pointer;">合规上报</span></td>
			<td style="text-align: center"><span style="margin-left: 100px;font-size: 14px;font-weight: bold;;margin-top: 10px;cursor: pointer;" onclick="showReprotDetailMore()">查看更多</span></td></tr>
		</table>
		<table width="100%"  class="noticeTable" id="reportTable" cellpadding="0" cellspacing="0" style="background-color: #E5F5FF;">
			<logic:notEmpty name="EmployeeIndexForm" property="reportDetailList">
			<logic:iterate id="reportDetail" name="EmployeeIndexForm" property="reportDetailList" indexId="indexid">
				<tr class="css1" onmouseover="mouseOver(this)" onmouseout="mouseOut(this)" height="20px" >
				   <td width="70%" onclick="goReportDetail('${reportDetail.id}','${reportDetail.taskType}')">
					<font color="blue" style="margin-left: 20px" >
						<bean:write name="reportDetail" property="title" />
					</font>
				   </td>
					<td width="30%" onclick="goReportDetail('${reportDetail.id}')" >
					<font color="blue" style="margin-left: 20px" >
						<bean:write name="reportDetail" property="endDate" />
					</font>
				   </td>
				</tr>
			</logic:iterate>
			</logic:notEmpty>
			<tr style="color: #4D9FE0;text-align: center;display: none;font-size: 20px;height: 30px" id="reportNone"><td>没有上报任务</td></tr>
		</table>
			</td>
		<!-- 以后扩张用  -->
		<td style="vertical-align: top;border: 1px solid #4D9FE0;">
		</td>
	    </tr>

	</table>
	</div>
</body>
</html>