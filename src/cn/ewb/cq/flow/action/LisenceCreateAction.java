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
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.flow.bean.LisenceInfoBean;
import cn.ewb.cq.flow.form.LisenceCreateForm;
import cn.ewb.cq.flow.model.LisenceCreateModel;

public class LisenceCreateAction extends DispatchAction{

	private static Logger logger = Logger.getLogger(LisenceCreateAction.class);
	private LisenceCreateModel lisenceCreateModel = new LisenceCreateModel();
	
	
	public ActionForward lisenceCodeSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "lisenceCodeSearch()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));	
		 LisenceCreateForm  lisenceCreateForm = (LisenceCreateForm)form;
		 Session sess = HibernateUtil.getSession();
		 try{
			 List<LisenceInfoBean> lisenceInfoBeanList = lisenceCreateModel.lisenceCodeSearch(sess,lisenceCreateForm);
			 int total =  lisenceCreateModel.selectTotalPage(sess,lisenceCreateForm);
			 
			 
			 lisenceCreateForm.setLisenceInfoList(lisenceInfoBeanList);
			 lisenceCreateForm.setTotalPage(total);
			 
			 if(lisenceInfoBeanList==null||lisenceInfoBeanList.size()==0){
					ActionMessages msgs = new ActionMessages();
					msgs.add("showMsg", new ActionMessage("M001"));
					saveMessages(request, msgs);		 
			 }

			 
		 }catch(Exception ex){
			 throw ex;
		 }finally{
			 HibernateUtil.closeSession();
			 
		 }
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	public ActionForward lisenceCreate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "lisenceCreate()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));	
		 LisenceCreateForm  lisenceCreateForm = (LisenceCreateForm)form;
		 lisenceCreateModel.setLoginUser(SystemUtil.getLoginUser(request));
		
		 try{
			 lisenceCreateModel.lisenceCreate(lisenceCreateForm);
		 }catch(Exception ex){
			 throw ex;
		 }
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	

}
