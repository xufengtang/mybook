package cn.ewb.cq.flow.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.UnitInfo;
import cn.ewb.cq.flow.bean.UnitBean;
import cn.ewb.cq.flow.form.UnitEditForm;
import cn.ewb.cq.flow.form.UnitSearchForm;

public class UnitSearchModel extends BaseModel {

	public List<UnitBean>searchUnit(Session sess,UnitSearchForm unitSearchForm)throws Exception{
		List <UnitBean> unitBeanList = new ArrayList<UnitBean>();
		try{
			String levelName = unitSearchForm.getLevelNameSearch();
			Criteria UnitInfoSearch = sess.createCriteria(UnitInfo.class);
			UnitInfoSearch.add(Restrictions.eq("levelId", levelName));
			UnitInfoSearch.add(Restrictions.eq("delFlg", "0"));
			UnitInfoSearch.addOrder(Order.asc("orderNum"));
			List<UnitInfo> unitInfoList  = UnitInfoSearch.list();
						
			if(unitInfoList!=null&&unitInfoList.size()>0){
				for(int i=0;i<unitInfoList.size();i++){
					UnitInfo unitInfo =	unitInfoList.get(i);
					UnitBean unitBean = new UnitBean();
					if("1".equals(unitInfo.getLevelId())){
						unitBean.setLevelName("日语一级");
					}else if ("2".equals(unitInfo.getLevelId())){
						unitBean.setLevelName("日语二级");
					}else if ("3".equals(unitInfo.getLevelId())){
						unitBean.setLevelName("日语三级");
					}else if ("4".equals(unitInfo.getLevelId())){
						unitBean.setLevelName("日语四级");
					}else if ("5".equals(unitInfo.getLevelId())){
						unitBean.setLevelName("日语五级");
					}
					unitBean.setUnitName(unitInfo.getUnitName());
					unitBean.setOrderNum(String.valueOf(unitInfo.getOrderNum()));
					unitBean.setId(unitInfo.getId());
					unitBeanList.add(unitBean);
				}
			}
		}catch(Exception ex){
			throw ex;
		}
		
		return unitBeanList;
		
	}
	
	
	public void deleteUnit(String editId,UnitSearchForm unitEditForm)throws Exception{
		Session sess = HibernateUtil.getSession();
		try{

			Transaction tx =sess.beginTransaction();
			// 编辑情况
			if(StringUtil.isNotNull(editId)){
				UnitInfo unitInfo = (UnitInfo)sess.get(UnitInfo.class, editId);
				unitInfo.setDelFlg("1");
				unitInfo.setUpdateId(getLoginUser());
				unitInfo.setUpdateTime(SystemUtil.getSystemTime());
				sess.update(unitInfo);
			}
			tx.commit();
		}catch(Exception ex){
			throw ex;
		}finally{
			HibernateUtil.closeSession();
			
		}
		
	}
}
