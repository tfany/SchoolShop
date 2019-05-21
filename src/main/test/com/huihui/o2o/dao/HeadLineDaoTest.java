package com.huihui.o2o.dao;

import com.huihui.o2o.BaseTest;
import com.huihui.o2o.pojo.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class HeadLineDaoTest extends BaseTest {
   @Autowired
   private HeadLineDao headLineDao;
   @Test
   public void queryHeadLineTest(){
      int effnum=headLineDao.queryHeadLine(new HeadLine()).size();
      assertEquals(4,effnum);

   }
}
