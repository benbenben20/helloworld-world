package com.example.springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FlowBackMissionMapper {
	
	@Select(" SELECT EXECUTION_ID_ FROM  act_ru_task where CATEGORY_ = #{cateGory}")
	public String getExecutionIdByCateGray(@Param(value="cateGory") String cateGory);
	
	@Select(" SELECT group_concat(EXECUTION_ID_) FROM  act_ru_task where CATEGORY_ like '%" + "${cateGory}" + "%'")
	public String getExecutionIdByLikeCateGray(@Param(value="cateGory") String cateGory);
	
	@Select(" SELECT group_concat(NAME_) FROM  act_ru_task where CATEGORY_ like '%" + "${cateGory}" + "%'")
	public String getNameByLikeCateGray(@Param(value="cateGory") String cateGory);

	@Select(" SELECT CATEGORY_ FROM act_ru_task WHERE EXECUTION_ID_ = #{executionId}")
	public String getOrderIdByExecutionId(String executionId);
	
	@Update(" UPDATE act_ru_task t1 SET t1.ASSIGNEE_= #{assignee} WHERE t1.CATEGORY_= #{cateGory}")
	public void updateAssigneeByCateGory(@Param(value="assignee")String assignee,@Param(value="cateGory") String cateGory);

	@Delete(" DELETE a,b FROM tbl_postloan_record a INNER JOIN tbl_postloan_record_detail b ON a.product_no = b.product_no WHERE a.product_no = #{productNo}")
	public void deletePostloanRecordBy(String productNo);

//	@SelectProvider(type=com.midai.flowback.provider.FlowBackMissionProvider.class,method="deleteProductDataByOrderId")
//	public void deleteProductDataByOrderId(@Param("orderId") String orderId);
	
//	@SelectProvider(type=com.midai.flowback.provider.FlowBackMissionProvider.class,method="updateLeasePayTimeAndPayMoney")
//	public void updateLeasePayTimeAndPayMoney(@Param(value="orderId") String orderId);

	@Update(" UPDATE tbl_car_order_amount SET review_money = #{money}, final_money = #{money}, risk_money = #{money}, manager_money = #{money} WHERE order_id = #{businessKey}")
	public void updateManageMoneyByOderId(@Param(value="businessKey") String businessKey, @Param(value="money")double money);
	
//	@Update(" UPDATE tbl_car_order_review SET amount = #{money} WHERE review_result = 1 AND review_level IN (2,5,6,7,8)")
	public void updateCarOrderReviewAmount(double money);

	@Update(" UPDATE tbl_car_order_loan_final SET manager_money = #{money} WHERE order_id = #{businessKey}")
	public void updateCarOrderLoanFinal(@Param(value="money")double money,@Param(value="businessKey") String businessKey);
	
//	@SelectProvider(type=com.midai.flowback.provider.FlowBackMissionProvider.class,method="getOperateUserByOrderId")
//	public String getOperateUserByOrderId(@Param("businessKey") String businessKey);

	/**
	 * 根据orderId到贷后表中查询记录
	 * @param orderId
	 * @return
	 */
	@Select(" SELECT COUNT(1) FROM tbl_postloan_record WHERE product_no = #{orderId}")
	public long getPostloanRecordByOrderId(String orderId);

	/**
	 * 修改进件的申请状态
	 * @param businessKey
	 */
//	@SelectProvider(type=com.midai.flowback.provider.FlowBackMissionProvider.class,method="updateRunState")
//	public void updateRunState(@Param("businessKey") String businessKey, @Param("runState") String runState);

	/**
	 * 删除贷后还款数据
	 * @param businessKey
	 */
	@Delete(" DELETE a,b FROM tbl_postloan_payment a INNER JOIN tbl_postloan_payment_review b ON a.product_no = b.product_no WHERE a.product_no = #{productNo}")
	public void deletePostloanRepaymentRecordBy(@Param("productNo")  String productNo);

	/**
	 * 删除gps和抵押金相关数据
	 * @param businessKey
	 */
	@Delete(" DELETE t1,t2,t3 FROM tbl_car_deposit_bs t1 "
			+ " left join tbl_car_deposit_review t2 on t1.order_id_view = t2.order_id "
			+ " left join tbl_car_deposit_history t3 on t1.order_id_view = t3.order_id "
			+ " where t1.order_id = #{businessKey}")
	public void deleteDepositData(@Param("businessKey") String businessKey);
	
	@Delete(" DELETE t1,t2 from tbl_car_deposit_refund_bs t1"
			+ " left join tbl_car_deposit_refund_review t2 on t1.order_id_view = t2.order_id  "
			+ " where t1.order_id = #{businessKey}")
	public void deleteDepositRefundData(@Param("businessKey") String businessKey);
	
	@Delete(" DELETE f,g FROM tbl_car_gps_deposit_bs f"
			+ " left join tbl_car_gps_refund_review g on f.order_id = g.order_id "
			+ " WHERE f.order_id = #{businessKey}")
	public void deleteGpsData(@Param("businessKey") String businessKey);

	/**
	 * 更新建议抵押金额
	 * @param businessKey
	 */
//	@SelectProvider(type=com.midai.flowback.provider.FlowBackMissionProvider.class,method="updateManagerMortMoney")
//	public void updateManagerMortMoney(@Param("businessKey") String businessKey);

	/**
	 * 删除销账相关操作数据
	 * @param businessKey
	 */
	@Delete(" DELETE t1,t2,t3,t4 FROM tbl_postloan_record_offs t1"
			+ " left join tbl_postloan_record_offs_operate t2 on t1.product_no = t2.product_no "
			+ " left join tbl_postloan_record_offs_review t3 on t1.product_no = t3.product_no "
			+ " left join tbl_postloan_record_status_operate t4 on t1.product_no = t4.product_no "
			+ " WHERE t1.product_no = #{businessKey}")
	public void deleteOffsData(String businessKey);

	/**
	 * 判断进件是否是代扣
	 * @param orderId
	 * @return
	 */
	@Select(" SELECT COUNT(1) FROM tbl_hf_withhold_detail WHERE order_id = #{orderId}")
	public long getWithholdByOrderId(String orderId);

	/**
	 * 判断该进件是否在资产包列表中
	 * @param orderId
	 * @return
	 */
//	@SelectProvider(type=com.midai.flowback.provider.FlowBackMissionProvider.class,method="getProductPackageByOrderId")
//	public long getProductPackageByOrderId(@Param("orderId") String orderId);

	/**
	 * 根据进件单号删除正在流程中的放款申请
	 * @param businessKey
	 */
	@Delete(" DELETE t1,t2,t3 FROM tbl_fk_payment t1,tbl_fk_payment_apply t2,act_ru_task t3"
			+ " WHERE t1.payment_no = t2.payment_no AND t3.CATEGORY_ = t2.payment_no AND t2.order_id = #{businessKey}")
	public void deleteFkPaymentData(String businessKey);

	
}
