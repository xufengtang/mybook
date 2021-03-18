package cn.ewb.cq.flow.action;

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
import cn.ewb.cq.flow.form.MovieInfoEditForm;
import cn.ewb.cq.flow.model.MovieInfoEditModel;

public class MovieInfoEditAction extends DispatchAction{
	
	private MovieInfoEditModel movieInfoEditModel = new MovieInfoEditModel();
	private static Logger logger = Logger.getLogger(MovieInfoEditAction.class);
	
	
	public ActionForward movieInfoEditInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "movieInfoEditInit()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		String editId  = request.getParameter("editId");
		MovieInfoEditForm movieInfoEditForm = (MovieInfoEditForm)form;
		if(StringUtil.isNotNull(editId)){
			movieInfoEditForm.setEditId(editId);
		}
		try{
			
			movieInfoEditModel.movieInfoEditInit(movieInfoEditForm);
			
		}catch(Exception ex){
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	public ActionForward saveMovieInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "saveMovieInfo()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		
		MovieInfoEditForm movieInfoEditForm = (MovieInfoEditForm)form;
		movieInfoEditModel.setLoginUser(SystemUtil.getLoginUser(request));
		try{
			movieInfoEditModel.saveMovieInfo(movieInfoEditForm);
			ActionMessages msgs = new ActionMessages();
			msgs.add("infoMsg", new ActionMessage("M002"));
			saveMessages(request, msgs);
		}catch(Exception ex){
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
}
