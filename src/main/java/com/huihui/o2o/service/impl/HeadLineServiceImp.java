package com.huihui.o2o.service.impl;

import com.huihui.o2o.dao.HeadLineDao;
import com.huihui.o2o.pojo.HeadLine;
import com.huihui.o2o.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HeadLineServiceImp implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;
    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
        return headLineDao.queryHeadLine(headLineCondition);
    }
}
