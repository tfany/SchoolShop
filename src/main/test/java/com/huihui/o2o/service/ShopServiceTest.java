package com.huihui.o2o.service;

import com.huihui.o2o.BaseTest;
import com.huihui.o2o.dto.ShopExecution;
import com.huihui.o2o.pojo.Area;
import com.huihui.o2o.pojo.Shop;
import com.huihui.o2o.pojo.ShopCategory;
import com.huihui.o2o.util.FileToMult;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

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
        shop.setShopName("mytest1");
        shop.setShopDesc("mytest1");
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

        ShopExecution se = shopService.addShop(shop, FileToMult.change("c:\\Users\\huige\\Pictures\\gg.jpg"));
        assertEquals("mytest1", se.getShop().getShopName());
    }

    @Test
    public void testModifyShop() throws IOException {
        Shop shop=shopService.getShopByShopId(37L);
        shop.setShopName("怕怕");
        shopService.modifyShop(shop, FileToMult.change("c:\\Users\\huige\\Pictures\\gg.jpg"));
    }

    @Test
    public void testQueryList(){
        Shop shop=new Shop();
        shop.setOwnerId(8L);
        ShopExecution execution =shopService.getShopList(shop,1,5);
        System.out.println(execution.getCount());
    }


}
