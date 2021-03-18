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
import cn.ewb.cq.domain.Role;
import cn.ewb.cq.framework.form.RoleForm;
import cn.ewb.cq.framework.model.RoleModel;


public class RoleAction extends DispatchAction {

	private RoleModel roleModel = new RoleModel();
	private static Logger logger = Logger.getLogger(RoleAction.class);
	
	public ActionForward searchRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "searchRole()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		RoleForm roleForm = (RoleForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			List<Role> roleList = roleModel.searchRole(sess);
			if(roleList==null||roleList.size()==0){
				ActionMessages msgs = new ActionMessages();
				msgs.add("showMsg", new ActionMessage("M001"));
				saveMessages(request, msgs);
			}else{
				roleForm.setRoleList(roleList);
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
	
	public ActionForward saveRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "saveRole()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		RoleForm roleForm = (RoleForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			ActionMessages msgs = new ActionMessages();
			String roleName = roleForm.getRoleName();
			String editId = roleForm.getEditId();
			String loginUser = SystemUtil.getLoginUser(request);
			boolean errorFlg = false;
			// 更新
			if(StringUtil.isNotNull(editId)){
				roleModel.updateRole(sess,editId, roleName, loginUser);
			}else{
				// 检查重复
				if(roleModel.checkExistRole(sess,roleName)){
					msgs.add("errorMsg", new ActionMessage("M003",new String[]{"角色"}));
					saveErrors(request, msgs);
					errorFlg = true;
				}else{
					roleModel.addRole(sess,roleName, loginUser);
				}
			}
			if(!errorFlg){
				msgs.add("infoMsg", new ActionMessage("M002"));
				saveMessages(request, msgs);
			}
			roleForm.setRoleName("");
			roleForm.setEditId("");
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return searchRole(mapping,roleForm,request,response);
	}
	
	public ActionForward delRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "delRole()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		RoleForm roleForm = (RoleForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			ActionMessages msgs = new ActionMessages();
			String editId = roleForm.getEditId();
			String loginUser = SystemUtil.getLoginUser(request);
			roleModel.delRole(sess,editId, loginUser);
			msgs.add("infoMsg", new ActionMessage("M004",new String[]{"角色"}));
			saveMessages(request, msgs);
			roleForm.setRoleName("");
			roleForm.setEditId("");
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return searchRole(mapping,roleForm,request,response);
	}
}
