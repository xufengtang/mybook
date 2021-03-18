package cn.ewb.cq.flow.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.domain.NHKInfo;
import cn.ewb.cq.flow.form.NHKInfoForm;

public class NHKInfoModel extends BaseModel{
	
	public void initNHKInfo(NHKInfoForm nhkInfoForm)throws Exception{	
		Session sess = HibernateUtil.getSession();
		String editId ="";
		String urlName ="";
		try{
			Criteria nhkInfoSearch = sess.createCriteria(NHKInfo.class);
			List<NHKInfo> nhkInfoList = nhkInfoSearch.list();
			
			if(nhkInfoList!=null&&nhkInfoList.size()>0){
				NHKInfo nhkInfo = nhkInfoList.get(0);
				 editId  = nhkInfo.getId();
				 urlName = nhkInfo.getUrlName();
			}
			nhkInfoForm.setEditId(editId);
			nhkInfoForm.setUrlName(urlName);
		}catch(Exception ex){
			throw ex;
		}finally{
			HibernateUtil.closeSession();		
		}         
	}
	
	
	public void saveUrl(NHKInfoForm nhkInfoForm)throws Exception{	
		Session sess = HibernateUtil.getSession();
		String editId = nhkInfoForm.getEditId();
		String urlName = nhkInfoForm.getUrlName();
		try{
			
			Transaction tx = sess.beginTransaction();
			
			NHKInfo nhkInfo = (NHKInfo)sess.get(NHKInfo.class, editId);
			nhkInfo.setUrlName(urlName);
			nhkInfo.setUpdateId(getLoginUser());
			nhkInfo.setUrlName(urlName);
			
			sess.update(nhkInfo);
			tx.commit();
			
		}catch(Exception ex){
			throw ex;
		}finally{
			HibernateUtil.closeSession();		
		}         
	}

}
