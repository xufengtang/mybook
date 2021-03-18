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

import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.Role;
import cn.ewb.cq.framework.bean.AuthorityBean;
import cn.ewb.cq.framework.form.AuthorityForm;
import cn.ewb.cq.framework.model.AuthorityModel;

public class AuthorityAction extends DispatchAction {

	private AuthorityModel authoritymodel = new AuthorityModel();
	private static Logger logger = Logger.getLogger(AuthorityAction.class);
	
	public ActionForward searchAuthority(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "searchAuthority()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		AuthorityForm authorityForm = (AuthorityForm) form;
		try{
			List<Role> roleList = authoritymodel.searchRoleList();
			// 角色LIST设置
			authorityForm.setRoleList(roleList);
			// 取得该角色权限
			if(roleList!=null&&roleList.size()>0){
				List<AuthorityBean> authorityList;
				if(StringUtil.isNotNull(authorityForm.getSelectRole())){
					authorityList =  authoritymodel.searchAuthorityList(authorityForm.getSelectRole());
				}else{
					authorityList =  authoritymodel.searchAuthorityList(roleList.get(0).getId());
				}
				// 权限LIST设置
				authorityForm.setAuthorityList(authorityList);
			}else{
				ActionMessages msgs = new ActionMessages();
				msgs.add("showMsg", new ActionMessage("M001"));
				saveMessages(request, msgs);
			}
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	public ActionForward saveAuthority(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "saveAuthority()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		AuthorityForm authorityForm = (AuthorityForm) form;
		try{
			String loginUser = SystemUtil.getLoginUser(request);
			String roleId = authorityForm.getSelectRole();
			List<AuthorityBean> authorityList = authorityForm.getAuthorityList();
			// 权限更新
			authoritymodel.updateAuthority(roleId, authorityList, loginUser);
			ActionMessages msgs = new ActionMessages();
			msgs.add("infoMsg", new ActionMessage("M002"));
			saveMessages(request, msgs);
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		//return mapping.findForward("success");
		return searchAuthority(mapping,authorityForm,request,response);
		
	}
	
}
