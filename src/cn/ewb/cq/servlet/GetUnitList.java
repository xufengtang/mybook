package cn.ewb.cq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.UnitInfo;

public class GetUnitList  extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(GetUnitList.class);
	@Override
	protected synchronized  void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "GetUnitList()";
		String levelId =  req.getParameter("levelIdParam");
		Session sess = HibernateUtil.getSession();
		
		try{		
			Criteria unitSearch = sess.createCriteria(UnitInfo.class);
			unitSearch.add(Restrictions.eq("levelId", levelId));
			unitSearch.add(Restrictions.ne("delFlg", "1"));
			unitSearch.addOrder(Order.asc("orderNum"));
			List<UnitInfo> unitInfoList = unitSearch.list();
			JSONObject jObject=new JSONObject();
			JSONArray jArray = new JSONArray();
			
			if(unitInfoList!=null && unitInfoList.size()>0){
				for(int i=0;i<unitInfoList.size();i++){
					UnitInfo  unitInfo  = unitInfoList.get(i);
					JSONObject children = new JSONObject();
					children.put("unitId", unitInfo.getId());
					children.put("unitName", unitInfo.getUnitName());
					jArray.add(children);
				}
			}
			
			jObject.put("unitInfo", jArray);
			//resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.println(jObject.toString());
			
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(req,this.getClass().getName(),methodName,ex.getMessage()));
		}finally{
			HibernateUtil.closeSession();
			
		}

	}

	@Override
	protected synchronized  void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String methodName = "GetUnitList()";
		String levelId =  req.getParameter("levelIdParam");
		Session sess = HibernateUtil.getSession();
		
		try{		
			Criteria unitSearch = sess.createCriteria(UnitInfo.class);
			unitSearch.add(Restrictions.eq("levelId", levelId));
			unitSearch.add(Restrictions.ne("delFlg", "1"));
			unitSearch.addOrder(Order.asc("orderNum"));
			List<UnitInfo> unitInfoList = unitSearch.list();
			JSONObject jObject=new JSONObject();
			JSONArray jArray = new JSONArray();
			
			if(unitInfoList!=null && unitInfoList.size()>0){
				for(int i=0;i<unitInfoList.size();i++){
					UnitInfo  unitInfo  = unitInfoList.get(i);
					JSONObject children = new JSONObject();
					children.put("unitId", unitInfo.getId());
					children.put("unitName", unitInfo.getUnitName());
					jArray.add(children);
				}
			}
			
			jObject.put("unitInfo", jArray);
			//resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.println(jObject.toString());
			
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(req,this.getClass().getName(),methodName,ex.getMessage()));
		}finally{
			HibernateUtil.closeSession();
			
		}

	}
	
	

}
