package cn.ewb.cq.flow.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SysConstant;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.Grammar;
import cn.ewb.cq.domain.UnitInfo;
import cn.ewb.cq.flow.bean.GrammarBean;
import cn.ewb.cq.flow.form.GrammarSearchForm;


public class GrammarSearchModel extends BaseModel{
	
	public void initSearchGrammar(GrammarSearchForm grammarSearchForm)throws Exception{
		
		Session sess = HibernateUtil.getSession();
		try{
			
			grammarSearchForm.setLevelValueSearch("1");
			
			Criteria unitSearch = sess.createCriteria(UnitInfo.class);
			unitSearch.add(Restrictions.eq("levelId", grammarSearchForm.getLevelValueSearch()));
			unitSearch.add(Restrictions.eq("delFlg","0"));	
			unitSearch.addOrder(Order.asc("orderNum"));
			List<UnitInfo> unitList = unitSearch.list();
			grammarSearchForm.setUnitList(unitList);
			grammarSearchForm.setUnitValueSearch("");
			
		}catch(Exception ex){
			throw ex;
		}finally{
			HibernateUtil.closeSession();
			
		}
	}
	
	
	public List<GrammarBean> grammarSearch(Session sess,GrammarSearchForm grammarSearchForm)throws Exception{
		List<GrammarBean> grammarList = new ArrayList<GrammarBean>();
		String levelValue = grammarSearchForm.getLevelValueSearch();
		String unitValue = grammarSearchForm.getUnitValueSearch();
		String grammarTitle = grammarSearchForm.getGrammarTitleSearch();
		
		
		String hql = "from Grammar GA,UnitInfo UI where GA.unitInfo = UI.id and GA.delFlg='0' ";
		
		try{
			
			if(StringUtil.isNotNull(levelValue)){
				hql = hql + " and UI.levelId = :levelValue";
			}
			
			if(StringUtil.isNotNull(unitValue)){
				
				hql = hql + " and UI.id = :unitValue";
			}
			if(StringUtil.isNotNull(grammarTitle)){
				hql = hql + " and GA.titleName like :grammarTitle";
				
			}
			
			hql = hql + " order by GA.orderNum " ;
			
			
			
			// 页数计算
			Query q = sess.createQuery(hql);
			if(StringUtil.isNotNull(levelValue)){
				q.setParameter("levelValue", levelValue);
			}
			if(StringUtil.isNotNull(unitValue)){
				q.setParameter("unitValue",unitValue);
			}
			
			if(StringUtil.isNotNull(grammarTitle)){
				q.setParameter("grammarTitle",  "%"+ grammarTitle +"%");
			}
			
			
			q.setFirstResult((grammarSearchForm.getCurrentPage()-1)* SysConstant.PAGE);
			q.setMaxResults(SysConstant.PAGE);
			
			List grammarResultList =  q.list();
			
			if(grammarResultList!=null&&grammarResultList.size()>0){
				for(int i=0;i<grammarResultList.size();i++){
					Object[] object =  (Object[])grammarResultList.get(i);
					Grammar grammar =  (Grammar)object[0];
					GrammarBean grammarBean = new GrammarBean();
					grammarBean.setId(grammar.getId());
					grammarBean.setLevelName(grammar.getUnitInfo().getLevelId());
					grammarBean.setUnitName(grammar.getUnitInfo().getUnitName());
					grammarBean.setGrammarTitle(grammar.getTitleName() );
					grammarBean.setOrderNum(String.valueOf(grammar.getOrderNum()));
					grammarList.add(grammarBean);
				}
			}
			
			
			// 读取单元下拉列表
			Criteria unitSearch = sess.createCriteria(UnitInfo.class);
			unitSearch.add(Restrictions.eq("levelId", grammarSearchForm.getLevelValueSearch()));
			unitSearch.add(Restrictions.eq("delFlg","0"));	
			unitSearch.addOrder(Order.asc("orderNum"));
			List<UnitInfo> unitList = unitSearch.list();
			grammarSearchForm.setUnitList(unitList);
			
		    return grammarList;
		}catch(Exception ex){
			throw ex;
		}
	
	}
	
	
	 public int selectTotalPage(Session sess,GrammarSearchForm grammarSearchForm) throws Exception {
		 String levelValue = grammarSearchForm.getLevelValueSearch();
		 String unitValue = grammarSearchForm.getUnitValueSearch();
		 String grammarTitle = grammarSearchForm.getGrammarTitleSearch();	 
		 try{
			 String hql = " select count(GA.id) from Grammar GA,UnitInfo UI where GA.unitInfo = UI.id";
			 
				if(StringUtil.isNotNull(levelValue)){
					hql = hql + " and UI.levelId = :levelValue";
				}
				
				if(StringUtil.isNotNull(unitValue)){
					
					hql = hql + " and UI.id = :unitValue";
				}
				if(StringUtil.isNotNull(grammarTitle)){
					hql = hql + " and GA.titleName like :grammarTitle";
					
				}	
				
				// 页数计算
				Query q = sess.createQuery(hql);
				if(StringUtil.isNotNull(levelValue)){
					q.setParameter("levelValue", levelValue);
				}
				if(StringUtil.isNotNull(unitValue)){
					q.setParameter("unitValue",unitValue);
				}
				
				if(StringUtil.isNotNull(grammarTitle)){
					q.setParameter("grammarTitle",  "%"+ grammarTitle +"%");
				}
				 
			 
				int totalNum = Integer.parseInt(q.iterate().next().toString());
				int totalPage = totalNum%SysConstant.PAGE==0?totalNum/SysConstant.PAGE:totalNum/SysConstant.PAGE+1;
				return totalPage;
			
		 }catch(Exception ex){
			throw ex;
		 }
	 }
	 
	 
	 public void deleteGrammar(Session sess,GrammarSearchForm grammarSearchForm)throws Exception{		 
		 String editid = grammarSearchForm.getEditId();
		 Transaction tx = sess.beginTransaction();
		 try{
			 Grammar grammar = (Grammar)sess.get(Grammar.class, editid);
			 grammar.setDelFlg("1");
			 grammar.setUpdateId(getLoginUser());
			 grammar.setUpdateTime(SystemUtil.getSystemTime());
			 sess.update(grammar);
			 tx.commit();
		 }catch(Exception ex){
			 throw ex;
		 }

	 }
	 
}