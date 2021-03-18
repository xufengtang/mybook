package cn.ewb.cq.framework.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.framework.bean.TreeNodeBean;
import cn.ewb.cq.framework.form.ShowMenuForm;
import cn.ewb.cq.framework.model.ShowMenuModel;

public class ShowMenuAction extends Action {
	private ShowMenuModel showMenuModel = new ShowMenuModel();
	private static Logger logger = Logger.getLogger(LoginAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "showMenu()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		ShowMenuForm showMenuForm = (ShowMenuForm) form;
		try{
			String userCode = (String)request.getSession().getAttribute("userCode");
			List<TreeNodeBean> menuTree = showMenuModel.readMenuTree(userCode);
			showMenuForm.setTreeList(menuTree);

		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
}
