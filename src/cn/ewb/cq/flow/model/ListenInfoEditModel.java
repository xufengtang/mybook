package cn.ewb.cq.flow.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.ListenInfo;
import cn.ewb.cq.domain.PubDict;
import cn.ewb.cq.flow.form.ListenInfoEditForm;

public class ListenInfoEditModel extends BaseModel{
	
	
	
		public void listenInfoEditInit(ListenInfoEditForm listenInfoEditForm)throws Exception{
			String editId = listenInfoEditForm.getEditId();
			Session sess = HibernateUtil.getSession();
			try{
				if(StringUtil.isNotNull(editId)){	
					ListenInfo listenInfo  = (ListenInfo)sess.get(ListenInfo.class, editId);
					listenInfoEditForm.setLevelId(listenInfo.getLevelId());
					listenInfoEditForm.setListenName(listenInfo.getListenName());
					listenInfoEditForm.setListenUrl(listenInfo.getListenUrl());
					listenInfoEditForm.setOrderNum(String.valueOf(listenInfo.getOrderNum()));					
				}
				getLevelList(sess,listenInfoEditForm);
			}catch(Exception ex){
				throw ex;
			}finally{
				HibernateUtil.closeSession();
			}

		}
		
		public void saveListenInfo(ListenInfoEditForm listenInfoEditForm)throws Exception{
			String editId = listenInfoEditForm.getEditId();
			String levelId = listenInfoEditForm.getLevelId();
			String listenName = listenInfoEditForm.getListenName();
			String orderNum = listenInfoEditForm.getOrderNum();
			String listenUrl = listenInfoEditForm.getListenUrl();
			Session sess = HibernateUtil.getSession();
			try{
				Transaction tx = sess.beginTransaction();
				if(StringUtil.isNotNull(editId)){	
					ListenInfo listenInfo  = (ListenInfo)sess.get(ListenInfo.class, editId);
					listenInfo.setLevelId(levelId);
					listenInfo.setListenName(listenName);
					listenInfo.setOrderNum(Integer.parseInt(orderNum));
					listenInfo.setListenUrl(listenUrl);
					listenInfo.setUpdateId(getLoginUser());
					listenInfo.setUpdateTime(SystemUtil.getSystemTime());
					
					sess.update(listenInfo);
				}else{
					ListenInfo listenInfo = new ListenInfo();
					
					listenInfo.setLevelId(levelId);
					listenInfo.setListenName(listenName);
					listenInfo.setOrderNum(Integer.parseInt(orderNum));
					listenInfo.setListenUrl(listenUrl);
					listenInfo.setCreateId(getLoginUser());
					listenInfo.setCreateTime(SystemUtil.getSystemTime());
					listenInfo.setUpdateId(getLoginUser());
					listenInfo.setUpdateTime(SystemUtil.getSystemTime());
					listenInfo.setDelFlg("0");
					
					sess.save(listenInfo);
				}
				getLevelList(sess,listenInfoEditForm);
				tx.commit();
			}catch(Exception ex){
				throw ex;
			}finally{
				HibernateUtil.closeSession();
			}

		}
		
		
		private void getLevelList(Session sess,ListenInfoEditForm listenInfoEditFor)throws Exception{
			List<PubDict> levelList = getPubDict(sess, "LISTEN");
			listenInfoEditFor.setLevelList(levelList);
		}
}
