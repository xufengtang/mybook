var g_dis_ifr=0;/*当前显示*/
var g_use_ifr=new Array();/*使用情况汇报*/
var g_current_tab=null//tab对象
var g_pre_tab=null//前一个tab对象

//初始化打开程序,只有第一个是正确的
for (var i=0;i<7;i++)
{g_use_ifr[i]=0;}
//设置第一个tab

//刷新当前TAB
function f_refresh_current_item()
{
	window.frames.item("ifr_"+g_dis_ifr).location.reload();
	
}

function f_close_current_all()
{  
   if(!confirm('确定要关闭所有打开窗口吗？')){ return false;}

	  for(i=6;i>0;i--){			
		 try {
			var _delTab=document.all["tab"+i];			
			//隐藏
			document.all("ifr_"+i).src="about:blank";
			document.all("ifr_"+i).style.display="none";
			
			if (g_pre_tab==null)
			  g_pre_tab=document.all["tab"+i];
			
			g_use_ifr[i]=0
			g_current_tab=null;
			f_select_tab(g_pre_tab);
			_delTab.outerHTML="";
			}
		catch(err){;}
		finally {;}
		 }		   
	f_select_tab(document.all.tab7);			
}
	
//关闭当前TAB
function f_close_current_item()
{
	if ((g_dis_ifr!=0)&&(g_dis_ifr!=7))
	{ 
	   
		var _delTab=g_current_tab;
		var _delifr=g_dis_ifr;		
		
		//隐藏
		document.all("ifr_"+_delifr).src="about:blank";
		document.all("ifr_"+_delifr).style.display="none";
		
		if (g_pre_tab==null) g_pre_tab=document.all.tab7;
		
		g_current_tab=null;
		f_select_tab(g_pre_tab)
		//删除tab
		_delTab.outerHTML="";
		//设置为未用标记
		g_use_ifr[_delifr]=0
		g_dis_ifr=parseInt(g_pre_tab.id.replace("tab",""));
		g_pre_tab=null;		
	}
}

//TAB的选择,点进处理
function f_select_tab(_obj)
{
	//变颜色
	if (_obj==g_current_tab)
	return
	_obj.className="comtabsl"
	_obj.innerHTML=_obj.innerHTML.replace("comtabr","comtabsr")
	
	if (g_current_tab!=null)
	{
		g_current_tab.className="comtabl"
		g_current_tab.innerHTML=g_current_tab.innerHTML.replace("comtabsr","comtabr")
		g_pre_tab=g_current_tab
		f_refresh_current_item()
	}
	
	g_current_tab=_obj

	//切换程序
	
	//隐藏
	document.all("ifr_"+g_dis_ifr).style.display="none";
	g_dis_ifr=parseInt(_obj.id.replace("tab",""))
	document.all("ifr_"+g_dis_ifr).style.display="";
	if (g_dis_ifr==0)
	{
		if (window.frames.item('ifr_0').location.href.indexOf("about:blank")>=0)
		window.frames.item('ifr_0').location.href='HXDdesk/WSOASMyTask.Asp';
	}
}
//运行应用程序,打开新的
function run_item(txt,act,_available)
{
	var _obj
	//计算现有运行程序个数并取得可用ifr
	for (var i=1;i<=6;i++)
	{
		if (g_use_ifr[i]==0)
		{
			if (_available==-1)
			{
				_available=i
				break;
			}
		}
	}
	
	if(_available==-1)
	{
		//注意！系统最多能同时打开六个应用程序，启用最后一个
		 var _win=true;		
		 for (var i=1;i<=6;i++)
		  {			 
			if(getparastr(window.frames.item("ifr_"+i).location.href,act))			
			  { 
			    _win=false;	
				_available=i;
			    break;
			   }	
			}
			
				if(_win==true)
				{
					//切换TAB
					_obj=document.all.tab6
					g_current_tab.className="comtabl"
					g_current_tab.innerHTML=g_current_tab.innerHTML.replace("comtabsr","comtabr")
					g_current_tab=_obj
					
					//切换程序,隐藏
					document.all("ifr_"+g_dis_ifr).style.display="none";
					g_dis_ifr=6
					//更换文字及链接
					g_current_tab=document.all.tab6
					window.frames.item("ifr_"+g_dis_ifr).location.href=act;
					document.all("ifr_"+g_dis_ifr).style.display="";
					g_current_tab.all.tags("SPAN")[0].innerText=txt;		
					_obj.className="comtabsl"
					_obj.innerHTML=_obj.innerHTML.replace("comtabr","comtabsr")
			    }
				else{
						   _obj=document.all["tab"+_available];
						   f_select_tab(_obj);
						   g_dis_ifr=_available;
						   f_refresh_current_item()

				}
		}
	else
	{
		//创建新的
		var _cell=document.all.run_item_cell
		var _id=g_current_tab.id
		var _win=true;		
		 for (var i=1;i<=6;i++)
		  {
			if(getparastr(window.frames.item("ifr_"+i).location.href,act))			
			  { 
			    _win=false;	
				_available=i;
			    break;
			   }	
			}
		if(_win==true)
		{
			_cell.innerHTML += "<span class=comtabl id='tab"+_available+"' onclick='f_select_tab(this)' onDblclick='f_select_tab(this);f_close_current_item();'><span class=comtabr>"+txt+" <img src='HXimages/ManageMain/close.gif' style='CURSOR:hand' onClick='f_close_current_item()' onmouseover=this.src='HXimages/ManageMain/close1.gif' onmouseout=this.src='HXimages/ManageMain/close.gif' title='关闭'></span></span>";
		   _obj=document.all["tab"+_available];
		   g_current_tab=document.all[_id];
		   window.frames.item("ifr_"+_available).location.href=act;
		   f_select_tab(_obj);
		   g_dis_ifr=_available;
		 }
		else{
			   _obj=document.all["tab"+_available];
			   f_select_tab(_obj);
			   g_dis_ifr=_available;
		}
	}
	g_use_ifr[g_dis_ifr]=1
}


function getparastr(hrefstr,actstr)
  {
	var hrefstr
	var actstr=actstr.replace("../","")
    if(hrefstr.lastIndexOf(actstr)!=-1)
	   {return true;}
	else	
		{return false;}
}