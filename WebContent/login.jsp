<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<HTML>
<HEAD>
<TITLE>夏目日语</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<LINK href="css/admin.css" type="text/css" rel="stylesheet">
<link rel="shortcut icon" href="images/egf.ico" type="image/x-icon"/>
<script type="text/javascript" src="js/validate.js"></script>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script language="javascript">
document.onkeydown = keyDownSearch;
function keyDownSearch(e){
	var theEvent = e||window.event;
	var code = theEvent.keyCode||thenEvent.which||theEvent.charCode;
    if(code==13){
		fm_submit();
	}
}

function fm_submit(){
	//if(navigator.userAgent.indexOf("MSIE")<0){
		//alert("请使用IE游览器");
		//return;
//	}
	// if(!!window.ActiveXObject||"ActiveXObject" in window){
		 var userId = $("#userCode").val();
		 if(isNull(userId)){
			 $("#errorsMsg").html("用户名不能为空");
		 }else{
			 document.forms[0].submit();
		 }
	 
	// }else{
	//		alert("请使用IE游览器");
		//	return;
	// }
 }

$(document).ready(function(){
	if(!!window.ActiveXObject||"ActiveXObject" in window){
		
	}else{
		alert("请使用IE游览器");
	}
	//if(navigator.userAgent.indexOf("MSIE")<0){
		//alert("请使用IE游览器");
	//}
});
</script>

</HEAD>
<BODY>
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" bgColor=#002779 
border=0>
  <TR>
    <TD align=middle>
      <span id="errorsMsg" style="color: red"><html:errors/></span>
      <TABLE cellSpacing=0 cellPadding=0 width=468 border=0>
        <TR>
          <TD><IMG height=23 src="images/login_1.jpg" width=468></TD></TR>
        <TR>
          <TD><IMG height=147 src="images/login_2.jpg" 
            width=468></TD></TR></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width=468 bgColor=#ffffff border=0>
        <TR>
          <TD width=16><IMG height=122 src="images/login_3.jpg" 
            width=16></TD>
          <TD align=middle>
            <TABLE cellSpacing=0 cellPadding=0 width=230 border=0>
              <html:form method="post" action="login.do?method=login">
              <TR height=5>
                <TD width=5></TD>
                <TD width=56></TD>
                <TD></TD></TR>
              <TR height=36>
                <TD></TD>
                <TD>用户名</TD>
                <TD>
				  <html:text  styleId="userCode" property="userCode" style="BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-BOTTOM: #000000 1px solid" maxlength="15"></html:text></TD>
              </TR>
              <TR height=36>
                <TD>&nbsp; </TD>
                <TD>密     码</TD>
                <TD>
				  <html:password property="pwd" style="BORDER-RIGHT: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; BORDER-BOTTOM: #000000 1px solid" maxlength="15"></html:password></TD></TR>
              <TR height=5>
                <TD colSpan=3></TD>
              </TR>
              <TR>
                <TD>&nbsp;</TD>
                <TD>&nbsp;</TD>
                <TD>
  				   <img src="images/bt_login.gif" height=18 width=70 style="CURSOR:hand" onclick="fm_submit();"/>
				</TD>
			 </TR>
                 </html:form>
            </TABLE></TD>
          <TD width=16><IMG height=122 src="images/login_4.jpg" 
            width=16></TD></TR></TABLE>
      <TABLE cellSpacing=0 cellPadding=0 width=468 border=0>
        <TR>
          <TD><IMG height=16 src="images/login_5.jpg" 
          width=468>
          </TD>
       </TR>
      </TABLE>
</BODY>
</HTML>
