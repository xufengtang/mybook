package cn.ewb.cq.flow.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.ewb.cq.com.BaseModel;
import cn.ewb.cq.com.HibernateUtil;
import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SysConstant;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.domain.LisenceInfo;
import cn.ewb.cq.flow.bean.LisenceInfoBean;
import cn.ewb.cq.flow.form.LisenceCreateForm;





public class LisenceCreateModel extends BaseModel{
	
	public List<LisenceInfoBean> lisenceCodeSearch(Session sess,LisenceCreateForm  lisenceCreateForm)throws Exception{
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String status = lisenceCreateForm.getUserStatusSearch();
		String openId = lisenceCreateForm.getOpenIdSearch();
		
		Criteria  lisenceInfoSearch = sess.createCriteria(LisenceInfo.class);
		
		if(StringUtil.isNotNull(openId)){
			lisenceInfoSearch.add(Restrictions.eq("openId", openId));
		}
		// 已使用
		if("2".equals(status)){
			lisenceInfoSearch.add(Restrictions.isNotNull("useDate"));
		}
		
		lisenceInfoSearch.addOrder(Order.desc("createTime"));
		
		
		lisenceInfoSearch.setFirstResult((lisenceCreateForm.getCurrentPage()-1)* SysConstant.PAGE);
		lisenceInfoSearch.setMaxResults(SysConstant.PAGE);
		
		
		List<LisenceInfo> lisenceInfoList = lisenceInfoSearch.list();
		List<LisenceInfoBean> lisenceInfoBeanList = new ArrayList<LisenceInfoBean>();
		
		if(lisenceInfoList!=null&&lisenceInfoList.size()>0){
			for(int i=0;i<lisenceInfoList.size();i++){
				LisenceInfo lisenceInfo = lisenceInfoList.get(i);
				LisenceInfoBean lisenceInfoBean = new LisenceInfoBean();
				lisenceInfoBean.setLisenceCode(lisenceInfo.getLisenceCode());
				lisenceInfoBean.setOpenId(lisenceInfo.getOpenId());
				if(lisenceInfo.getUseDate()!=null){
					lisenceInfoBean.setUseDate(df.format(lisenceInfo.getUseDate()));
				}
				lisenceInfoBean.setCreateDate(df.format(lisenceInfo.getCreateTime()));
				lisenceInfoBeanList.add(lisenceInfoBean);
			}
		}
		
		return  lisenceInfoBeanList ;
		
		//lisenceCreateForm.setLisenceInfoList(lisenceInfoBeanList);
		
	}
	
	
	public int selectTotalPage(Session sess,LisenceCreateForm  lisenceCreateForm)throws Exception{
		
		String status = lisenceCreateForm.getUserStatusSearch();
		String openId = lisenceCreateForm.getOpenIdSearch();
		
		String hql = " select count(LI.id) from LisenceInfo LI where 1=1 ";
		
		
		if(StringUtil.isNotNull(openId)){
			hql = hql + " and  openId= :openId";	
		}
		
		// 已使用
		if("2".equals(status)){
			hql = hql + " and useDate is not null";	
		}
		
		// 页数计算
		Query q = sess.createQuery(hql);
		
		if(StringUtil.isNotNull(openId)){
			q.setParameter("openId", openId);
		}
		
		 
		int totalNum = Integer.parseInt(q.iterate().next().toString());
		int totalPage = totalNum%SysConstant.PAGE==0?totalNum/SysConstant.PAGE:totalNum/SysConstant.PAGE+1;
		return totalPage;

		
	}
	
	
	
	
	
	public void lisenceCreate( LisenceCreateForm  lisenceCreateForm)throws Exception{
		List<String> lisenceCodeList = new ArrayList<String>();
		List<String> lisenceCodeCopyList = new ArrayList<String>();
		initLisenceCode(lisenceCodeList);
		Session sess = HibernateUtil.getSession();
		Random random = new Random();
		StringBuffer lisenceCode = new StringBuffer();
		try{
			for(int i=0;i<lisenceCodeList.size();i++){
				int result = random.nextInt(lisenceCodeList.size());
				lisenceCodeCopyList.add(new String(lisenceCodeList.get(result)));
				lisenceCodeList.remove(result);
				i--;
			}
			for(int i=0;i<12;i++){
				int result = random.nextInt(lisenceCodeCopyList.size());
				lisenceCode.append(lisenceCodeCopyList.get(result));
				lisenceCodeCopyList.remove(result);
				if(i==3||i==7){
					lisenceCode.append("-");
				}
			}
			
			LisenceInfo lisenceInfo = new LisenceInfo();
			lisenceInfo.setLisenceCode(lisenceCode.toString());
			lisenceInfo.setCreateId(getLoginUser());
			lisenceInfo.setCreateTime(SystemUtil.getSystemTime());
			lisenceInfo.setUpdateId(getLoginUser());
			lisenceInfo.setUpdateTime(SystemUtil.getSystemTime());
			add(sess,lisenceInfo);
			lisenceCreateForm.setLisenceCode(lisenceInfo.getLisenceCode());
			lisenceCreateForm.setSuccessFlg("success");
		}catch(Exception ex){	
			throw ex;
		}finally{
			
			HibernateUtil.closeSession();
		}	
	}
	
	
	
	
	
	private void initLisenceCode(List<String>lisenceCodeList){
		lisenceCodeList.add("A");
		lisenceCodeList.add("B");
		lisenceCodeList.add("C");
		lisenceCodeList.add("D");
		lisenceCodeList.add("E");
		lisenceCodeList.add("F");
		lisenceCodeList.add("G");
		lisenceCodeList.add("H");
		lisenceCodeList.add("I");
		lisenceCodeList.add("J");
		lisenceCodeList.add("K");
		lisenceCodeList.add("L");
		lisenceCodeList.add("M");
		lisenceCodeList.add("N");
		lisenceCodeList.add("O");
		lisenceCodeList.add("P");
		lisenceCodeList.add("Q");
		lisenceCodeList.add("R");
		lisenceCodeList.add("S");
		lisenceCodeList.add("T");
		lisenceCodeList.add("U");
		lisenceCodeList.add("V");
		lisenceCodeList.add("W");
		lisenceCodeList.add("X");
		lisenceCodeList.add("Y");
		lisenceCodeList.add("Z");
	}
}
