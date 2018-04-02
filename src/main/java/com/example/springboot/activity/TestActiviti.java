/**
 * Project Name:midai-oa-service-impl
 * File Name:ActivitiTest.java
 * Package Name:com.midai.activiti.test
 * Date:2017年3月15日下午4:42:19
 * Copyright (c) 2017, Shanghai midaigroup Technology Co., Ltd. All Rights Reserved.
 *
 */

package com.example.springboot.activity;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.TaskServiceImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springboot.SpringBootWorldApplication;
import com.example.springboot.mybatis.mapper.FlowBackMissionMapper;
import com.example.springboot.service.PaymentProcessService;

/**
 * ClassName:ActivitiTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年3月15日 下午4:42:19 <br/>
 * @author   屈志刚
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootWorldApplication.class)// 指定spring-boot的启动类 
public class TestActiviti {

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private ProcessExecutorConfigService processExecutorConfigService;

	@Autowired
	private ProcessFacadeService processFacadeService;
	
	@Autowired
	private FlowBackMissionMapper flowBackMissionMapper;
	
	@Autowired
	private PaymentProcessService paymentProcessService;
	
//	@Autowired
//	private IZqSignatureService iZqSignatureService;
	
	
//	@Test
//	public void testPaymentProcess(){
//		paymentProcessService.testPaymentProcess();
//	}
	
	
	
	/*

	@SuppressWarnings("deprecation")
	@Test
	@Deployment
	public void testGetLeaseVin(){
		Logger logger = LoggerFactory.getLogger(ActivitiTest.class);
		System.out.println("coming in the method ~ ");
		// 获得所有进件状态为'lease-zhuanyuan-vin'的进件的act_ru_task表中的execution_id
		List<String> executionIds = testMapper.getExecutionIdByCateGray();
		logger.info(executionIds.toString());
		String activityId = "treasury_approve";
		String assignee = "uadmin,|r80,.*o45,";
		int count = 1;// 计数器
		for (String executionId : executionIds) {
			
			String orderId = testMapper.getOrderIdByExecutionId(executionId);
			logger.info("===正在处理的orderId为：" + orderId);
			logger.info("===正在处理的executionId为：" + executionId);
			logger.info(count +"-1"+"===开始跳转工作流节点,进件单号为："+ orderId+",执行id为：" + executionId);
			
			TaskServiceImpl taskServiceImpl=(TaskServiceImpl)taskService;  
			taskServiceImpl.getCommandExecutor().execute(new JumpTaskCmd(executionId, activityId));  
		
			logger.info("===进件："+ orderId+" 的工作流节点跳转完毕。");
			// 更新tbl_lease_order_apply表中的run_state改为：'treasury_approve'
			logger.info(count +"-2"+"===开始更新进件："+orderId+"的进件状态为："+ activityId);
		
			leaseOrderApplyMapper.updateRunstate(orderId, activityId);
			
			logger.info("===进件：" + orderId+" 的进件状态更新完毕。");

			logger.info(count +"-3"+"===开始更新进件："+orderId+" 的委托人为：" + assignee);
			
			testMapper.updateAssigneeByCateGory(assignee, orderId);
			
			logger.info("===进件："+orderId+" 的委托人更新完毕");

			logger.info(count +"-4"+"===开始删除2张还款表的数据。");
			
			testMapper.deletePostloanRecordBy(orderId);
			
			logger.info("===已删除。");

			logger.info(count +"-5"+"===开始删除3张资产表的相关数据。");
			
			testMapper.deleteProductDataByOrderId(orderId);
			
			logger.info("===已删除。");
			
			logger.info(count +"-6"+"===更新订单借款表的放款时间和放款金额。");
			testMapper.updateLeasePayTimeAndPayMoney(orderId);
			logger.info("===更新完毕");
			count++;
		}

	}

	@SuppressWarnings("deprecation")
	@Test
	@Deployment
	public void carDepositProcessTest(){
		String carLoanOrderID = UUID.randomUUID().toString()+"test";
		System.out.println("---------------------------------businessId=" + carLoanOrderID);

		int orgId = 100;

		List<PendingTask> task_ = processFacadeService.startBusinessProcess(ProcessTypeEnum.car_process, carLoanOrderID, orgId);

		// create_order(r115),	
		// order_first_evaluate(r41), order_second_evaluate(r43)
		// car_first_evaluate(r42),	car_second_evaluate(r44)
		// outer_visit(r45)
		// order_final_evaluate(r46),	risk_control(r47),	gm_approve(r48)
		// order_first_evaluate_contract(r85), contract_approve(r61), 
		// treasury_approve(r80)
		// package_evaluate(r126)
		// package_submit(r84)
		// road ->　0:回退, 1:外访, 2:正常流程, 3:跳过节点

		//车贷专员  -> 初审/一评
		Assert.assertEquals("car_first_evaluate,order_first_evaluate", completeTargetBusinessTask(carLoanOrderID,"115",null,true));

		//初审 ->一评/复审
		Assert.assertEquals("car_first_evaluate,order_second_evaluate",  completeTargetBusinessTask(carLoanOrderID,"41",null,true));
		//一评 ->二评/复审
		Assert.assertEquals("car_second_evaluate,order_second_evaluate",  completeTargetBusinessTask(carLoanOrderID,"42",null,true));

		// 复审 不通过  ->初审/二评  =========================================
		Assert.assertEquals("car_second_evaluate,order_first_evaluate!",  completeTargetBusinessTask(carLoanOrderID,"43",null,false));
		//	初审 -> 二评/复审
		Assert.assertEquals("car_second_evaluate,order_second_evaluate",  completeTargetBusinessTask(carLoanOrderID,"41",null,true));

		//case 1-0 :复审 (跳过外访) -> 二评/终审  
		Assert.assertEquals("car_second_evaluate,order_final_evaluate", 
				completeTargetBusinessTask(carLoanOrderID,"43",makeMapfromString(new String[]{"road","3"}),true));

		//case 1-1 : 复审 -> 外访  
		Assert.assertEquals("car_second_evaluate,outer_visit", 
				completeTargetBusinessTask(carLoanOrderID,"43",makeMapfromString(new String[]{"road","2"}),true));
		// 外访 -> 二评/终审
		Assert.assertEquals("car_second_evaluate,order_final_evaluate",  completeTargetBusinessTask(carLoanOrderID,"45",null,true));  

		// 终审 (外派) -> 二评/外访   ============================
		Assert.assertEquals("car_second_evaluate,outer_visit", 
				completeTargetBusinessTask(carLoanOrderID,"46",makeMapfromString(new String[]{"road","1"}),true));

		//外访  -> 二评/终审
		Assert.assertEquals("car_second_evaluate,order_final_evaluate", 
				completeTargetBusinessTask(carLoanOrderID,"45",null,true));

		// 二评 ->终审
		Assert.assertEquals("order_final_evaluate", 
				completeTargetBusinessTask(carLoanOrderID,"44",null,true));

		//终审 回退到  复审
		Assert.assertEquals("order_sp__edit!", 
				completeTargetBusinessTask(carLoanOrderID,"46",
					makeMapfromString(new String[]{"road","0"},new String[]{"form_sp__edit","order_second_evaluate"}),false));
		Assert.assertEquals("order_final_evaluate", 
				completeTargetBusinessTask(carLoanOrderID,"43",null,true));

		//终审 ->风控
		Assert.assertEquals("risk_control", 
				completeTargetBusinessTask(carLoanOrderID,"46",makeMapfromString(new String[]{"road","2"}),true));

		//风控 回退到  终审
		Assert.assertEquals("order_sp__edit2!", 
				completeTargetBusinessTask(carLoanOrderID,"47",
					makeMapfromString(new String[]{"road","0"},new String[]{"form_sp__edit","order_final_evaluate"}),false));
		Assert.assertEquals("risk_control", 
				completeTargetBusinessTask(carLoanOrderID,"46",null,true));


		//风控 -> 总经理
		Assert.assertEquals("gm_approve", 
				completeTargetBusinessTask(carLoanOrderID,"47",null,true));

		// 总经理 -> 初审合同
		Assert.assertEquals("order_first_evaluate_contract", 
				completeTargetBusinessTask(carLoanOrderID,"48",null,true));
		// 初审合同 -> 合同审查员
		Assert.assertEquals("contract_approve", 
				completeTargetBusinessTask(carLoanOrderID,"85",null,true));
		// 合同审查员 -> 资管
		Assert.assertEquals("treasury_approve", 
				completeTargetBusinessTask(carLoanOrderID,"61",null,true));
		//合同审查员 -> 结束
		Assert.assertEquals("complete", 
				completeTargetBusinessTask(carLoanOrderID,"80",null,true));
    }

	@SuppressWarnings("deprecation")
	@Test
	@Deployment
	public void testProductAgencyPackage(){
		String carLoanOrderID = UUID.randomUUID().toString()+"test";
		System.out.println("---------------------------------businessId=" + carLoanOrderID);

		int orgId = 100;

		List<PendingTask> task_ = processFacadeService.startBusinessProcess(ProcessTypeEnum.product_package_process, carLoanOrderID, orgId);

		// package_submit(r84)
		// package_evaluate(r126)

		// 产品专员  -> 初审
		Assert.assertEquals("package_evaluate", completeTargetBusinessTask(carLoanOrderID,"84",null,true));

		// 审核 不通过  ->产品专员  =========================================
		Assert.assertEquals("package_submit!",  completeTargetBusinessTask(carLoanOrderID,"126",null,false));
		Assert.assertEquals("package_evaluate", completeTargetBusinessTask(carLoanOrderID,"84",null,true));
		Assert.assertEquals("package_submit!",  K(carLoanOrderID,"126",null,false));
		Assert.assertEquals("package_evaluate", completeTargetBusinessTask(carLoanOrderID,"84",null,true));

		//	审核 通过 -> 结束
		Assert.assertEquals("complete",  completeTargetBusinessTask(carLoanOrderID,"126",null,true));

		System.out.println("流程结束。。。。。。。。。。。。。。");
		System.out.println("流程结束。。。。。。。。。。。。。。");

	}
*/
	private String completeTargetBusinessTask(String businessKey,String userID,Map<String,String> map,boolean approval){
		List<Integer> roleIds = new ArrayList<Integer>();
		roleIds.add(Integer.parseInt(userID));



		List<Integer> orgIds = new ArrayList<Integer>();
		orgIds.add(100);
		orgIds.add(200);

		List<PendingTask> ptList = processFacadeService.getPendingTaskByUserOrRolesInOrg(userID, roleIds, orgIds);

		String processStatus = null;
		for(PendingTask pt:ptList){
			if(pt.getBusinessKey().equals(businessKey)){
				List<PendingTask> pList = null; 

				if(approval){
					pList = processFacadeService.approvalCarLaonProcessTask(userID, businessKey, pt.getId(), map);	
				}else{
					pList = processFacadeService.rejectCarLoanProcessTask(userID, businessKey, pt.getId(), map);
				}

				if(pList==null||pList.size()==0){
					return "complete";
				}

				String[] strArray = new String[pList.size()];
				for(int i=0;i<pList.size();i++){
					PendingTask p = pList.get(i);
					strArray[i]=p.getTaskKey()+(ProcessVariablesValuesEnum.approval_reject.toString().equalsIgnoreCase(p.getDescription())?"!":"");
				}
				Arrays.sort(strArray);

				StringBuilder sb = new StringBuilder();
				for(String str:strArray){
					sb.append(str).append(",");
				}
				sb.deleteCharAt(sb.length()-1);

				System.out.println("----------------result:"+sb.toString());
				return sb.toString();
			}
		}
		return processStatus;
	}

	private Map<String,String> makeMapfromString(String[]... strs){
		if(strs==null){
			return null;
		}
		Map<String,String> map = new HashMap<String,String>();
		for(String[] ss:strs){
			map.put(ss[0], ss[1]);
		}
		return map;
	}


	private List<Task> findListTaskByPid(ProcessInstance pi){
		return taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).list();
	}
	
	
	
	
	@Test
	@Deployment(resources={"/spring-boot-sample-world/src/main/resources/META-INF/diagrams/client_blacklist_process.bpmn20.xml"})
	public void blackListTestProcess(){
		String carLoanOrderID = UUID.randomUUID().toString()+"test";
		System.out.println("---------------------------------businessId=" + carLoanOrderID);
		int orgId = 100;
		List<PendingTask> task_ = processFacadeService.startBusinessProcess(ProcessTypeEnum.client_blacklist_process, carLoanOrderID, orgId);
		//黑名单提交员到黑名单审核员
		
	}
	
	
	
	
	
	@Test
	@Deployment(resources={"/spring-boot-sample-world/src/main/resources/META-INF/diagrams/client_blacklist_process.bpmn20.xml"})
	public void testCarProcess(){
		
		String carLoanOrderID = UUID.randomUUID().toString()+"test";
		//String carLoanOrderID = "JJ-CD-170606-037";
		System.out.println("---------------------------------businessId=" + carLoanOrderID);

		int orgId = 100;
		//int orgId = 45;

		List<PendingTask> task_ = processFacadeService.startBusinessProcess(ProcessTypeEnum.car_process, carLoanOrderID, orgId);
		
				 //PendingTask task_ = processFacadeService.getPendingTaskByTaskID("48a6f46f-df2e-11e7-a13c-d8cb8a364836");
		
		//资管退回至初审上传合同
		Assert.assertEquals("order_first_evaluate_contract!",  completeTargetBusinessTask(carLoanOrderID,"80",null,false));
		
		//上传合同提交到合同审核
		Assert.assertEquals("contract_approve", completeTargetBusinessTask(carLoanOrderID,"85",null,true));
		
		//合同审核退回至上传合同
		Assert.assertEquals("order_first_evaluate_contract!",  completeTargetBusinessTask(carLoanOrderID,"61",null,false));
		
		//上传合同提交到合同审核
		Assert.assertEquals("contract_approve", completeTargetBusinessTask(carLoanOrderID,"85",null,true));
		
		//合同审核提交到资管
		Assert.assertEquals("contract_approve", 
				completeTargetBusinessTask(carLoanOrderID,"85",null,true));
		
		
		// 合同审查员 -> 资管
		Assert.assertEquals("treasury_approve", 
				completeTargetBusinessTask(carLoanOrderID,"61",null,true));
		//合同审查员 -> 结束
		Assert.assertEquals("complete", 
				completeTargetBusinessTask(carLoanOrderID,"80",null,true));
		
		
		
		// create_order(r115),	
		// order_first_evaluate(r41), order_second_evaluate(r43)
		// car_first_evaluate(r42),	car_second_evaluate(r44)
		// outer_visit(r45)
		// order_final_evaluate(r46),	risk_control(r47),	gm_approve(r48)
		// order_first_evaluate_contract(r85), contract_approve(r61), 
		// treasury_approve(r80)
		// package_evaluate(r126)
		// package_submit(r84)
		// road ->　0:回退, 1:外访, 2:正常流程, 3:跳过节点

		//车贷专员  -> 初审/一评
		Assert.assertEquals("car_first_evaluate,order_first_evaluate", completeTargetBusinessTask(carLoanOrderID,"115",null,true));

		//初审 ->一评/复审
		Assert.assertEquals("car_first_evaluate,order_second_evaluate",  completeTargetBusinessTask(carLoanOrderID,"41",null,true));
		//一评 ->二评/复审
		Assert.assertEquals("car_second_evaluate,order_second_evaluate",  completeTargetBusinessTask(carLoanOrderID,"42",null,true));

		// 复审 不通过  ->初审/二评  =========================================
		Assert.assertEquals("car_second_evaluate,order_first_evaluate!",  completeTargetBusinessTask(carLoanOrderID,"43",null,false));
		//	初审 -> 二评/复审
		Assert.assertEquals("car_second_evaluate,order_second_evaluate",  completeTargetBusinessTask(carLoanOrderID,"41",null,true));

		//case 1-0 :复审 (跳过外访) -> 二评/终审  
		Assert.assertEquals("car_second_evaluate,order_final_evaluate", 
				completeTargetBusinessTask(carLoanOrderID,"43",makeMapfromString(new String[]{"road","3"}),true));

		//case 1-1 : 复审 -> 外访  
		Assert.assertEquals("car_second_evaluate,outer_visit", 
				completeTargetBusinessTask(carLoanOrderID,"43",makeMapfromString(new String[]{"road","2"}),true));
		// 外访 -> 二评/终审
		Assert.assertEquals("car_second_evaluate,order_final_evaluate",  completeTargetBusinessTask(carLoanOrderID,"45",null,true));  

		// 终审 (外派) -> 二评/外访   ============================
		Assert.assertEquals("car_second_evaluate,outer_visit", 
				completeTargetBusinessTask(carLoanOrderID,"46",makeMapfromString(new String[]{"road","1"}),true));

		//外访  -> 二评/终审
		Assert.assertEquals("car_second_evaluate,order_final_evaluate", 
				completeTargetBusinessTask(carLoanOrderID,"45",null,true));

		// 二评 ->终审
		Assert.assertEquals("order_final_evaluate", 
				
				completeTargetBusinessTask(carLoanOrderID,"44",null,true));

		//终审 回退到  复审
		Assert.assertEquals("order_sp__edit!", 
				completeTargetBusinessTask(carLoanOrderID,"46",
					makeMapfromString(new String[]{"road","0"},new String[]{"form_sp__edit","order_second_evaluate"}),false));
		Assert.assertEquals("order_final_evaluate", 
				completeTargetBusinessTask(carLoanOrderID,"43",null,true));

		//终审 ->风控
		Assert.assertEquals("risk_control", 
				completeTargetBusinessTask(carLoanOrderID,"46",makeMapfromString(new String[]{"road","2"}),true));

		//风控 回退到  终审
		Assert.assertEquals("order_sp__edit2!", 
				completeTargetBusinessTask(carLoanOrderID,"47",
					makeMapfromString(new String[]{"road","0"},new String[]{"form_sp__edit","order_final_evaluate"}),false));
		Assert.assertEquals("risk_control", 
				completeTargetBusinessTask(carLoanOrderID,"46",null,true));


		//风控 -> 总经理
		Assert.assertEquals("gm_approve", 
				completeTargetBusinessTask(carLoanOrderID,"47",null,true));
		

		// 总经理 -> 初审合同
		Assert.assertEquals("order_first_evaluate_contract", 
				completeTargetBusinessTask(carLoanOrderID,"48",null,true));
		// 初审合同 -> 合同审查员
		Assert.assertEquals("contract_approve", 
				completeTargetBusinessTask(carLoanOrderID,"85",null,true));
		
		// 合同审查员 -> 资管
		Assert.assertEquals("treasury_approve", 
				completeTargetBusinessTask(carLoanOrderID,"61",null,true));
		
		
		//资管退回至初审上传合同
		Assert.assertEquals("order_first_evaluate_contract!",  completeTargetBusinessTask(carLoanOrderID,"80",null,false));
		
		//上传合同提交到合同审核
		Assert.assertEquals("contract_approve", completeTargetBusinessTask(carLoanOrderID,"85",null,true));
		
		//合同审核退回至上传合同
		Assert.assertEquals("order_first_evaluate_contract!",  completeTargetBusinessTask(carLoanOrderID,"61",null,false));
		
		//上传合同提交到合同审核
		Assert.assertEquals("contract_approve", completeTargetBusinessTask(carLoanOrderID,"85",null,true));
		
		//合同审核提交到资管
		Assert.assertEquals("contract_approve", 
				completeTargetBusinessTask(carLoanOrderID,"85",null,true));
		
		
		// 合同审查员 -> 资管
		Assert.assertEquals("treasury_approve", 
				completeTargetBusinessTask(carLoanOrderID,"61",null,true));
		//合同审查员 -> 结束
		Assert.assertEquals("complete", 
				completeTargetBusinessTask(carLoanOrderID,"80",null,true));
	}
	
	@Test
	@Deployment(resources="/midai-oa-service-impl/src/main/resources/META-INF/diagrams/lease_process.bpmn20.xml")
	public void testLeaseProcess(){
		
		/**
		 * 专员 r79
		 * 初审 r64    复审  r66
		 * 一级车评 r65   二级车评  r67
		 * 外访  r68
		 * 终审  r69
		 * 风控  r70
		 * 总经理审核  r71
		 * 上传合同     r72
		 * 合同审核     r73
		 * 资管审批     r80
		 * vim码或上传其他资料  r112
		 * 资料审核   r113
		 * complete
		 * 
		 */
		
		String carLoanOrderID = UUID.randomUUID().toString()+"test";
		
		System.out.println("---------------------------------businessId=" + carLoanOrderID);

		int orgId = 100;
		
		List<PendingTask> task_ = processFacadeService.startBusinessProcess(ProcessTypeEnum.lease_process, carLoanOrderID, orgId);
		
		// 融资专员  ==>   初审、一级车评
		Assert.assertEquals("lease_car_first_evaluate,lease_order_first_evaluate", completeTargetBusinessTask(carLoanOrderID,"79",null,true));
		
		// 初审  ==>  一级车评、复审
		//Assert.assertEquals("lease_car_first_evaluate,lease_order_second_evaluate", completeTargetBusinessTask(carLoanOrderID,"64",null,true));
		
		// 复审 ==>	一级车评、二级车评
		//Assert.assertEquals("lease_car_first_evaluate,lease_car_second_evaluate", completeTargetBusinessTask(carLoanOrderID,"66",null,true));
		
		//	一级车评	==>	二级车评
		//Assert.assertEquals("lease_car_second_evaluate", completeTargetBusinessTask(carLoanOrderID,"65",null,true));	
		
		//	二级车评 ==>	外访
		//Assert.assertEquals("lease_outer_visit", completeTargetBusinessTask(carLoanOrderID,"67",null,true));	
		
	}
	
	/**
	 * 
	 * paymenyProcessTest:(放款流程测试). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author 屈志刚  
	 * @since JDK 1.7
	 */
	@SuppressWarnings("deprecation")
	@Test
	//@Deployment
	public void paymenyProcessTest(){
		
		Map<String, String> map = new HashMap<>();
		map.put("user_code", "201803061101320001");//用户唯一标示，该值不能重复
		map.put("name", "曹循安");//平台方用户姓名
		map.put("id_card_no", "232301199302016237");//身份证号
		map.put("mobile", "15326463525");//联系电话（手机号码）


		// 测试：个人用户颁发数字证书
//		ZqResultModel resultModel = iZqSignatureService.regPerson(map);
//		System.out.println(resultModel);
		
		String carLoanOrderID = UUID.randomUUID().toString()+"test";
		System.out.println("---------------------------------businessId=" + carLoanOrderID);
		
		// payment_apply(r142),	  放款申请
		// payment_audit(r143),  放款审核
		// gm_approve(r144),     总经理审批
		// treasury_frist_evaluate(r145),   资管初审
		// ibank_docment(r146),  网银制单
		// ibank_payment(r147)   网银放款
		
		paymentProcessService.testPaymentProcess();
		
		System.exit(0);
		
		TaskServiceImpl taskServiceImpl=(TaskServiceImpl)taskService; 
		String executionId;

		int orgId = 100;
		//开始流程 -->  放款申请
		List<PendingTask> task_ = processFacadeService.startBusinessProcess(ProcessTypeEnum.payment_process, carLoanOrderID, orgId);	
		
		
		//放款申请 【提交  ==>】  放款审核
		Assert.assertEquals("payment_audit", completeTargetBusinessTask(carLoanOrderID,"142",null,true));
		//放款审核 【提交  退回至 放款申请】    放款申请
		Assert.assertEquals("payment_apply!", 
				completeTargetBusinessTask(carLoanOrderID,"143",makeMapfromString(new String[]{"road","0"}),false));
		//放款申请【提交 ==>】	放款审核
		Assert.assertEquals("payment_audit", completeTargetBusinessTask(carLoanOrderID,"142",null,true));
		//放款审核【提交 ==>】	总经理审核
		Assert.assertEquals("gm_approve", 
				completeTargetBusinessTask(carLoanOrderID,"143",makeMapfromString(new String[]{"road","1"}),true));
		//总经理【提交==>】	资管初审
		Assert.assertEquals("treasury_frist_evaluate", completeTargetBusinessTask(carLoanOrderID,"144",null,true));
		//资管初审【提交 退回至】 放款申请
		executionId = flowBackMissionMapper.getExecutionIdByCateGray(carLoanOrderID);
        taskServiceImpl.getCommandExecutor().execute(new JumpTaskCmd(executionId, "payment_apply"));
		//放款申请【提交 ==>】	资管初审
		executionId = flowBackMissionMapper.getExecutionIdByCateGray(carLoanOrderID);
        taskServiceImpl.getCommandExecutor().execute(new JumpTaskCmd(executionId, "treasury_frist_evaluate"));
		//资管初审【提交 ==>】	网银制单
        Assert.assertEquals("ibank_docment", completeTargetBusinessTask(carLoanOrderID,"145",null,true));
		//网银制单【提交  退回至】	资管初审
		Assert.assertEquals("treasury_frist_evaluate!", 
				completeTargetBusinessTask(carLoanOrderID,"146",null,false));
		//资管初审【提交 ==>】 网银制单
		Assert.assertEquals("ibank_docment", completeTargetBusinessTask(carLoanOrderID,"145",null,true));
		//网银制单【提交 ==>】	网银放款
		Assert.assertEquals("ibank_payment", 
				completeTargetBusinessTask(carLoanOrderID,"146",null,true)); 
		//网银放款【提交 退回至】	网银制单
		executionId = flowBackMissionMapper.getExecutionIdByCateGray(carLoanOrderID);
        taskServiceImpl.getCommandExecutor().execute(new JumpTaskCmd(executionId, "ibank_docment"));
		//网银制单【提交 ==>】	网银放款
		executionId = flowBackMissionMapper.getExecutionIdByCateGray(carLoanOrderID);
        taskServiceImpl.getCommandExecutor().execute(new JumpTaskCmd(executionId, "ibank_payment"));
		//网银放款【提交 退回至】	资管初审
		executionId = flowBackMissionMapper.getExecutionIdByCateGray(carLoanOrderID);
        taskServiceImpl.getCommandExecutor().execute(new JumpTaskCmd(executionId, "treasury_frist_evaluate"));
		//资管初审【提交 ==>】	网银放款
		executionId = flowBackMissionMapper.getExecutionIdByCateGray(carLoanOrderID);
        taskServiceImpl.getCommandExecutor().execute(new JumpTaskCmd(executionId, "ibank_payment"));
		//网银放款【提交 退回至】	放款申请
		executionId = flowBackMissionMapper.getExecutionIdByCateGray(carLoanOrderID);
        taskServiceImpl.getCommandExecutor().execute(new JumpTaskCmd(executionId, "payment_apply"));
		//放款申请【提交 ==>】	网银放款
		executionId = flowBackMissionMapper.getExecutionIdByCateGray(carLoanOrderID);
        taskServiceImpl.getCommandExecutor().execute(new JumpTaskCmd(executionId, "ibank_payment"));
		//网银放款【提交  结束流程】
		Assert.assertEquals("complete", 
				completeTargetBusinessTask(carLoanOrderID,"147",null,true));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

    }
	
}

