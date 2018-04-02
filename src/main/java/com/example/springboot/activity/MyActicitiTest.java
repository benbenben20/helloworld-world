package com.example.springboot.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.TaskService;
import org.activiti.engine.test.Deployment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springboot.SpringBootWorldApplication;
import com.example.springboot.service.PaymentProcessService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootWorldApplication.class)// 指定spring-boot的启动类 
public class MyActicitiTest {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ProcessFacadeService processFacadeService;
	
	@Autowired
	private PaymentProcessService paymentProcessService;
	
	@Test
	@Deployment(resources={"/spring-boot-sample-world/src/main/resources/META-INF/diagrams/client_blacklist_process.bpmn20.xml"})
	public void blackListTestProcess(){
		String carLoanOrderID = UUID.randomUUID().toString()+"test";
		System.out.println("---------------------------------businessId=" + carLoanOrderID);
		int orgId = 100;
		List<PendingTask> task_ = processFacadeService.startBusinessProcess(ProcessTypeEnum.client_blacklist_process, carLoanOrderID, orgId);
		//黑名单提交员到黑名单审核员
		Assert.assertEquals("client_blacklist_audit", completeTargetBusinessTask(carLoanOrderID,"137",null,true));

	}
	
	
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

}
