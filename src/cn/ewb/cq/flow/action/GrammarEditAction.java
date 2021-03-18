package cn.ewb.cq.flow.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.UnitInfo;
import cn.ewb.cq.flow.form.GrammarEditForm;
import cn.ewb.cq.flow.model.GrammarEditModel;

public class GrammarEditAction  extends DispatchAction{
	
	private GrammarEditModel grammarEditModel = new GrammarEditModel();
	private static Logger logger = Logger.getLogger(GrammarEditAction.class);
	
	
	public ActionForward initGrammar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "initGrammar()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		GrammarEditForm grammarEditForm = (GrammarEditForm) form;
		try{
			String editId = request.getParameter("editId");
			String levelValue = request.getParameter("levelValue");
			String unitValue = request.getParameter("unitValue");
			grammarEditForm.setEditId(editId);
			grammarEditForm.setLevelValue(levelValue);
			grammarEditForm.setUnitValue(unitValue);
			grammarEditModel.initGrammar(grammarEditForm);	

		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	public ActionForward saveGrammar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "saveGrammar()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		GrammarEditForm grammarEditForm = (GrammarEditForm) form;
		try{
			grammarEditModel.setLoginUser(SystemUtil.getLoginUser(request));
			grammarEditModel.saveGrammar(grammarEditForm);	
			ActionMessages msgs = new ActionMessages();
			msgs.add("infoMsg", new ActionMessage("M002"));
			saveMessages(request, msgs);
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	
	public ActionForward getUnitList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "getUnitList()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		String levelId = request.getParameter("levelId");
		Session sess = HibernateUtil.getSession();
		try{

			Criteria unitSearch = sess.createCriteria(UnitInfo.class);
			unitSearch.add(Restrictions.eq("levelId", levelId));
			unitSearch.add(Restrictions.eq("delFlg","0"));	
			unitSearch.addOrder(Order.asc("orderNum"));
			List<UnitInfo> unitList = unitSearch.list();
			JSONObject jObject=new JSONObject();
			JSONArray jArray = new JSONArray();
			if(unitList!=null&&unitList.size()>0){	
				for(int i=0;i<unitList.size();i++){
					UnitInfo unitInfo = unitList.get(i);
					JSONObject unit = new JSONObject();
					unit.put("unitId", unitInfo.getId());
					unit.put("unitName", unitInfo.getUnitName());
					jArray.add(unit);
				}	
			}
			jObject.put("unitList", jArray);
			
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(jObject.toString());
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return null;
	} 
	
	
	
	
	
	
}
