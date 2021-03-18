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
import cn.ewb.cq.framework.form.DepartmentForm;
import cn.ewb.cq.framework.model.DepartmentModel;


public class DepartmentAction extends DispatchAction {

	private DepartmentModel departmentModel = new DepartmentModel();
	private static Logger logger = Logger.getLogger(DepartmentAction.class);
	
	public ActionForward searchDepart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "searchDepart()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		DepartmentForm departmentForm = (DepartmentForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			List<Department> departList = departmentModel.searchDepart(sess);
			if(departList==null||departList.size()==0){
				ActionMessages msgs = new ActionMessages();
				msgs.add("showMsg", new ActionMessage("M001"));
				saveMessages(request, msgs);
			}else{
				departmentForm.setDepartList(departList);
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
	
	public ActionForward saveDepart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "saveDepart()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		DepartmentForm departmentForm = (DepartmentForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			ActionMessages msgs = new ActionMessages();
			String departmentCode = departmentForm.getDepartmentCode();
			String departmentName = departmentForm.getDepartmentName();
			String editId = departmentForm.getEditId();
			String bankCode  = departmentForm.getBankCode();
			String loginUser = SystemUtil.getLoginUser(request);
			boolean errorFlg = false;
			// 更新
			if(StringUtil.isNotNull(editId)){
				departmentModel.updateDepart(sess,editId, departmentName, loginUser,bankCode);
			// 新增	
			}else{
				// 检查重复
				if(departmentModel.checkExistDepart(sess,departmentCode)){
					msgs.add("errorMsg", new ActionMessage("M003",new String[]{"部门"}));
					saveErrors(request, msgs);
					errorFlg = true;
				}else{
					departmentModel.addDepart(sess,departmentCode,departmentName,loginUser,bankCode);
				}
			}
			
			if(!errorFlg){
				msgs.add("infoMsg", new ActionMessage("M002"));
				saveMessages(request, msgs);
			}
			departmentForm.setDepartmentCode("");
			departmentForm.setDepartmentName("");
			departmentForm.setBankCode("");
			departmentForm.setEditId("");
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return searchDepart(mapping,departmentForm,request,response);
	}
	
	public ActionForward delDepart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "delDepart()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		DepartmentForm departmentForm = (DepartmentForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			ActionMessages msgs = new ActionMessages();
			String editId = departmentForm.getEditId();
			String loginUser = SystemUtil.getLoginUser(request);

			departmentModel.delDepart(sess,editId,loginUser);
			msgs.add("infoMsg", new ActionMessage("M004",new String[]{"部门"}));
			saveMessages(request, msgs);
			departmentForm.setDepartmentCode("");
			departmentForm.setDepartmentName("");
			departmentForm.setDepartmentCode("");
			departmentForm.setEditId("");
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return searchDepart(mapping,departmentForm,request,response);
	}
}
