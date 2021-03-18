package cn.ewb.cq.com;

public class SysConstant {
	// 显示条数
	public static final int PAGE = 15;
	public static final int PAGE2 = 10;
	// 工作模块CODE
	public static final String WORK_FLOW_CODE_1 = "1";
	public static final String WORK_FLOW_CODE_2 = "2";
	public static final String WORK_FLOW_CODE_3 = "3";
	public static final String WORK_FLOW_CODE_4 = "4";
	public static final String WORK_FLOW_CODE_5 = "5";
	public static final String WORK_FLOW_CODE_8 = "8";
	public static final String WORK_FLOW_CODE_9 = "9";
	
	// 工作模块页面
	public static final String WORK_PAGE_NAME = "workEdit.jsp";
	public static final String WORK_PROGRESS_NAME = "workShow.jsp";
	// 意见模块页面
	public static final String FEED_PAGE_NAME = "feedEdit.jsp";
	public static final String FEED_PROGRESS_NAME = "feedShow.jsp";
	// 授信模块页面
	public static final String CREDIT_PAGE_NAME = "creditProjectEdit.jsp";
	public static final String CREDIT_PROGRESS_NAME = "creditProjectShow.jsp";
	// 内部账户申请模块页面
	public static final String INTERNAL_ACCOUNT_PAGE_NAME = "internalAccountApplyEdit.jsp";
	public static final String INTERNAL_ACCOUNT_PROGRESS_NAME = "internalAccountApplyShow.jsp";
	// 大额资金交易申请模块页面
	public static final String LARGE_MONEY_PAGE_NAME = "largeMoneyApplyEdit.jsp";
	public static final String LARGE_MONEY_PROGRESS_NAME = "largeMoneyApplyShow.jsp";
	// 营业机构考核模块页面
	public static final String BUSS_DEPART_CHECK_PAGE_NAME = "bussDepartCheckEdit.jsp";
	public static final String BUSS_DEPART_CHECK_PROGRESS_NAME = "bussDepartCheckShow.jsp";
	// 流程银行线下审批模块页面
	public static final String BANK_APPROVAL_PAGE_NAME = "bankApprovalEdit.jsp";
	public static final String BANK_APPROVAL_PROGRESS_NAME = "bankApprovalShow.jsp";
	
	// 权限控制用
	public static final String WORK_FLOW_REPORT_3 = "授信项目查询";
	public static final String WORK_FLOW_REPORT_4 = "经营机构授信项目统计表";
	public static final String WORK_FLOW_REPORT_5 = "经营机构授信时效统计表";
	public static final String WORK_FLOW_REPORT_6 = "经营机构授信项目未完统计表";
	public static final String WORK_FLOW_REPORT_7 = "授信项目时效查询";
	public static final String WORK_FLOW_REPORT_8 = "内部账户审批查询打印";
	public static final String WORK_FLOW_REPORT_9 = "大额交易审批查询打印";
	public static final String WORK_FLOW_REPORT_10 = "评估项目抽签";
	public static final String WORK_FLOW_REPORT_11 = "营业机构考核报表";
	public static final String WORK_FLOW_REPORT_12 = "营业室经理考核报表";
	public static final String WORK_FLOW_REPORT_13 = "柜员考核报表";
	public static final String WORK_FLOW_REPORT_14 = "考核结果明细查询";
	
	
	/** 数据字典CODE定义**/
	//客户类型
	public static final String CUST_TYPE_CODE = "CUSTTYPE";
	//币种
	public static final String CURRENCY_CODE = "CURRENCY";
	//贷款期限
	public static final String TERMNO_CODE = "TERMNO";
	//担保方式
	public static final String ASSURE_MODE_CODE = "ASSUREMODE";
	//授信类型
	public static final String LOAN_TYPE_CODE = "LOANTYPE";
	//企业类型
	public static final String ENTERPRISESIZE_CODE = "ENTERPRISESIZE";
	//行业类型
	public static final String INDUSTRY_TYPE_CODE = "INDUSTRYTYPE";
	//注册地
	public static final String AREA_CODE = "AREACODE";
	//两高一剩分类
	public static final String ELIMINDUSTRY_TYPE_CODE = "ELIMINDUSTRYTYPE";
	//涉农客户类型
	public static final String AGRICULTURE_TYPE_CODE = "AGRICULTURETYPE";
	//业务种类
	public static final String BUSINESS_TYPE_CODE = "BUSINESSTYPE";
	//项目权限
	public static final String PROJECT_RIGHT_CODE = "PROJECTRIGHT";
	//还款方式
	public static final String REPAY_TYPE_CODE = "REPAYTYPE";
	//审批结果
	public static final String APPROVAL_RESULT_CODE = "APPROVALRESULT";
	//信用等级
	public static final String CREDIT_LEVEL_CODE = "CREDITLEVEL";
	//审核委员
	public static final String APPROVAL_USER_CODE = "APPROVALUSER";
	//出账类型
	public static final String EXPEND_TYPE_CODE = "EXPENDTYPE";
	//出账业务种类
	public static final String EXPEND_BUSINESS_TYPE_CODE = "EXPENDBUSINESSTYPE";
	//审查状态
	public static final String CHECK_STATUS_TYPE_CODE = "CHECKSTATUSTYPE";
	
	
	// 重要程度与紧急程度
	public static final String DEGREE_CODE = "DEGREE";
	public static final String DEGREE_CODE_1= "1"; // 低
	public static final String DEGREE_CODE_2= "2"; // 中
	public static final String DEGREE_CODE_3= "3"; // 高
	// 周期性
	public static final String CYCLE_CODE = "CYCLE";
	public static final String CYCLE_CODE_1 = "1"; //一次性
	public static final String CYCLE_CODE_2 = "2"; //每月
	public static final String CYCLE_CODE_3 = "3";//半月
	public static final String CYCLE_CODE_4 = "4";//每周
 	
	// 工作流实例状态
	public static final String FLOWSTATUS_CODE = "FLOWSTATUS"; 
	public static final String FLOWSTATUS_CODE_0 = "0"; // 初始
	public static final String FLOWSTATUS_CODE_1 = "1"; // 执行中
	public static final String FLOWSTATUS_CODE_2 = "2"; // 完成
	public static final String FLOWSTATUS_CODE_3 = "3"; // 归档
	
	// 处理状态
	public static final String FINISHSTATUS_CODE="FINISHSTATUS";
	public static final String FINISHSTATUS_CODE_0 = "0";//初始
	public static final String FINISHSTATUS_CODE_1 = "1";//执行中
	public static final String FINISHSTATUS_CODE_2 = "2";//完成
	
	// 制度范畴
	public static final String DATASCOPE_CODE = "DATASCOPE";
	// 监管机构
	public static final String DATASUPERVISION_CODE = "DATASUPERVISION";
	
	// 人员选择区分
	public static final String EMPLOYEE_KBN_0 = "0"; // 下一步人员
	public static final String EMPLOYEE_KBN_1 = "1"; // 转交人员
	
	// 评价等级
	public static final String KP_LEVEL1 = "1";
	public static final String KP_LEVEL2 = "2";
	public static final String KP_LEVEL3 = "3";
	public static final String KP_LEVEL4 = "4";
	public static final String KP_LEVEL5 = "5";
	
	// 短信通发送对象区分
	public static final String SEND_USER_KBN_0 = "0"; // 下一步处理人
	public static final String SEND_USER_KBN_1 = "1"; // 当前步骤处理人
	public static final String SEND_USER_KBN_2 = "2"; // 流程发起人
	
}
