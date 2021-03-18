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
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.Department;
import cn.ewb.cq.domain.Post;
import cn.ewb.cq.domain.Role;
import cn.ewb.cq.framework.form.UserEditForm;
import cn.ewb.cq.framework.model.UserEditModel;

public class UserEditAction extends DispatchAction {

	private UserEditModel userEditModel = new UserEditModel();
	private static Logger logger = Logger.getLogger(UserEditAction.class);
	
	
	public ActionForward initEditUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "initEditUser()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		UserEditForm userEditForm= (UserEditForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			// 获取查询页面传过来的ID
			String editId = request.getParameter("editId");
			if(StringUtil.isNotNull(editId)){
				userEditModel.selectEditUser(sess,userEditForm,editId);
			}
			// 获取下拉列表
			getComboxList(sess,userEditForm);
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	public ActionForward addUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "addUser()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		UserEditForm userEditForm= (UserEditForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			ActionMessages msgs = new ActionMessages();
			boolean errorFlg = false;
			String loginUser = SystemUtil.getLoginUser(request);
			// 用户存在检查
			if(userEditModel.isExistUser(sess,userEditForm.getUserCode())){
				msgs.add("errorMsg", new ActionMessage("M003",new String[]{"员工"}));
				saveErrors(request, msgs);
				errorFlg = true;
			// 部门(属)与部门(兼)冲突检查	
			}else if (userEditModel.isErrorUserDepart(userEditForm.getDepartId(),userEditForm.getUserDepartId())){
				msgs.add("errorMsg", new ActionMessage("user.departfail"));
				saveErrors(request, msgs);
				errorFlg = true;
			}else{
				userEditModel.addUser(sess,userEditForm,loginUser);
			}
			if(!errorFlg){
				clearFormData(userEditForm);
				msgs.add("infoMsg", new ActionMessage("M002"));
				saveMessages(request, msgs);
				
				
			}
			// 获取下拉列表
			getComboxList(sess,userEditForm);
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	public ActionForward editUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String methodName = "editUser()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		UserEditForm userEditForm= (UserEditForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			ActionMessages msgs = new ActionMessages();
			boolean errorFlg = false;
			String loginUser = SystemUtil.getLoginUser(request);
			if (userEditModel.isErrorUserDepart(userEditForm.getDepartId(),userEditForm.getUserDepartId())){
				msgs.add("errorMsg", new ActionMessage("user.departfail"));
				saveErrors(request, msgs);
				errorFlg = true;
			}else{
				userEditModel.updateUser(sess,userEditForm,loginUser);
			}
			if(!errorFlg){
				//clearFormData(userEditForm);
				msgs.add("infoMsg", new ActionMessage("M002"));
				saveMessages(request, msgs);	
			}
			// 获取下拉列表
			getComboxList(sess,userEditForm);
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	public ActionForward pwdReset(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String methodName = "pwdReset()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		UserEditForm userEditForm= (UserEditForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			ActionMessages msgs = new ActionMessages();
			String loginUser = SystemUtil.getLoginUser(request);
			userEditModel.pwdReset(sess,userEditForm,loginUser);
			msgs.add("infoMsg", new ActionMessage("user.pwdinfo"));
			saveMessages(request, msgs);	
			// 获取下拉列表
			getComboxList(sess,userEditForm);
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	private void getComboxList(Session sess,UserEditForm userEditForm)throws Exception{
		List<Post> postList = null;
		List<Role> roleList = null;
		List<Department> departList = null;
		// 列表取得
		postList = userEditModel.selectPostList(sess);
		roleList = userEditModel.selectRoleList(sess);
		departList = userEditModel.selectDepartList(sess);
		// 设置
		userEditForm.setPostList(postList);
		userEditForm.setRoleList(roleList);
		userEditForm.setDepartmentList(departList);
	}
	
	private void clearFormData(UserEditForm userEditForm)throws Exception{
		userEditForm.setUserCode("");
		userEditForm.setUserName("");
		userEditForm.setDepartId("");
		userEditForm.setPostId("");
		userEditForm.setRoleId(null);
		userEditForm.setUserDepartId(null);
	}
}
