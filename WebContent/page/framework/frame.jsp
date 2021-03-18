<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<% 
response.setHeader("pragma","no-cache");
response.setHeader("cache-control","no-no-cache");
response.setHeader("expries","0");
response.flushBuffer();
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<SCRIPT language="JavaScript" src="js/WSOASFunction.Js" charset="GBK">
</SCRIPT>
<link rel=stylesheet type=text/css href="<%=request.getContextPath()%>/css/HX_Style.css">
<link rel=stylesheet type=text/css href="<%=request.getContextPath()%>/css/Css.css">
<link rel="shortcut icon" href="images/egf.ico" type="image/x-icon"/>
<TITLE>大谷田日语</TITLE>
<script type="text/javascript">
function loginOut(){
	if(confirm("确定退出")){
	   //window.location="loginOut.do?method=loginOut";
		window.location='http://localhost:8081/MYBOOK';   
    }
}
function refurbish(){
	//var frm = document.frames;
	//for (i=0; i < frm.length; i++){
	//	if(frm(i).name=='ifr'){
	
		//}
	//}

}
</script>
</head>

<body style="background: #E5F5FF;">


<table style="WIDTH:100%;HEIGHT:100%" border="0" cellpadding="0" cellspacing="0">
  <tr valign="top">
	  <td valign="middle" class="TOPBg" id="WSOAS_Top">
	   <table width="100%" border="0" cellspacing="1" cellpadding="1">
	    <tr>
	      <td align="left"><span class='LogoStyle'></span></td>
	      <td width="35%" align="right">
	        <table style="WIDTH:100%;HEIGHT:100%" border="0" cellpadding="6" cellspacing="0">
	          <tr class="WSOASTopMenuFont">
	            <td align="right"  style="cursor:hand" onclick="loginOut();"><img src="images/exit.gif" height="30">&nbsp;&nbsp;<br><strong>安全退出</strong></td>
	          </tr>
	        </table>
	      </td>
	    </tr>
	   </table>
	  </td>
  </tr>
  <tr>
   <td height="25" class="ManageMainMenu">
     <table border="0" width="100%" class="ManageMainMenuHref">
       <tr>
         <td>
           <table width='96%' border='0' cellpadding='2' cellspacing='2'>
			<tr><td style="color: blue" >当前用户:<%=request.getSession().getAttribute("userName")%></td></tr>
           </table>
         </td>
         <td align="right"><input type="button" style="cursor: hand;" class="button" value="返回首页" onclick="refurbish();" /></td>
       </tr>
     </table>
   </td>
  </tr>
  
  <tr>
    <td>
      <table style="WIDTH:100%;HEIGHT:100%;background: #E5F5FF;margin:0;padding:0;" border="0" cellpadding="0" cellspacing="0" >
        <tr style="HEIGHT:100%" valign="top">
          <td valign="top" width='200' id="WSOAS_Left" >
            <table style="width:200;HEIGHT:100%;background: #E5F5FF;" border="0" cellpadding="0" cellspacing="0" ID="Table1">
              <tr style="HEIGHT:100%" valign="top">
                 <td valign=top  align=center>       
                   <iframe id="WSOAS_Leftifr"  name="WSOAS_Leftifr" scroll=auto style="WIDTH:100%;HEIGHT:100%" frameborder="0"  src="showMenu.do">
                   </iframe>
                 </td>
              </tr>

            </table>
    </td>
          <td Class="ManageMainBgcolor" valign="middle" width="8">
            <img src="images/goin.gif" width="8" border="0" style="CURSOR:hand" onClick="switchSysBar(this)" title="">
          </td>
          <td valign="top">
            <table style="WIDTH:100%;HEIGHT:100%;background: #E5F5FF;" border="0" cellpadding="0" cellspacing="0" >
              <tr height="1" >
                <td id="tabs">
                  <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr style="height:3;">
                     <td colspan=3></td>
			        </tr>
			        <tr>
			          <td width="100%"  id="run_item_cell">
			          </td>
			        </tr>
                  </table>
                </td>
              </tr>
              <tr >
                <td>
					<!--flowWaitSearch.do?method=flowWaitSearch -->
                  <iframe name="ifr" scrolling="auto" style="WIDTH:100%;HEIGHT:100%;" frameborder="0" src="page/framework/backColor.jsp"></iframe>
                </td>
              </tr>
            </table>
          </td>
          
        </tr>
      </table>
    </td>
  </tr>

</table>


</body>
</html>