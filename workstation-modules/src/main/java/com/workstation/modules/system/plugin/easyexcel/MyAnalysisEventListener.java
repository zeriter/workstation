package com.workstation.modules.system.plugin.easyexcel;

import com.alibaba.excel.event.AnalysisEventListener;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 自定义解析结果监听器
 * @date 2024/1/13 14:53 星期六
 */
public abstract class MyAnalysisEventListener<T> extends AnalysisEventListener<T> {
    private String msg;

    public abstract String getMsg();
}
