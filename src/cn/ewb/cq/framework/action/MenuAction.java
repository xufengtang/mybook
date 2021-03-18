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
import cn.ewb.cq.domain.Menu;
import cn.ewb.cq.framework.bean.MenuBean;
import cn.ewb.cq.framework.form.MenuForm;
import cn.ewb.cq.framework.model.MenuModel;


public class MenuAction extends DispatchAction {

	private MenuModel menuModel = new MenuModel();
	private static Logger logger = Logger.getLogger(MenuAction.class);
	
	public ActionForward searchMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "searchMenu()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		MenuForm menuForm = (MenuForm) form;
		try{
			// 1级菜单取得
			List<Menu> parentMenuList = menuModel.searchParentMenu();
			// 所有菜单列表取得
			List<MenuBean> menuList = menuModel.searchMenu();
			menuForm.setParentMenuList(parentMenuList);
			// 没有数据时候
			if(menuList==null||menuList.size()==0){
				ActionMessages msgs = new ActionMessages();
				msgs.add("showMsg", new ActionMessage("M001"));
				saveMessages(request, msgs);
			}else{
				menuForm.setMenuList(menuList);
			}
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	public ActionForward saveMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "saveMenu()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		MenuForm menuForm = (MenuForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			ActionMessages msgs = new ActionMessages();
			String editId = menuForm.getEditId();
			String loginUser = SystemUtil.getLoginUser(request);
			boolean errorFlg = false;
			// 更新
			if(StringUtil.isNotNull(editId)){
				if(menuModel.checkHasChildren(sess,editId)){
					msgs.add("errorMsg", new ActionMessage("M003",new String[]{"父级的子级"}));
					saveErrors(request, msgs);
					errorFlg = true;
				}
				//检查通过执行更新
				if(!errorFlg){
					menuModel.updateMenu(sess,editId,menuForm,loginUser);
				}
			}else{
				menuModel.addMenu(sess,menuForm,loginUser);	
			}
			if(!errorFlg){
				msgs.add("infoMsg", new ActionMessage("M002"));
				saveMessages(request, msgs);
			}
			menuForm.setOrderNo("");
			menuForm.setMenuName("");
			menuForm.setRank("1");
			menuForm.setUrl("");
			menuForm.setEditId("");
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return searchMenu(mapping,menuForm,request,response);
	}
	
	public ActionForward delMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "delMenu()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		MenuForm menuForm = (MenuForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			ActionMessages msgs = new ActionMessages();
			String editId = menuForm.getEditId();
			String loginUser = SystemUtil.getLoginUser(request);
			boolean errorFlg = false;
			if(menuModel.checkHasChildren(sess,editId)){
				msgs.add("errorMsg", new ActionMessage("M003",new String[]{"父级的子级"}));
				saveErrors(request, msgs);
				errorFlg = true;
			}else{
				// 执行删除
				menuModel.delMenu(sess,editId,loginUser);
			}
			if(!errorFlg){
				msgs.add("infoMsg", new ActionMessage("M004",new String[]{"菜单"}));
				saveMessages(request, msgs);
			}
			menuForm.setOrderNo("");
			menuForm.setMenuName("");
			menuForm.setRank("1");
			menuForm.setUrl("");
			menuForm.setEditId("");
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return searchMenu(mapping,menuForm,request,response);
	}
}
