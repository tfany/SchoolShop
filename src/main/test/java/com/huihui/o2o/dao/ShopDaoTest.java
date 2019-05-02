package com.huihui.o2o.dao;

import com.huihui.o2o.BaseTest;
import com.huihui.o2o.pojo.Area;
import com.huihui.o2o.pojo.Shop;
import com.huihui.o2o.pojo.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    public void insertShop() {
        Shop shop = new Shop();
        shop.setOwnerId(9L);
        Area area = new Area();
        area.setAreaId(4L);
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(10L);
        sc.setParentId(12L);
        shop.setShopName("江的店铺");
        shop.setShopDesc("茶叶蛋");
        shop.setShopAddr("宿舍");
        shop.setPhone("13810524526");
        shop.setShopImg("xxxx");
        shop.setLongitude(1D);
        shop.setLatitude(1D);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(0);
        shop.setAdvice("审核中");
        shop.setArea(area);
        shop.setPriority(100);
        shop.setShopCategory(sc);
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    public void updateShop() {
        Shop shop = new Shop();
        shop.setOwnerId(9L);
        shop.setShopId(28L);
        shop.setShopName("芳芳的店铺");
        shop.setShopDesc("奶茶店哟");
        shop.setShopAddr("不知道");
        shop.setPhone("13810524526");
        shop.setShopImg("xxxx");
        shop.setLongitude(1D);
        shop.setLatitude(1D);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(0);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testShopQueyr() {
        Shop shop = new Shop();
        shop = shopDao.queryByShopId(15L);
        long num = shop.getShopId();
        int num1 = (int) num;
        assertEquals(15, num1);

    }

    @Test
    public void testQueryListAndCount() {
        Shop shop=new Shop();
        shop.setOwnerId(8L);
        List<Shop> shopList=shopDao.queryShopList(shop,0,20);
        int sum=shopDao.queryShopCount(shop);
        System.out.println(sum);
        System.out.println(shopList.size());
    }



}
