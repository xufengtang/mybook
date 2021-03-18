package cn.ewb.cq.flow.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.UnitInfo;
import cn.ewb.cq.flow.form.UnitEditForm;

public class UnitEditModel extends BaseModel{
	
	
	public void initUnit(UnitEditForm unitEditForm)throws Exception{
		Session sess = HibernateUtil.getSession();
		try{
			String  editId = unitEditForm.getEditId();
			UnitInfo unitInfo = (UnitInfo)sess.get(UnitInfo.class, editId);
			unitEditForm.setLevelName(unitInfo.getLevelId());
			unitEditForm.setUnitName(unitInfo.getUnitName());
			unitEditForm.setOrderNum(String.valueOf(unitInfo.getOrderNum()));
		}catch(Exception ex){
			throw ex;
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	public void saveUnit(UnitEditForm unitEditForm)throws Exception{
		
		Session sess = HibernateUtil.getSession();
		try{
			String  editId = unitEditForm.getEditId();
			String levelName = unitEditForm.getLevelName();
			String unitName = unitEditForm.getUnitName();
			String orderNum =unitEditForm.getOrderNum();
			Transaction tx =sess.beginTransaction();
			// 编辑情况
			if(StringUtil.isNotNull(editId)){
				UnitInfo unitInfo = (UnitInfo)sess.get(UnitInfo.class, editId);
				unitInfo.setLevelId(levelName);
				unitInfo.setUnitName(unitName);
				unitInfo.setOrderNum(Integer.parseInt(orderNum));
				unitInfo.setUpdateId(getLoginUser());
				unitInfo.setUpdateTime(SystemUtil.getSystemTime());
				sess.update(unitInfo);
			//新增情况
			}else{
				UnitInfo unitInfo = new UnitInfo();
				unitInfo.setLevelId(levelName);
				unitInfo.setUnitName(unitName);
				unitInfo.setOrderNum(Integer.parseInt(orderNum));
				unitInfo.setCreateId(getLoginUser());
				unitInfo.setCreateTime(SystemUtil.getSystemTime());
				unitInfo.setUpdateId(getLoginUser());
				unitInfo.setUpdateTime(SystemUtil.getSystemTime());
				unitInfo.setDelFlg("0");
				sess.save(unitInfo);
			}
			tx.commit();
		}catch(Exception ex){
			throw ex;
		}finally{
			HibernateUtil.closeSession();
			
		}
		
	}

}
