package com.example.springboot.activity;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.task.Task;

public class ProcessUtils {

	public static List<PendingTask> BuildListPendingTaskByTaskList(List<Task> tList){
		if(tList == null){
			return null;
		}
		List<PendingTask> pendingTaskList = new ArrayList<PendingTask>();
		for(Task task : tList){
			pendingTaskList.add(BuildPendingTaskByTask(task));
		}
		return pendingTaskList;
	}
	
	private static PendingTask BuildPendingTaskByTask(Task t){
		if(t == null){
			return null;
		}
		PendingTask pt = new PendingTask();
		BuildProcessTaskByTask(pt, t);
		return pt;
	}
	
	private static void BuildProcessTaskByTask(ProcessTask pt,Task t){
		pt.setId(t.getId());
		pt.setName(t.getName());
		pt.setDescription(t.getDescription());
		pt.setCreateTime(t.getCreateTime());
		pt.setAssignee(t.getAssignee());
		pt.setProcessKey(t.getProcessDefinitionId().split(":")[0]);
		pt.setTaskKey(t.getTaskDefinitionKey());
		pt.setFormKey(t.getFormKey());
		pt.setBusinessKey(t.getCategory());
	}
}
