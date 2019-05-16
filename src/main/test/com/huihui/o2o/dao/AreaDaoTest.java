package com.huihui.o2o.dao;

import com.huihui.o2o.BaseTest;
import com.huihui.o2o.pojo.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;


    /**
     * 测试dao层
     */
    @Test
    public void testQueryArea(){
        List<Area> areaList=areaDao.queryArea();
        assertEquals(4,areaList.size());

    }
}
