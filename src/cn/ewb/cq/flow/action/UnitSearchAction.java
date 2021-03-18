package cn.ewb.cq.flow.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.Session;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.flow.bean.UnitBean;
import cn.ewb.cq.flow.form.UnitEditForm;
import cn.ewb.cq.flow.form.UnitSearchForm;
import cn.ewb.cq.flow.model.UnitSearchModel;

public class UnitSearchAction extends DispatchAction{
	
	
	private UnitSearchModel unitSearchModel = new UnitSearchModel();
	private static Logger logger = Logger.getLogger(UnitSearchAction.class);
	
	
	public ActionForward searchUnitInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "searchUnitInit()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		UnitSearchForm unitSearchForm = (UnitSearchForm)form;
		unitSearchForm.setLevelNameSearch("1");
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return searchUnit(mapping,unitSearchForm,request,response);
	}
	
	
	
	public ActionForward searchUnit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "searchUnit()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		UnitSearchForm unitSearchForm = (UnitSearchForm)form;
		try{
			Session sess = HibernateUtil.getSession();
			List <UnitBean> unitBeanList = unitSearchModel.searchUnit(sess,unitSearchForm);
			if(unitBeanList==null||unitBeanList.size()<1){
				ActionMessages msgs = new ActionMessages();
				msgs.add("showMsg", new ActionMessage("M001"));
				saveMessages(request, msgs);
			}else{
				unitSearchForm.setUnitBeanList(unitBeanList);
			}
			
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	public ActionForward deleteUnit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		final String methodName = "deleteUnit()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		UnitSearchForm unitSearchForm = (UnitSearchForm) form;
		try{
			String editId = request.getParameter("editId");
			if(StringUtil.isNotNull(editId)){
				unitSearchModel.deleteUnit(editId,unitSearchForm);	
			}
			ActionMessages msgs = new ActionMessages();
			msgs.add("infoMsg", new ActionMessage("M002"));
			saveMessages(request, msgs);
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return searchUnit(mapping,unitSearchForm,request,response);
	}

}
