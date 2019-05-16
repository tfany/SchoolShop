package com.huihui.o2o.service;

import com.huihui.o2o.BaseTest;
import com.huihui.o2o.dto.ProductExecution;
import com.huihui.o2o.enums.ProductStateEnum;
import com.huihui.o2o.pojo.Product;
import com.huihui.o2o.pojo.ProductCategory;
import com.huihui.o2o.pojo.Shop;
import com.huihui.o2o.util.FileToMult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    public void addProductTest() throws IOException {
        Product product=new Product();
        Shop shop=new Shop();
        shop.setShopId(20L);
        ProductCategory pc=new ProductCategory();
        pc.setProductCategoryId(20L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品2");
        product.setPriority(20);
        product.setCreateTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        CommonsMultipartFile multipartFile= FileToMult.getCommonsMult("c:\\Users\\huige\\Pictures\\gg.jpg");
        List<CommonsMultipartFile> multipartFileList=new ArrayList<>();
        multipartFileList.add(FileToMult.getCommonsMult("c:\\Users\\huige\\Pictures\\girl.jpg"));
        multipartFileList.add(FileToMult.getCommonsMult("c:\\Users\\huige\\Pictures\\girl1.png"));
        ProductExecution pe= productService.addProduct(product,multipartFile,multipartFileList);
        assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());

    }

    @Test
    public void modifyProductTest() throws IOException {
        Product product=new Product();
        Shop shop=new Shop();
        shop.setShopId(20L);
        ProductCategory pc=new ProductCategory();
        pc.setProductCategoryId(20L);
        product.setProductId(20L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("正式商品1");
        product.setProductDesc("正式商品1");
        product.setPriority(20);
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        CommonsMultipartFile multipartFile= FileToMult.getCommonsMult("c:\\Users\\huige\\Pictures\\gg.jpg");
        List<CommonsMultipartFile> multipartFileList=new ArrayList<>();
        multipartFileList.add(FileToMult.getCommonsMult("c:\\Users\\huige\\Pictures\\girl.jpg"));
        multipartFileList.add(FileToMult.getCommonsMult("c:\\Users\\huige\\Pictures\\girl1.png"));
        ProductExecution pe= productService.modifyProduct(product,multipartFile,multipartFileList);
        assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());
    }

}
