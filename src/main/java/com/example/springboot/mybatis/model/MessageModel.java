package com.example.springboot.mybatis.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class MessageModel implements Serializable {

	private static final long serialVersionUID = 67939105009332400L;
	private Integer msgId;
    private String employeeId;	//所属人
    private String msgTitle;
    private String msgContent;
    private Integer msgStatus;
    private String msgStatusDesc;
    private Date createTime;
    private Date updateTime;
    private String addressorId;	//发件人Id
    private String addressorName;	//发件人姓名
    private Integer msgType;	//信件类型 0 系统通知  1 员工通信
    private String msgResource;	//信件来源，如米袋汽车、融资租赁
}
