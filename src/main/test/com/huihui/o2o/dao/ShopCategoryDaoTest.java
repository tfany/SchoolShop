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
        ShopCategory shopCategory=new ShopCategory();
        List<ShopCategory> shopCategoryList=shopCategoryDao.queryShopCategory(shopCategory);
        assertEquals(12,shopCategoryList.size());
    }
}
