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
import cn.ewb.cq.flow.form.UnitEditForm;
import cn.ewb.cq.flow.model.UnitEditModel;


public class UnitEditAction extends DispatchAction {

	private UnitEditModel unitEditModel = new UnitEditModel();
	private static Logger logger = Logger.getLogger(UnitEditAction.class);
	
	public ActionForward initUnit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "initUnit()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		UnitEditForm unitEditForm = (UnitEditForm) form;
		try{
			String editId = request.getParameter("editId");
			if(StringUtil.isNotNull(editId)){
				unitEditForm.setEditId(editId);
				unitEditModel.initUnit(unitEditForm);
				
			}
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	public ActionForward saveUnit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "saveUnit()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		UnitEditForm unitEditForm = (UnitEditForm) form;
		try{
			unitEditModel.setLoginUser(SystemUtil.getLoginUser(request));
			ActionMessages msgs = new ActionMessages();
			unitEditModel.saveUnit(unitEditForm);
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
