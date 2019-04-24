package com.huihui.o2o.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Area {
	private Long areaId;
	private String areaName;
	private String areaDesc;
	private Integer priority;
	private Date createTime;
	private Date lastEditTime;
}
