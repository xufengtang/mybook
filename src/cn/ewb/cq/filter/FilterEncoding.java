package cn.ewb.cq.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterEncoding implements Filter {
	private String encoding; // �����ַ�����
	private  FilterConfig filterConfig; // ��ʼ������

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		filterConfig=arg0;
		encoding = filterConfig.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// ��web.xml�ļ��ж�ȡencoding��ֵ

		if (arg0.getCharacterEncoding() == null) {
			// ���Ϊ���ȴ�web.xml�еõ�
			String encoding = selectEncoding(arg0);
			if (encoding != null) {
				// �����ַ�������
				arg0.setCharacterEncoding(encoding);
			}
		}
		// ����ִ��
		arg2.doFilter(arg0, arg1);
	}

	// �õ��ַ�����
	private String selectEncoding(ServletRequest request) {
		return encoding;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		filterConfig=null;
	}

}
