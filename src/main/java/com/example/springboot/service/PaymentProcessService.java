package com.example.springboot.service;

import java.util.List;

import com.example.springboot.activity.PendingTask;
import com.example.springboot.mybatis.model.MessageModel;

public interface PaymentProcessService {

	void startOrderProcess(String orderId,String employeeId, MessageModel msgM);
	
	 void runOrderProcess(String taskId, String orderId, String employeeId, String processRole, boolean approval,String road, MessageModel msgM);
	
	 void runOrderProcess(String taskId, String orderId, String processRole, String employeeId, boolean approval, String road, String retBy,MessageModel msgM);
	
	 void skipZjlProcess(String taskId, String orderId, String processRole,  String employeeId, boolean approval,String road, MessageModel msgM, String createUser);
	
	 void jumpProcess(String taskId, String orderId, String processRole,  String employeeId, String retBy, MessageModel msgM, boolean skipZjl);
	
	 void closeProcess(String taskId, String processRole, String orderId, String employeeId,MessageModel msgM);
	
	List<PendingTask> pendingTaskPages(String employeeId, int start, int size);
	
	int pendingTaskCount(String employeeId);
	
	void testPaymentProcess();
	
   String querySpecificUser(int orgId,int roleId);
}
