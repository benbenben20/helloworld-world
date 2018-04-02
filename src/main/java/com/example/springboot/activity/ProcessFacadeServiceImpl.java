package com.example.springboot.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.NativeTaskQuery;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

public class ProcessFacadeServiceImpl implements ProcessFacadeService{
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	RepositoryService repositoryService;
	
	@Autowired
	ProcessEngineConfiguration processEngineConfiguration;
	
	@Autowired
	ProcessEngineFactoryBean processEngine;
	
	@Override
	public List<PendingTask> startBusinessProcess(ProcessTypeEnum processType, String bussinessId, Integer orgId) {

		Map<String,String> map = null;
		StringBuilder sb = null;
		if(orgId != null){
			sb = new StringBuilder();
			sb.append("o").append(orgId);
		}
		if(sb != null){
			map = new HashMap<String,String>();
			map.put(ProcessVariablesEnum.include_orgs.toString(), sb.toString());
		}
		return startProcess(processType, bussinessId, map);
	}

	public List<PendingTask> startProcess(ProcessTypeEnum processType,String businessKey,Map<String,String> processVariables){
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(processVariables != null){
			for(Map.Entry<String, String> entry : processVariables.entrySet()){
				map.put(entry.getKey(), entry.getValue());
			}
		}
		map.put("_ACTIVITI_SKIP_EXPRESSION_ENABLED", true);//跳出某个节点
		this.runtimeService.startProcessInstanceById(processType.getProcessDefId(), businessKey, map);
		return getCurrentProcessPendingTasksByBusiness(businessKey);
	}
	
	private List<PendingTask> getCurrentProcessPendingTasksByBusiness(String businessKey){
		List<Task> taskList = this.taskService.createTaskQuery().processInstanceBusinessKey(businessKey).list();
		return taskList == null ? null : ProcessUtils.BuildListPendingTaskByTaskList(taskList);
	}
	
	
	@Override
	public PendingTask getPendingTaskByTaskID(String taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PendingTask> getPendingTaskByUserOrRolesInOrg(String userId, List<Integer> roleIds,
			List<Integer> orgIds) {
		StringBuffer sb = new StringBuffer();
		if (null != userId) {
			sb.append("u").append(userId).append(",");
		}
		if (null != roleIds && roleIds.size() > 0) {
			for (Integer roleId : roleIds) {
				sb.append("r").append(roleId).append(",");
			}
		}
		if (null != orgIds && orgIds.size() > 0) {
			for (Integer orgId : orgIds) {
				sb.append("o").append(orgId).append(",");
			}
		}
		return ProcessUtils.BuildListPendingTaskByTaskList(heloMakeNativeTaskQuery(sb.toString()));
	}

	@Override
	public List<PendingTask> getPendingTaskByUserOrRolesInOrg(String[] processDefArr, String userId,
			List<Integer> roleIds, List<Integer> orgIds, int start, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PendingTask> completeCarLoanProcessTask(String uid, String orderId, String taskId,
			Map<String, String> processVariables) {
		return completeProcessTask(uid, orderId, taskId, processVariables);
	}
	
	private List<PendingTask> completeProcessTask(String uid,String businessKey,String taskId,Map<String,String> processVariables){
		return claimAndCompleteTask(uid, businessKey, taskId, processVariables);
	}
	
	private List<PendingTask> claimAndCompleteTask(String uid,String businessKey,String taskId,Map<String,String> processVariables){
		Map<String,Object> map = null;
		if(processVariables != null){
			map = new HashMap<String,Object>();
			for(Map.Entry<String, String> entry:processVariables.entrySet()){
				map.put(entry.getKey(), entry.getValue());
			}
		}
		//让流程记住每一个任务的最后执行人
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task != null){
			map.put(task.getTaskDefinitionKey(), "u"+uid);
		}
		this.taskService.setOwner(taskId, "u"+uid);
		this.taskService.complete(taskId, map);
		return getCurrentProcessPendingTasksByBusiness(businessKey);
	}
	
	
	
	

	@Override
	public List<PendingTask> approvalCarLaonProcessTask(String uid, String orderId, String taskId,
			Map<String, String> processVariables) {
		if(processVariables == null){
			processVariables = new HashMap<String,String>();
		}
		processVariables.put(ProcessVariablesEnum.approval.toString(), ProcessVariablesValuesEnum.approval_approve.toString());
		return this.completeCarLoanProcessTask(uid, orderId, taskId, processVariables);
	}

	@Override
	public List<PendingTask> rejectCarLoanProcessTask(String uid, String orderId, String taskId,
			Map<String, String> processVariables) {
		if(processVariables == null){
			processVariables = new HashMap<String,String>();
		}
		processVariables.put(ProcessVariablesEnum.approval.toString(), ProcessVariablesValuesEnum.approval_reject.toString());
		return this.completeCarLoanProcessTask(uid, orderId, taskId, processVariables);
	}
	
	

	private List<Task> heloMakeNativeTaskQuery(String parameterStr){
		StringBuilder sb = new StringBuilder("select RES.* from ACT_RU_TASK RES WHERE SUSPENSION_STATE_ != 2 and (");
		sb.append("  #{parameter} REGEXP RES.ASSIGNEE_  ) ");
		sb.append(" order by CREATE_TIME_ asc ");
		NativeTaskQuery ntq = taskService.createNativeTaskQuery().sql(sb.toString());
		ntq.parameter("parameter", parameterStr);
		return ntq.list();
	}
}



















