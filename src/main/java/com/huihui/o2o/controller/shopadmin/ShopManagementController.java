package com.huihui.o2o.controller.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huihui.o2o.dto.ShopExecution;
import com.huihui.o2o.pojo.PersonInfo;
import com.huihui.o2o.pojo.Shop;
import com.huihui.o2o.service.ShopService;
import com.huihui.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @RequestMapping(value="/registershop",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> registerShop(HttpServletRequest request){
        //1.接收并转换相应的参数 包括店铺信息以及图片信息
        Map<String,Object> modelMap=new HashMap<>();
        String shopStr= HttpServletRequestUtil.getString(request,"shopStr");
        ObjectMapper mapper=new ObjectMapper();
        Shop shop=null;
        try{
            //获取前端传来的信息 并将其转换成实体类
            shop = mapper.readValue(shopStr,Shop.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg=null;
        CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        if(commonsMultipartResolver.isMultipart(request)){
            //接收前端传来的文件
            MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
            shopImg= (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","上传图片不能为空");
            return modelMap;
        }
        //2.注册店铺
        if(shop!=null&&shopImg!=null){
            PersonInfo personInfo=new PersonInfo();
            personInfo.setUserId(1L);
            shop.setOwnerId(personInfo.getUserId());
            ShopExecution se=shopService.addShop(shop,shopImg);
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","店铺信息不正确");
            return modelMap;
        }
        //3.返回结果
        return modelMap;
    }
}
