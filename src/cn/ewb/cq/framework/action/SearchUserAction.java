package cn.ewb.cq.framework.action;
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
import cn.ewb.cq.domain.Department;
import cn.ewb.cq.framework.bean.UserBean;
import cn.ewb.cq.framework.form.SearchUserForm;
import cn.ewb.cq.framework.model.SearchUserModel;


public class SearchUserAction extends DispatchAction {

	private SearchUserModel searchUserModel = new SearchUserModel();
	private static Logger logger = Logger.getLogger(SearchUserAction.class);
	
	
	public ActionForward searchUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "searchUser()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		SearchUserForm searchUserForm= (SearchUserForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			//查询
			List<UserBean> userList = searchUserModel.selectUserList(sess,searchUserForm);
			// 总页码查询
			int totalPage  = searchUserModel.selectTotalPage(sess,searchUserForm);
			// 查询结果设置
			searchUserForm.setUserList(userList);
			searchUserForm.setTotalPage(totalPage);
			if(userList==null||userList.size()==0){
				ActionMessages msgs = new ActionMessages();
				msgs.add("showMsg", new ActionMessage("M001"));
				saveMessages(request, msgs);
			}
			// 获取下拉列表
			getComboxList(sess,searchUserForm);
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	public ActionForward deleteUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "deleteUser()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		SearchUserForm searchUserForm= (SearchUserForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			ActionMessages msgs = new ActionMessages();
			String loginUser = SystemUtil.getLoginUser(request);
			// 获取查询页面传过来的ID
			String editId = request.getParameter("editId");
			searchUserModel.deleteUser(sess,editId,loginUser);
			msgs.add("infoMsg", new ActionMessage("M004",new String[]{"员工"}));
			saveMessages(request, msgs);
			// 获取下拉列表
			getComboxList(sess,searchUserForm);
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return searchUser(mapping,searchUserForm,request,response);
	}
	
	
	private void getComboxList(Session sess,SearchUserForm searchUserForm)throws Exception{
		List<Department> departList = null;
		departList = searchUserModel.selectDepartList(sess);
		Department addObject = new Department();
		addObject.setId("");
		addObject.setDepartmentName("全部");
		departList.add(0, addObject);
		// 设置
		searchUserForm.setDepartmentList(departList);
	}
}
