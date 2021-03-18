package cn.ewb.cq.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterEncoding implements Filter {
	private String encoding; // 接收字符编码
	private  FilterConfig filterConfig; // 初始化配置

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		filterConfig=arg0;
		encoding = filterConfig.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 从web.xml文件中读取encoding的值

		if (arg0.getCharacterEncoding() == null) {
			// 如果为空先从web.xml中得到
			String encoding = selectEncoding(arg0);
			if (encoding != null) {
				// 设置字符集编码
				arg0.setCharacterEncoding(encoding);
			}
		}
		// 继续执行
		arg2.doFilter(arg0, arg1);
	}

	// 得到字符编码
	private String selectEncoding(ServletRequest request) {
		return encoding;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		filterConfig=null;
	}

}
