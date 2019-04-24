package com.huihui.o2o.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class ShopAuthMap {

	private Long shopAuthId;
	private Long employeeId;
	private Long shopId;
	private String name;
	private String title;
	private Integer titleFlag;
	private Integer enableStatus;
	private Date createTime;
	private Date lastEditTime;
	private PersonInfo employee;
	private Shop shop;
}
