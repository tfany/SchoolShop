package com.huihui.o2o.service.impl;

import com.huihui.o2o.dao.AreaDao;
import com.huihui.o2o.pojo.Area;
import com.huihui.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service层
 */
@Service
public class AreaServiceImpl implements AreaService {
    //依赖于dao层 自动注入
    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
