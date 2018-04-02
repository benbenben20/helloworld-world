package com.example.springboot.activity;

public enum ProcessTypeEnum {

	car_process("car_process"),	//车贷流程
	product_process("product_process"),	//产品流程
	lease_process("lease_process"),	//融资租赁流程
	settle_process("settle_process"),	//结算流程
	packing_process("packing_process"), //打包流程
	capital_process("capital_process"),	//资管流程
	car_deposit_process("car_deposit_process"), //车贷抵押金申请流程
	car_deposittk_process("car_deposittk_process"), //车贷押金退款申请流程
	car_gps_refund_process("car_gps_refund_process"), //车贷GPS退款申请流程
	car_defer_process("car_defer_process"), //车贷展期申请流程
	postloan_refund_process("postloan_refund_process"), //车贷还款申请流程
	postloan_offs_process("postloan_offs_process"), //车贷销账申请流程
	postloan_clean_process("postloan_clean_process"), //车贷平账申请流程
	postloan_advance_process("postloan_advance_process"), //车贷提前结款申请流程
	car_suspend_process("car_suspend_process"), //车贷挂起流程
	product_package_process("product_package_process"),//机构资产包申请流程
	withhold_review_process("withhold_review_process"),//代扣金额修改流程 
	client_blacklist_process("client_blacklist_process"),//客户黑名单流程
	complex("complex"),
	/** 修改审批工作流程 */
	flow_back_process("flow_back_process"),
	/** 放款申请工作流程 */
	payment_process("payment_process"),
	test("test_binxin_2");
	
	private String processDefId;
	
	private ProcessTypeEnum(String processDefId){
		this.processDefId = processDefId;
	}
	public String getProcessDefId(){
		return processDefId;
	}
}
