package com.huihui.o2o.util;

public class PageCalculator {
    /**
     * 从页数转到行数的转变
     * @param pageIndex 页数
     * @param pageSize 选几条
     * @return rowIndex 即起始位置
     */
    public static int calculateRowIndex(int pageIndex,int pageSize){
        return (pageIndex>0)?(pageIndex-1)*pageSize:0;
    }
}
