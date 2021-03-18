<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<% 
response.setHeader("pragma","no-cache");
response.setHeader("cache-control","no-no-cache");
response.setHeader("expries","0");
response.flushBuffer();
%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet type=text/css href="<%=request.getContextPath()%>/css/menu.css">
<title>左边导航</title>
<script language="javascript">
function Switchmenup(obj){
   var el = document.getElementById("menup"+obj);
   if(el.className=="menuptbg_1"){
       var ar = document.getElementById("menup_"+obj);
       ar.style.display = "block";
       el.className="menuptbg_2";
   }else{
        var ar2 = document.getElementById("menup_"+obj);
        ar2.style.display = "none";
        el.className="menuptbg_1";
   }
}
</script>
</head>
<body style="background: #E5F5FF;">
    <div id="menup">			
	<logic:notEmpty name="ShowMenuForm"  property="treeList">
		<logic:iterate id="treeNode" name="ShowMenuForm" property="treeList" indexId="indexid">
			<div id="menup${indexid}" class="menuptbg_2" onclick="Switchmenup(${indexid})"><bean:write name="treeNode" property="menuName"/></div>
			<ul id="menup_${indexid}" class="submenup" style="display: block">
            <table cellspacing="0" cellpadding="0" width="100%" border="0" class="mbox">
			<logic:iterate id="treeNodeChild" name="treeNode" property="nodeList">
               <tr>
                  <td class="mt1">
                      <a style="text-decoration: none; color: #333;"><a href="<bean:write name="treeNodeChild" property="url"/>"  target="ifr"><bean:write name="treeNodeChild" property="menuName"/></a></a>
                  </td>
               </tr>
 			</logic:iterate>
            </table>
			</ul>
        </logic:iterate>
    </logic:notEmpty>
   </div>
</body>
</html>