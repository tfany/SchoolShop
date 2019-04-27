package com.huihui.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    public static boolean cheakVerifyCode(HttpServletRequest request) {
        String verifyCodeExpected = (String) request.getSession().getAttribute("com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY");
        String verifyCodeActual=HttpServletRequestUtil.getString(request,"verifyCodeExpected");
        return verifyCodeActual == null || verifyCodeExpected.equals(verifyCodeActual);
    }
}
