package com.huihui.o2o.dao;
import com.huihui.o2o.pojo.Area;

import java.util.List;

public interface AreaDao {
    /**
     * 列出区域列表
     * @return areaList
     */
    List<Area> queryArea();
}
