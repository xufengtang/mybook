package cn.ewb.cq.flow.action;

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
import cn.ewb.cq.flow.form.NHKInfoForm;
import cn.ewb.cq.flow.model.NHKInfoModel;

public class NHKInfoAction extends DispatchAction{
	
	
	private NHKInfoModel nhkInfoModel = new NHKInfoModel();
	private static Logger logger = Logger.getLogger(NHKInfoAction.class);
	
	
	public ActionForward initNHKInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "initNHKInfo()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		try{
				
			NHKInfoForm  nhkInfoForm =  (NHKInfoForm) form;
			nhkInfoModel.initNHKInfo(nhkInfoForm);		
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	public ActionForward saveUrl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "saveUrl()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		try{
			nhkInfoModel.setLoginUser(SystemUtil.getLoginUser(request));
			NHKInfoForm  nhkInfoForm =  (NHKInfoForm) form;
			nhkInfoModel.saveUrl(nhkInfoForm);

			ActionMessages msgs = new ActionMessages();
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
