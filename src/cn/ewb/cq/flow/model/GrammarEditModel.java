package cn.ewb.cq.flow.model;

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
import cn.ewb.cq.domain.Grammar;
import cn.ewb.cq.domain.UnitInfo;
import cn.ewb.cq.flow.form.GrammarEditForm;

public class GrammarEditModel extends BaseModel{
	
	
	public void initGrammar(GrammarEditForm grammarEditForm)throws Exception{
		
		String editId = grammarEditForm.getEditId();
		Session sess = HibernateUtil.getSession();
		try{
			if(StringUtil.isNotNull(editId)){
				Grammar grammar = (Grammar)sess.get(Grammar.class, editId);
				grammarEditForm.setLevelValue(grammar.getUnitInfo().getLevelId());
				grammarEditForm.setUnitValue(grammar.getUnitInfo().getId());
				grammarEditForm.setTitleName(grammar.getTitleName());
				grammarEditForm.setFollowValue(grammar.getFollowValue());
				grammarEditForm.setExplainValue(grammar.getExplainValue());
				grammarEditForm.setExample1(grammar.getExample1());
				grammarEditForm.setExample2(grammar.getExample2());
				grammarEditForm.setExample3(grammar.getExample3());
				grammarEditForm.setExample4(grammar.getExample4());
				grammarEditForm.setExample5(grammar.getExample5());
				grammarEditForm.setExample6(grammar.getExample6());
				grammarEditForm.setContent(grammar.getContent());
				grammarEditForm.setOrderNum(String.valueOf(grammar.getOrderNum()));
			}
			//else{			
				//grammarEditForm.setLevelValue("1");
				
			//}
			
			Criteria unitSearch = sess.createCriteria(UnitInfo.class);
			unitSearch.add(Restrictions.eq("levelId", grammarEditForm.getLevelValue()));
			unitSearch.add(Restrictions.eq("delFlg","0"));	
			unitSearch.addOrder(Order.asc("orderNum"));
			List<UnitInfo> unitList = unitSearch.list();
			grammarEditForm.setUnitList(unitList);
//			if(!StringUtil.isNotNull(editId)){
//				grammarEditForm.setUnitValue(unitList.get(0).getId());
//			}
			
		}catch(Exception ex){	
		   throw ex;		
		}finally{
			HibernateUtil.closeSession();	
		}	
		
	}
	
	
	public void saveGrammar(GrammarEditForm grammarEditForm)throws Exception{
		Session sess = HibernateUtil.getSession();
		Transaction tx = sess.beginTransaction();
		try{
		   String editId = grammarEditForm.getEditId();
		   //String levelValue  = grammarEditForm.getLevelValue();
		   String unitValue = grammarEditForm.getUnitValue();
		   String titleName  = grammarEditForm.getTitleName();
		   String orderNum= grammarEditForm.getOrderNum();
		   String followValue =grammarEditForm.getFollowValue();
		   String explainValue = grammarEditForm.getExplainValue();
		   String example1 = grammarEditForm.getExample1();
		   String example2 =grammarEditForm.getExample2();
		   String example3 =grammarEditForm.getExample3();
		   String example4 =grammarEditForm.getExample4();
		   String example5 =grammarEditForm.getExample5();
		   String example6 =grammarEditForm.getExample6();
		   String content =grammarEditForm.getContent();
		   
		   UnitInfo unitInfo = (UnitInfo)sess.get(UnitInfo.class, unitValue);
		  // grammarEditForm.setLevelValue(unitInfo.getLevelId());
		   //更新情况
		   if(StringUtil.isNotNull(editId)){
			 Grammar grammar = (Grammar)sess.get(Grammar.class, editId);
			 grammar.setUnitInfo(unitInfo);
			 grammar.setTitleName(titleName);
			 grammar.setOrderNum(Integer.parseInt(orderNum)); 
			 grammar.setFollowValue(followValue);
			 grammar.setExplainValue(explainValue);
			 grammar.setExample1(example1);
			 grammar.setExample2(example2);
			 grammar.setExample3(example3);
			 grammar.setExample4(example4);
			 grammar.setExample5(example5);
			 grammar.setExample6(example6);
			 grammar.setContent(content);
			 grammar.setUpdateId(getLoginUser()) ;
			 grammar.setUpdateTime(SystemUtil.getSystemTime());  
			 sess.update(grammar);
		   //新增情况	   
		   }else{
			 Grammar grammar = new Grammar();
			 grammar.setUnitInfo(unitInfo);
			 grammar.setTitleName(titleName);
			 grammar.setOrderNum(Integer.parseInt(orderNum)); 
			 grammar.setFollowValue(followValue);
			 grammar.setExplainValue(explainValue);
			 grammar.setExample1(example1);
			 grammar.setExample2(example2);
			 grammar.setExample3(example3);
			 grammar.setExample4(example4);
			 grammar.setExample5(example5);
			 grammar.setExample6(example6);
			 grammar.setContent(content);
			 grammar.setDelFlg("0");
			 grammar.setCreateId(getLoginUser());
			 grammar.setCreateTime(SystemUtil.getSystemTime());
			 grammar.setUpdateId(getLoginUser()) ;
			 grammar.setUpdateTime(SystemUtil.getSystemTime());
			 sess.save(grammar);
		   }
		   
		   Criteria unitSearch = sess.createCriteria(UnitInfo.class);
		   unitSearch.add(Restrictions.eq("levelId", grammarEditForm.getLevelValue()));
		   unitSearch.add(Restrictions.eq("delFlg","0"));	
		   unitSearch.addOrder(Order.asc("orderNum"));
		   List<UnitInfo> unitList = unitSearch.list();
		   grammarEditForm.setUnitList(unitList);
		   tx.commit();
		}catch(Exception ex){	
		   throw ex;		
		}finally{
			HibernateUtil.closeSession();	
		}	
	}
	
}
