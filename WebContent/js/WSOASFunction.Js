//取得日期和星期
function initArray(){
	 this.length=initArray.arguments.length
	 for(var i=0;i<this.length;i++)
	 this[i+1]=initArray.arguments[i] 
}
 
function initTime(){
	today=new Date();
    var d=new initArray("星期日","星期一","星期二","星期三","星期四","星期五","星期六"); 
    document.write(today.getYear(),"年",today.getMonth()+1,"月",today.getDate(),"日 ",d[today.getDay()+1],"&nbsp;");  	
}

//显示、隐藏菜单
function switchSysBar(obj){    
	if (document.all("WSOAS_Left").style.display==""){
	 obj.src="images/goout.gif";
	 obj.title='显示菜单';
	 document.all("WSOAS_Left").style.display="none";
	 }
	else{         
	 obj.src="images/goin.gif";
	 obj.title='隐藏菜单';
	 document.all("WSOAS_Left").style.display="" ;
	}
}

//超链接指向,str标题,href地址
function HrefTarget(str,href)
{   
  var str,href;
  var parentfrm=window.parent;
  alert(href);
  alert(parentfrm.frames.item('ifr_1'));
  //parentfrm.frames.item('ifr_1').src=href;
}