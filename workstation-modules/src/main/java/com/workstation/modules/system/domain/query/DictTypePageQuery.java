package com.workstation.modules.system.domain.query;

import com.workstation.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 字典类型分页
 * @date 2024/1/13 0:08 星期六
 */
@Schema(description = "字典类型分页查询对象")
public class DictTypePageQuery extends BasePageQuery {
    @Schema(description = "关键字(类型名称/类型编码)")
    private String keywords;

    public DictTypePageQuery() {
    }

    public DictTypePageQuery(int pageNum, int pageSize, String keywords) {
        super(pageNum, pageSize);
        this.keywords = keywords;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
