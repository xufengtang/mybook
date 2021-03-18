package cn.ewb.cq.framework.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Session;

import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.framework.form.PwdInitForm;
import cn.ewb.cq.framework.model.PwdInitModel;

public class PwdInitAction extends Action {
	private PwdInitModel pwdModel = new PwdInitModel();
	private static Logger logger = Logger.getLogger(PwdInitAction.class);
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "pwdInitChange()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		PwdInitForm pwdForm = (PwdInitForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			String loginUser = SystemUtil.getLoginUser(request);
			ActionMessages msgs = new ActionMessages();
			boolean errorFlg = false;
			String oldPwd = pwdForm.getOldPwd();
			String newPwd = pwdForm.getNewPwd();
			String surePwd = pwdForm.getSurePwd();
			String userId = "";
			if(!StringUtil.isNotNull(oldPwd)){
				msgs.add("failMsg", new ActionMessage("M005",new String[]{"旧密码"}));
				errorFlg=true;
			}else if(!StringUtil.isNotNull(newPwd)){
				msgs.add("failMsg", new ActionMessage("M005",new String[]{"新密码"}));
				errorFlg=true;
			}else if(!StringUtil.isNotNull(surePwd)){
				msgs.add("failMsg", new ActionMessage("M005",new String[]{"确认密码"}));
				errorFlg=true;
			}else if(StringUtil.isLessLenth(newPwd, 6)){
				msgs.add("failMsg", new ActionMessage("M006",new String[]{"新密码","6"}));
				errorFlg=true;
			}else if(!newPwd.equals(surePwd)){
				msgs.add("failMsg", new ActionMessage("pwd.pwdfail"));
				errorFlg=true;
			}else if(!StringUtil.isNotNull(userId=pwdModel.searchUserId(sess,loginUser,oldPwd))){
				// 查询旧密码正确与否
				msgs.add("failMsg", new ActionMessage("pwd.oldpwdfail"));
				errorFlg=true;
			}else{
				// 密码进行更新
				pwdModel.pwdChange(sess,userId,newPwd,loginUser);
			}
			if(!errorFlg){
				msgs.add("infoMsg", new ActionMessage("M007"));
				saveMessages(request, msgs);
				pwdForm.setStatus("success");
			}else{
				saveErrors(request, msgs);
			}
			pwdForm.setOldPwd("");
			pwdForm.setNewPwd("");
			pwdForm.setSurePwd("");
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
}
