package com.huihui.o2o.controller.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/shop",method = RequestMethod.GET)
public class shopAdminController {
    @RequestMapping("/shopoperation")
    public String shopOperation(){
        return "shop/shopoperation";
    }

    @RequestMapping("/shoplist")
    public String shopList(){
        return "shop/shoplist";
    }

    @RequestMapping("/shopmanage")
    public String shopManage(){
        return "shop/shopmanage";
    }
}
