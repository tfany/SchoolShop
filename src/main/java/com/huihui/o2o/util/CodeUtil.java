package com.huihui.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    /**
     * 验证码工具 判断用户验证码是否正确
     * @param request 用户请求
     * @return bool
     */
    public static boolean checkVerifyCode(HttpServletRequest request) {
        String verifyCodeExpected = (String) request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual=HttpServletRequestUtil.getString(request,"verifyCodeActual");
        return verifyCodeActual == null || verifyCodeExpected.equals(verifyCodeActual);
    }
}
