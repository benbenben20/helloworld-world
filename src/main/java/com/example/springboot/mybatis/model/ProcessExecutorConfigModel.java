package com.example.springboot.mybatis.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.util.StringUtils;

public class ProcessExecutorConfigModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
		private String processKey;
	 	private String taskKey;
	 	private String executorExp;
	 	
	 	private String extendClassname;
	 	private String extendParam;
	 	private Integer lastoneRedo;
	 	
	 	private Date createTime;
	 	private Date updateTime;
	 	private int viewIndex;
	 	// 排序id
	 	private int sortId;
	 	
	 	public ProcessExecutorConfigModel(){
	 	}
	 	
	 	public ProcessExecutorConfigModel(String processKey,String taskKey,String executorExp){
	 		this.processKey = processKey;
	 		this.taskKey = taskKey;
	 		this.executorExp = executorExp;
	 		this.createTime = new Date();
	 	}
	 	
		public String getProcessKey() {
			return processKey;
		}
		public void setProcessKey(String processKey) {
			this.processKey = processKey;
		}
		
		public String getTaskKey() {
			return taskKey;
		}
		public void setTaskKey(String taskKey) {
			this.taskKey = taskKey;
		}
		
		public String getExecutorExp() {
			if(!StringUtils.isEmpty(executorExp)&&!executorExp.endsWith(",")){
				return executorExp+",";
			}
			return executorExp;
		}
		public void setExecutorExp(String executorExp) {
			this.executorExp = executorExp;
		}
		
		public String getExtendClassname() {
			return extendClassname;
		}
		public void setExtendClassname(String extendClassname) {
			this.extendClassname = extendClassname;
		}

		public String getExtendParam() {
			return extendParam;
		}
		public void setExtendParam(String extendParam) {
			this.extendParam = extendParam;
		}

		public Integer getLastoneRedo() {
			return lastoneRedo;
		}
		public void setLastoneRedo(Integer lastoneRedo) {
			this.lastoneRedo = lastoneRedo;
		}

		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public Date getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
		public int getViewIndex() {
			return viewIndex;
		}
		public void setViewIndex(int viewIndex) {
			this.viewIndex = viewIndex;
		}

		public int getSortId() {
			return sortId;
		}

		public void setSortId(int sortId) {
			this.sortId = sortId;
		}
}