package com.huihui.o2o.dao;

import com.huihui.o2o.BaseTest;
import com.huihui.o2o.pojo.ProductImg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductImgDaoTest extends BaseTest {
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void batchInsertProductImgTest(){
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("测试图片1");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(11L);
        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片2");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(12L);
        List<ProductImg> productImgList = new ArrayList<>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(2, effectedNum);
    }

    @Test
    public void queryProductImgListTest(){
        List<ProductImg> productImgList=productImgDao.queryProductImgList(11L);
        assertEquals(4, productImgList.size());
    }

    @Test
    public void delectProductImgTest(){
        int e1=productImgDao.deleteProductImgByProductId(42L);
        int e2=productImgDao.deleteProductImgByProductId(43L);
        assertEquals(2, e1+e2);
    }
}
