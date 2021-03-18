package cn.ewb.cq.flow.model;

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
import cn.ewb.cq.domain.ListenInfo;
import cn.ewb.cq.domain.PubDict;
import cn.ewb.cq.flow.form.ListenInfoSearchForm;

public class ListenInfoSearchModel extends BaseModel {
	
	public void searchListenInfoInit(ListenInfoSearchForm listenInfoSearchForm)throws Exception{
		Session sess = HibernateUtil.getSession();
		try{
			getLevelList(sess,listenInfoSearchForm);
		}catch(Exception ex){
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
	
	}
	
	
	public List<ListenInfo> searchListenInfo(Session sess,ListenInfoSearchForm listenInfoSearchForm)throws Exception{
		String levelId = listenInfoSearchForm.getLevelIdSearch();
		try{
			Criteria listenInfoSearch = sess.createCriteria(ListenInfo.class);
			listenInfoSearch.add(Restrictions.eq("levelId", levelId));
			listenInfoSearch.add(Restrictions.eq("delFlg", "0"));
			listenInfoSearch.addOrder(Order.asc("orderNum"));
			listenInfoSearch.setFirstResult((listenInfoSearchForm.getCurrentPage()-1)* SysConstant.PAGE);
			listenInfoSearch.setMaxResults(SysConstant.PAGE);		
			List<ListenInfo> listenInfoList = listenInfoSearch.list();
			
			return listenInfoList;
		}catch(Exception ex){
			throw ex;
		}
	}
	
	
	public int selectTotalPage(Session sess,ListenInfoSearchForm listenInfoSearchForm)throws Exception{
		String levelId = listenInfoSearchForm.getLevelIdSearch();
		String hql = " select count(id) from ListenInfo  where levelId= :levelId and delFlg= '0' ";
		try{
			
			
			
			// Ò³Êý¼ÆËã
			Query q = sess.createQuery(hql);
			if(StringUtil.isNotNull(levelId)){
				q.setParameter("levelId", levelId);
			}
			
			
			int totalNum = Integer.parseInt(q.iterate().next().toString());
			int totalPage = totalNum%SysConstant.PAGE==0?totalNum/SysConstant.PAGE:totalNum/SysConstant.PAGE+1;
			return totalPage;
			
		}catch(Exception ex){
			throw ex;
		}
	}
	
	
	public void listenInfoDel(String editId,ListenInfoSearchForm listenInfoSearchForm)throws Exception{
		
		Session sess = HibernateUtil.getSession();
		try{		
			Transaction tx = sess.beginTransaction();	
			if(StringUtil.isNotNull(editId)){			
				ListenInfo listenInfo  = (ListenInfo)sess.get(ListenInfo.class, editId);
				listenInfo.setDelFlg("1");
				listenInfo.setUpdateId(getLoginUser());
				listenInfo.setUpdateTime(SystemUtil.getSystemTime());
				sess.update(listenInfo);
			}
			listenInfoSearchForm.setListenInfoList(searchListenInfo(sess,listenInfoSearchForm));
			getLevelList(sess,listenInfoSearchForm);
			tx.commit();
		}catch(Exception ex){
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}

	}
	

	private void getLevelList(Session sess,ListenInfoSearchForm listenInfoSearchForm)throws Exception{
		List<PubDict> levelList = getPubDict(sess, "LISTEN");
		
		listenInfoSearchForm.setLevelList(levelList);
	}
}
