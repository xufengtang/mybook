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
import cn.ewb.cq.domain.ListenInfo;
import cn.ewb.cq.flow.form.ListenInfoEditForm;
import cn.ewb.cq.flow.form.ListenInfoSearchForm;
import cn.ewb.cq.flow.model.ListenInfoSearchModel;


public class ListenInfoSearchAction extends DispatchAction{
	
	
	private ListenInfoSearchModel listenInfoSearchModel = new ListenInfoSearchModel();
	private static Logger logger = Logger.getLogger(ListenInfoSearchAction.class);
	
	
	
	public ActionForward searchListenInfoInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "searchListenInfoInit()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
	
		ListenInfoSearchForm listenInfoSearchForm = (ListenInfoSearchForm)form;
		try{
			listenInfoSearchModel.searchListenInfoInit(listenInfoSearchForm);
			
		}catch(Exception ex){
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	
	public ActionForward searchListenInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "searchListenInfo()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		Session sess =HibernateUtil.getSession();
		try{
			ListenInfoSearchForm listenInfoSearchForm = (ListenInfoSearchForm)form;
			List<ListenInfo> listenInfoList = listenInfoSearchModel.searchListenInfo(sess,listenInfoSearchForm);
			int totalPage =listenInfoSearchModel.selectTotalPage(sess,listenInfoSearchForm);
			
			listenInfoSearchForm.setListenInfoList(listenInfoList);
			listenInfoSearchForm.setTotalPage(totalPage);
			if(listenInfoList==null||listenInfoList.size()<1){
				ActionMessages msgs = new ActionMessages();
				msgs.add("showMsg", new ActionMessage("M001"));
				saveMessages(request, msgs);
			}	
			listenInfoSearchModel.searchListenInfoInit(listenInfoSearchForm);
		}catch(Exception ex){
			throw ex;	
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	public ActionForward listenInfoDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "listenInfoDel()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
	
		String editId = request.getParameter("editId");
		ListenInfoSearchForm listenInfoSearchForm = (ListenInfoSearchForm)form;
		listenInfoSearchModel.setLoginUser(SystemUtil.getLoginUser(request));
		try{
			listenInfoSearchModel.listenInfoDel(editId,listenInfoSearchForm);
			ActionMessages msgs = new ActionMessages();
			msgs.add("infoMsg", new ActionMessage("M004",new String[]{"¼ÇÂ¼"}));
			saveMessages(request, msgs);
		}catch(Exception ex){
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	

}
