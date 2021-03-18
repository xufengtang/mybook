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
import cn.ewb.cq.domain.Post;
import cn.ewb.cq.framework.form.PostForm;
import cn.ewb.cq.framework.model.PostModel;



public class PostAction extends DispatchAction {

	private PostModel postModel = new PostModel();
	private static Logger logger = Logger.getLogger(PostAction.class);
	
	public ActionForward searchPost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "searchPost()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		PostForm postForm= (PostForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			List<Post> postList = postModel.searchPost(sess);
			if(postList==null||postList.size()==0){
				ActionMessages msgs = new ActionMessages();
				msgs.add("showMsg", new ActionMessage("M001"));
				saveMessages(request, msgs);
			}else{
				postForm.setPostList(postList);
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
	
	public ActionForward savePost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "savePost()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		PostForm postForm= (PostForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			ActionMessages msgs = new ActionMessages();
			String postCode = postForm.getPostCode();
			String postName = postForm.getPostName();
			String editId = postForm.getEditId();
			String loginUser = SystemUtil.getLoginUser(request);
			boolean errorFlg = false;
			// 更新
			if(StringUtil.isNotNull(editId)){
				postModel.updatePost(sess,editId,postName,loginUser);
			}else{
				// 检查重复
				if(postModel.checkExistPost(sess,postCode)){
					msgs.add("errorMsg", new ActionMessage("M003",new String[]{"职务"}));
					saveErrors(request, msgs);
					errorFlg = true;
				}else{
					postModel.addPost(sess,postCode,postName,loginUser);
				}
			}
			if(!errorFlg){
				msgs.add("infoMsg", new ActionMessage("M002"));
				saveMessages(request, msgs);
			}
			postForm.setPostCode("");
			postForm.setPostName("");
			postForm.setEditId("");		
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return searchPost(mapping,postForm,request,response);
	}
	
	public ActionForward delPost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "delPost()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		PostForm postForm= (PostForm) form;
		Session sess = HibernateUtil.getSession();
		try{
			ActionMessages msgs = new ActionMessages();
			String editId = postForm.getEditId();
			String loginUser = SystemUtil.getLoginUser(request);
			postModel.delPost(sess,editId, loginUser);
			msgs.add("infoMsg", new ActionMessage("M004",new String[]{"职务"}));
			saveMessages(request, msgs);
			postForm.setPostCode("");
			postForm.setPostName("");
			postForm.setEditId("");
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return searchPost(mapping,postForm,request,response);
	}
}
