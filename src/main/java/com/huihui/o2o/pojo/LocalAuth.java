package com.huihui.o2o.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class LocalAuth {
	private Long localAuthId;
	private String userName;
	private String password;
	private Long userId;
	private Date createTime;
	private Date lastEditTime;
	private PersonInfo personInfo;
}
