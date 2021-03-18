package cn.ewb.cq.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ewb.cq.com.StringUtil;

public class FilterLogin implements Filter {
	private  FilterConfig filterConfig; // ³õÊ¼»¯ÅäÖÃ
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		filterConfig=arg0;
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpReq  = (HttpServletRequest)arg0;
		HttpServletResponse httpRes = (HttpServletResponse)arg1;
		HttpSession session = httpReq.getSession();
		String userCode = (String)session.getAttribute("userCode");
		String url = httpReq.getRequestURI();
		
		if(StringUtil.isNotNull(userCode)){
			arg2.doFilter(arg0, arg1);
		}else if(!StringUtil.isNotNull(userCode) && (url.endsWith("/MYBOOK/")||url.endsWith("timeout.jsp")||url.endsWith("login.jsp") || url.indexOf("login.do") > 0 || url.indexOf("/images/") >0 || url.indexOf("/css/") >0 || url.indexOf("/js/") >0)||url.indexOf("/GetUnitList")>0 ||url.indexOf("/GetGrammarList")>0 ||url.indexOf("/GetGrammarDetail")>0||url.indexOf("/GetMyGrammarList")>0||url.indexOf("/SearchGrammarList")>0||url.indexOf("/GetOpenId")>0||url.indexOf("/UseLisence")>0||url.indexOf("/SearchIsMember")>0||url.indexOf("/GetEndDate")>0||url.indexOf("/GetNhkUrl")>0  ||url.indexOf("/SearchListenList")>0||url.indexOf("/GetListenInfo")>0||url.indexOf("/SearchMovieList")>0||url.indexOf("/GetMovieInfo")>0) {
			arg2.doFilter(arg0, arg1);
		}else{
			httpRes.sendRedirect(httpReq.getContextPath() + "/page/framework/timeout.jsp");
		}	
	}
	public void destroy() {
		// TODO Auto-generated method stub
		filterConfig=null;
	}
}
