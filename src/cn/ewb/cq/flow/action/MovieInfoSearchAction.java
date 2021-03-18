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
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.MovieInfo;
import cn.ewb.cq.flow.form.MovieInfoSearchForm;
import cn.ewb.cq.flow.model.MovieInfoSearchModel;

public class MovieInfoSearchAction extends DispatchAction{
	
	private MovieInfoSearchModel movieInfoSearchModel = new MovieInfoSearchModel();
	private static Logger logger = Logger.getLogger(MovieInfoSearchAction.class);
	
	
	public ActionForward searchMovieInfoInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "searchMovieInfoInit()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
	
		MovieInfoSearchForm movieInfoSearchForm = (MovieInfoSearchForm) form;
		try{
			movieInfoSearchModel.searchMovieInfoInit(movieInfoSearchForm);

		}catch(Exception ex){
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	public ActionForward searchMovieInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "searchMovieInfo()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		
		Session sess =HibernateUtil.getSession();
		try{
			MovieInfoSearchForm movieInfoSearchForm = (MovieInfoSearchForm) form;
			List<MovieInfo> movieInfoList = movieInfoSearchModel.searchMovieInfo(sess,movieInfoSearchForm);
			int totalPage =movieInfoSearchModel.selectTotalPage(sess,movieInfoSearchForm);
			movieInfoSearchModel.getMovieType(sess, movieInfoSearchForm);
			movieInfoSearchForm.setMovieInfoList(movieInfoList);
			movieInfoSearchForm.setTotalPage(totalPage);
			if(movieInfoList==null||movieInfoList.size()<1){
				ActionMessages msgs = new ActionMessages();
				msgs.add("showMsg", new ActionMessage("M001"));
				saveMessages(request, msgs);
			}
			
			movieInfoSearchModel.searchMovieInfoInit(movieInfoSearchForm);
			
		}catch(Exception ex){
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	
	
	public ActionForward movieInfoDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "movieInfoDel()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
	
		String editId = request.getParameter("editId");
		MovieInfoSearchForm movieInfoSearchForm = (MovieInfoSearchForm) form;
		movieInfoSearchModel.setLoginUser(SystemUtil.getLoginUser(request));
		Session sess = HibernateUtil.getSession();
		try{
			movieInfoSearchModel.movieInfoDel(editId,movieInfoSearchForm,sess);
			List<MovieInfo> movieInfoList = movieInfoSearchModel.searchMovieInfo(sess,movieInfoSearchForm);
			int totalPage =movieInfoSearchModel.selectTotalPage(sess,movieInfoSearchForm);
			movieInfoSearchForm.setMovieInfoList(movieInfoList);
			movieInfoSearchForm.setTotalPage(totalPage);
			movieInfoSearchModel.getMovieType(sess, movieInfoSearchForm);
			
			ActionMessages msgs = new ActionMessages();
			msgs.add("infoMsg", new ActionMessage("M004",new String[]{"¼ÇÂ¼"}));
			saveMessages(request, msgs);
		}catch(Exception ex){
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
}
