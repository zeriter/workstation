package com.workstation.modules.system.domain.query;

import com.workstation.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 角色分页查询对象
 * @date 2024/1/12 23:43 星期五
 */
@Schema(description = "角色分页查询对象")
public class RolePageQuery extends BasePageQuery {
    @Schema(description = "关键字(角色名称/角色编码)")
    private String keywords;

    public RolePageQuery() {
    }

    public RolePageQuery(String keywords) {
        this.keywords = keywords;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
