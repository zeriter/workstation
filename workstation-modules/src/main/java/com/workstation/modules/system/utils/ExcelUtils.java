package com.workstation.modules.system.utils;

import com.alibaba.excel.EasyExcel;
import com.workstation.modules.system.plugin.easyexcel.MyAnalysisEventListener;

import java.io.InputStream;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description Excel 工具类
 * @date 2024/1/13 15:03 星期六
 */
public class ExcelUtils {
    public static <T> String importExcel(InputStream is, Class clazz, MyAnalysisEventListener<T> listener) {
        EasyExcel.read(is, clazz, listener).sheet().doRead();
        return listener.getMsg();
    }
}
