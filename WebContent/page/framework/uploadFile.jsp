<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>
<title>文件上传</title>
<meta http-equiv="pragma" content="no-cache" >
<meta http-equiv="cache-control" content="no-cache" >
<meta http-equiv="expries" content="0" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet type=text/css href="<%=request.getContextPath()%>/css/HX_Style.css">
<link rel=stylesheet type=text/css href="<%=request.getContextPath()%>/css/Css.css">
<link rel=stylesheet type=text/css href="<%=request.getContextPath()%>/css/tree.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.js"></script>

<script language="javascript">
   var status = '${UploadFileForm.status}'
   var returnString = '${UploadFileForm.returnValue}'
   var count = 1;
   // 在DOM中插入一个上传文件列表项（div元素)和一个<input type="file"/>元素  
   function insertNextFile(obj)   
   {   
 // 获取上传控制个数  
       //var childnum = document.getElementById("files").getElementsByTagName("input").length;
       var childnum = count;       
       var id = childnum - 1;  
       var fullName = obj.value;  
       // 插入<div>元素及其子元素  
       var fileHtml = '';  
       fileHtml += '<div  id = "file_preview' + id + '" style ="border-bottom: 1px solid #CCC;">';  
       //fileHtml += '<img  width =20 height = 20 src ="<%=request.getContextPath()%>/images/folder_close.gif" title="' + fullName + '"/>';  
       fileHtml += fullName.substr(fullName.lastIndexOf('\\')+1) ;  
       fileHtml += '<a style="text-decoration: none; color:red;"> <a href="javascript:;"  onclick="removeFile(' + id + ');">删除</a></a>' +'  </div>';  
     
       var fileElement = document.getElementById("files_preview");  
       fileElement.innerHTML = fileElement.innerHTML + fileHtml;      
       obj.style.display = 'none';   // 隐藏当前的<input type=”file”/>元素  
       addUploadFile(childnum);  // 插入新的<input type=”file”/>元素  
       count = count + 1; 
   }  
   //  插入新的<input type=”file”/>元素，适合于不同的浏览器（包括IE、FireFox等）  
   function addUploadFile(index)  
   {  
       try  // 用于IE浏览器  
       {     
           var uploadHTML = document.createElement( "<input type='file' class='fileButton' id='file_" + index +   
                                   "' name='file[" + index + "]' onchange='insertNextFile(this)'/>");  
           document.getElementById("files").appendChild(uploadHTML);  
       }  
       catch(e)  // 用于其他浏览器  
       {   
           var uploadObj = document.createElement("input");  
           uploadObj.setAttribute("name", "file[" + index + "]");  
           uploadObj.setAttribute("onchange", "insertNextFile(this)");  
           uploadObj.setAttribute("type", "file");  
           uploadObj.setAttribute("id", "file_" + index);  
           document.getElementById("files").appendChild(uploadObj);  
       }  
   }  
   function removeFile(index)  // 删除当前文件的<div>和<input type=”file”/>元素  
   {  
       document.getElementById("files_preview").removeChild(document.getElementById("file_preview" + index));   
       document.getElementById("files").removeChild(document.getElementById("file_" + index));      
   }  
   function showStatus(obj)  // 显示“正在上传文件”提示信息  
   {  
     document.getElementById("status").style.visibility="visible";  
     document.forms[0].submit();
   }
   function pageClose(){
	   window.close();
   }
   
   $(document).ready(function(){
	     if(status=="success"){
	   		alert("上传成功");
	   		window.returnValue = returnString;
	   		window.close();
	   	}
	});
 </script>
<base target="_self"/>
</head>
<body style="background-color: #E5F5FF;">
<html:form action="uploadFile.do?method=uploadFile" method="post" enctype="multipart/form-data">
<html:hidden name="UploadFileForm" property="flowInstanceMainId"/>
<html:hidden name="UploadFileForm" property="flowInstanceStepUserId"/>
<html:hidden name="UploadFileForm" property="directFlg"/>
<table cellpadding='2' cellspacing='1' border='0' class='border' align=center>
 <tr>
  <td class='Navigation'>文件上传</td>
 </tr>
</table>
<table>
 <tr>
  <td>
	<span id="errorMsg" style="color: red;">
	<html:errors/>
	</span>
  </td>
 </tr>
</table>
   <span id="files"> <%--  在此处插入用于上传文件的input元素 --%>   
     <input type="file" class="fileButton" id="file_0" name="file[0]" onchange="insertNextFile(this)" /> 
   </span>
   <input type="button" class="button" value="上传 " onclick="showStatus(this);"/>
   <input type="button" class="button" value="关闭 " onclick="pageClose();"/>
</html:form>
<p>
    <div id="status" style="visibility: hidden; width: 100%;height: 100%;top:0;text-align: center;position:absolute;filter:alpha(opacity=45);opacity:0.45;z-index:1000;background-color: #FFFFFF">
	  <table height="100%">
			<tr>
				<td height="20%"></td>
			</tr>
			<tr><td height="80%"><img width =60 height = 60 src ="<%=request.getContextPath()%>/images/circle_animation.gif"/><br><p style="color: blue;">正在上传....</p></td></tr>
	  </table>
<!-- 
 　　　<img width =60 height = 60 src ="/images/circle_animation.gif"/><br>
	     正在上传............ -->
	</div>
<p>
<div id="files_preview" style="width: 500px; height: 500px; overflow: auto"></div>  
</body>
</html>