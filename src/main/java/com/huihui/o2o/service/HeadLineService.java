package com.huihui.o2o.service;

import com.huihui.o2o.pojo.HeadLine;

import java.util.List;

public interface HeadLineService {
    List<HeadLine> getHeadLineList(HeadLine headLineCondition);
}
