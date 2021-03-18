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

import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.framework.form.UserInfoEditForm;
import cn.ewb.cq.framework.model.UserInfoEditModel;

public class UserInfoEditAction extends DispatchAction{
	
	private UserInfoEditModel userInfoEditModel = new UserInfoEditModel();
	private static Logger logger = Logger.getLogger(UserInfoEditAction.class);
	
	public ActionForward initUserInfoEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		final String methodName = "initUserInfoEdit()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		UserInfoEditForm  userInfoEditForm = (UserInfoEditForm)form;
		try{
			String loginUser = SystemUtil.getLoginUser(request);
			userInfoEditModel.setLoginUser(loginUser);
			userInfoEditModel.initUserInfoEdit(userInfoEditForm);
			System.out.println(userInfoEditForm.getEditId());
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	public ActionForward saveUserInfoEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		final String methodName = "saveUserInfoEdit()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		UserInfoEditForm  userInfoEditForm = (UserInfoEditForm)form;
		try{
			String loginUser = SystemUtil.getLoginUser(request);
			ActionMessages msgs = new ActionMessages();
			userInfoEditModel.setLoginUser(loginUser);
			userInfoEditModel.saveUserInfoEdit(userInfoEditForm);
			msgs.add("infoMsg", new ActionMessage("M002"));
			saveMessages(request, msgs);
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
}
