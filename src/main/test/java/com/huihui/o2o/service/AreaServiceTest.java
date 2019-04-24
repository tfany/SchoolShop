package com.huihui.o2o.service;

import com.huihui.o2o.BaseTest;
import com.huihui.o2o.pojo.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;

    @Test
    public void getAreaList(){
        List<Area> areaServiceList = areaService.getAreaList();
        assertEquals("东苑",areaServiceList.get(0).getAreaName());
    }
}
