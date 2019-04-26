package com.huihui.o2o.service;

import com.huihui.o2o.BaseTest;
import com.huihui.o2o.dto.ShopExecution;
import com.huihui.o2o.pojo.Area;
import com.huihui.o2o.pojo.Shop;
import com.huihui.o2o.pojo.ShopCategory;
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
        File file = new File("c:\\Users\\huige\\Pictures\\gg.jpg");

        FileItem fileItem = new DiskFileItem(
                "formFieldName",//form表单文件控件的名字随便起
                Files.probeContentType(file.toPath()),//文件类型
                false, //是否是表单字段
                file.getName(),//原始文件名
                (int) file.length(),//Interger的最大值可以存储两部1G的电影
                file.getParentFile());//文件会在哪个目录创建

        //最关键的一步：为DiskFileItem的OutputStream赋值
        //IOUtils是org.apache.commons.io.IOUtils;
        //与此类似的还有FileUtils
        IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());

        CommonsMultipartFile cMultiFile = new CommonsMultipartFile(fileItem);
        System.out.println(cMultiFile.getOriginalFilename());

        ShopExecution se = shopService.addShop(shop, cMultiFile);
        assertEquals("mytest1", se.getShop().getShopName());
    }

}
