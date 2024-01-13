package com.workstation.modules.system.domain.query;

import com.workstation.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 字典数据分页对象
 * @date 2024/1/13 0:06 星期六
 */
@Schema(description = "字典数据项分页查询对象")
public class DictPageQuery extends BasePageQuery {
    @Schema(description = "关键字(字典项名称)")
    private String keywords;

    @Schema(description = "字典类型编码")
    private String typeCode;

    public DictPageQuery() {
    }

    public DictPageQuery(int pageNum, int pageSize, String keywords, String typeCode) {
        super(pageNum, pageSize);
        this.keywords = keywords;
        this.typeCode = typeCode;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
