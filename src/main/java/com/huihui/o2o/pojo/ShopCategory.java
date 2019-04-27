package com.huihui.o2o.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ShopCategory {

	private Long shopCategoryId;
	private String shopCategoryName;
	private String shopCategoryDesc;
	private String shopCategoryImg;
	private Integer priority;
	private Date createTime;
	private Date lastEditTime;
	private Long parentId;
}
