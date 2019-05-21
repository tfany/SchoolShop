package com.huihui.o2o.dao;

import com.huihui.o2o.BaseTest;
import com.huihui.o2o.pojo.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Test
    public void queryTest(){

        List<ShopCategory> shopCategoryList=shopCategoryDao.queryShopCategory(null);
        assertEquals(6,shopCategoryList.size());
    }
}
