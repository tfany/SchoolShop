package com.huihui.o2o.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ShopStateEnum {
    CHECK(0, "审核中"), OFFLINE(-1, "非法店铺"), SUCCESS(1, "操作成功"),
    PASS(2, "通过认证"), INNER_ERROR(-1001, "系统错误"),
    NULL_SHOPID(-1002,"ShopId为空"),NULL_SHOP(1003,"shop为空");
    private int state;
    private String stateInfo;
    ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     *根据状态值获取店铺状态
     * @param state 状态值
     * @return 返回状态
     */
    public static  ShopStateEnum stateOf(int state){
        for (ShopStateEnum value : values()) {
            if(value.getState()==state){
                return value;
            }
        }
        return null;
    }
}
