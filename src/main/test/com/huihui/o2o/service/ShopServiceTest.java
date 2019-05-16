package com.huihui.o2o.service;

import com.huihui.o2o.BaseTest;
import com.huihui.o2o.dto.ShopExecution;
import com.huihui.o2o.pojo.Area;
import com.huihui.o2o.pojo.Shop;
import com.huihui.o2o.pojo.ShopCategory;
import com.huihui.o2o.util.FileToMult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testShop() throws IOException {
        Shop shop = new Shop();
        shop.setOwnerId(8L);
        Area area = new Area();
        area.setAreaId(4L);
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(14L);
        shop.setShopName("mytest2");
        shop.setShopDesc("mytest2");
        shop.setShopAddr("testaddr1");
        shop.setPhone("13810524526");
        shop.setShopImg("test1");
        shop.setLongitude(1D);
        shop.setLatitude(1D);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(0);
        shop.setAdvice("审核中");
        shop.setArea(area);
        shop.setShopCategory(sc);

        ShopExecution se = shopService.addShop(shop, FileToMult.getCommonsMult("c:\\Users\\huige\\Pictures\\gg.jpg"));
        assertEquals("mytest2", se.getShop().getShopName());
    }

    @Test
    public void testModifyShop() throws IOException {
        Shop shop=shopService.getShopByShopId(37L);
        shop.setShopName("怕怕");
        shopService.modifyShop(shop, FileToMult.getCommonsMult("c:\\Users\\huige\\Pictures\\gg.jpg"));
    }

    @Test
    public void testQueryList(){
        Shop shop=new Shop();
        shop.setOwnerId(8L);
        ShopExecution execution =shopService.getShopList(shop,1,5);
        System.out.println(execution.getCount());
    }


}
