package com.example.springboot.activity;

import java.util.List;
import java.util.Map;

public interface ProcessFacadeService {

	/**
	 * 开启一个流程
	 * @param processType 流程类型
	 * @param bussinessId 业务ID
	 * @param orgId 组织机构ID
	 * @return
	 */
	List<PendingTask> startBusinessProcess(ProcessTypeEnum processType,String bussinessId,Integer orgId);
	
	
	/**
	 * 根据任务ID获取一个代办事项
	 * @param taskID
	 * @return
	 */
	PendingTask getPendingTaskByTaskID(String taskId);
	
	
	/**
	 * 通过单个用户ID，角色ID列表，组织机构ID获取代办事项
	 * 获取该用户ID的代办事项，或者角色ID将于组织机构ID一起进行判断，获取当前机构内该角色的代办事项
	 * @param userId
	 * @param roleIds
	 * @param orgIds
	 * @return
	 */
	List<PendingTask> getPendingTaskByUserOrRolesInOrg(String userId,List<Integer> roleIds,List<Integer> orgIds);
	
	
	/**
	 * 通过单个用户ID，角色ID列表，组织机构ID获取代办事项
	 * 获取该用户ID的代办事项，或者角色ID将于组织机构ID一起进行判断，获取当前机构内该角色的代办事项
	 * @param userId
	 * @param roleIds
	 * @param orgId
	 * @param start 
	 * @param size  
	 * @return
	 */
	List<PendingTask> getPendingTaskByUserOrRolesInOrg(String[] processDefArr,String userId,List<Integer> roleIds,List<Integer> orgIds,int start,int size);


	/**
	 * 完成一个车贷业务任务
	 * @param uid
	 * @param orderId
	 * @param taskId
	 * @param processVariables
	 * @return
	 */
	List<PendingTask> completeCarLoanProcessTask(String uid,String orderId,String taskId,Map<String,String> processVariables);


	/**
	 * 通过一个车贷业务任务
	 * @param uid   执行人ID
	 * @param orderId   车贷订单号
	 * @param taskID    任务ID
	 * @param processVariables  任务变量
	 * @return
	 */
	List<PendingTask> approvalCarLaonProcessTask(String uid,String orderId,String taskId,Map<String,String> processVariables);


	
	/**
	 * 驳回一个车贷业务任务
	 * @param uid   执行人ID
	 * @param orderId   车贷订单号
	 * @param taskID     任务ID
	 * @param processVariables   任务变量
	 * @return
	 */
	List<PendingTask> rejectCarLoanProcessTask(String uid,String orderId,String taskId,Map<String,String> processVariables);
	
	
	
	
	
	


}
