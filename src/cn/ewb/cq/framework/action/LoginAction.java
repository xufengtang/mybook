package cn.ewb.cq.framework.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.framework.form.LoginForm;
import cn.ewb.cq.framework.model.LoginModel;

public class LoginAction extends DispatchAction {
	private LoginModel loginModel = new LoginModel();
	private static Logger logger = Logger.getLogger(LoginAction.class);

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "login()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		LoginForm loginform = (LoginForm) form;
		String forwardName = "";
		try{
			String userCode = loginform.getUserCode();
			String pwd = loginform.getPwd();
			String comPwd = SystemUtil.getProperties(request).getProperty("SYS_PWD");
			String[] successUserCode = loginModel.checkLogin(userCode,pwd,comPwd);
			if(StringUtil.isNotNull(successUserCode[0])){
				request.getSession().setAttribute("userCode", successUserCode[0]);
				request.getSession().setAttribute("userName", successUserCode[1]);
				// TODO Auto-generated method stub
				if("pwdNotInit".equals(successUserCode[2])){
					forwardName = "success";
				}else{
					// 统一认证上线，屏蔽
					//forwardName = "pwdInit";
					forwardName = "success";
				}
			}else{
				ActionMessages msgs = new ActionMessages();
				msgs.add("failMsg", new ActionMessage("login.loginfail"));
				saveErrors(request, msgs);
				// TODO Auto-generated method stub
				
				forwardName = "fail";
			}
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward(forwardName);
	}
	
	public ActionForward loginOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "loginOut()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		request.getSession().removeAttribute("userCode");
		request.getSession().removeAttribute("floderId");
//		request.getSession().removeAttribute("employee");
//		request.getSession().removeAttribute("postCode");
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
}
