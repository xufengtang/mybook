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
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.flow.form.ListenInfoEditForm;
import cn.ewb.cq.flow.model.ListenInfoEditModel;

public class ListenInfoEditAction extends DispatchAction{

	
	
	private ListenInfoEditModel listenInfoEditModel = new ListenInfoEditModel();
	private static Logger logger = Logger.getLogger(ListenInfoEditAction.class);
	
	
	
	public ActionForward listenInfoEditInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "listenInfoEditInit()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		String editId  = request.getParameter("editId");

		ListenInfoEditForm listenInfoEditForm = (ListenInfoEditForm)form;
		if(StringUtil.isNotNull(editId)){
			listenInfoEditForm.setEditId(editId);
		}
		try{
			listenInfoEditModel.listenInfoEditInit(listenInfoEditForm);
			
		}catch(Exception ex){
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	public ActionForward saveListenInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "saveListenInfo()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
	
		ListenInfoEditForm listenInfoEditForm = (ListenInfoEditForm)form;
		listenInfoEditModel.setLoginUser(SystemUtil.getLoginUser(request));
		try{
			listenInfoEditModel.saveListenInfo(listenInfoEditForm);
			ActionMessages msgs = new ActionMessages();
			msgs.add("infoMsg", new ActionMessage("M002"));
			saveMessages(request, msgs);
		}catch(Exception ex){
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	
}
