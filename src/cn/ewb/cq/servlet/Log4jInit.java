package cn.ewb.cq.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.PropertyConfigurator;

public class Log4jInit extends HttpServlet {
	public void init() throws ServletException {
		String prefix = getServletContext().getRealPath("/");
		String file = getServletConfig().getInitParameter("log4j-config-file");
		// ��Servlet������ȡlog4j�������ļ�
		if (file != null) {
			PropertyConfigurator.configure(prefix + file);
		}
	}
}
