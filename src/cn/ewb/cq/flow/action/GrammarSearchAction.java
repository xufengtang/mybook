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
import cn.ewb.cq.flow.bean.GrammarBean;
import cn.ewb.cq.flow.form.GrammarSearchForm;
import cn.ewb.cq.flow.model.GrammarSearchModel;

public class GrammarSearchAction extends DispatchAction{
	
	private GrammarSearchModel grammarSearchModel = new GrammarSearchModel();
	private static Logger logger = Logger.getLogger(GrammarSearchAction.class);
	

	public ActionForward initSearchGrammar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "initSearchGrammar()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		GrammarSearchForm grammarSearchForm = (GrammarSearchForm) form;
		try{
			grammarSearchModel.initSearchGrammar(grammarSearchForm);		
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	public ActionForward grammarSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "grammarSearch()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		GrammarSearchForm grammarSearchForm = (GrammarSearchForm) form;
		Session sess  = HibernateUtil.getSession();
		try{
			List<GrammarBean> grammarList =  grammarSearchModel.grammarSearch(sess,grammarSearchForm);
			int total = grammarSearchModel.selectTotalPage(sess, grammarSearchForm);
			grammarSearchForm.setGrammarBeanList(grammarList);
			grammarSearchForm.setTotalPage(total);
			
			if(grammarList==null||grammarList.size()==0){
				ActionMessages msgs = new ActionMessages();
				msgs.add("showMsg", new ActionMessage("M001"));
				saveMessages(request, msgs);
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
	
	
	public ActionForward deleteGrammar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "deleteGrammar()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		
		String editId = request.getParameter("editId");
		GrammarSearchForm grammarSearchForm = (GrammarSearchForm) form;
		grammarSearchForm.setEditId(editId);
		Session sess  = HibernateUtil.getSession();
		try{
			grammarSearchModel.setLoginUser(SystemUtil.getLoginUser(request));
			grammarSearchModel.deleteGrammar(sess,grammarSearchForm);

			ActionMessages msgs = new ActionMessages();
			msgs.add("infoMsg", new ActionMessage("M004",new String[]{"Óï·¨"}));
			saveMessages(request, msgs);
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return grammarSearch(mapping,form,request,response);
	}
	
	
	
	
}
