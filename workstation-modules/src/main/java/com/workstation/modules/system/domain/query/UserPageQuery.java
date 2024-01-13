package com.workstation.modules.system.domain.query;

import com.workstation.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 用户分页查询对象
 * @date 2024/1/4 23:09 星期四
 */
@Schema(description = "用户分页查询对象")
public class UserPageQuery extends BasePageQuery {
    @Schema(description = "关键字(用户名/昵称/手机号)")
    private String keywords;

    @Schema(description = "用户状态")
    private Integer status;

    @Schema(description = "部门ID")
    private Long deptId;

    @Schema(description = "创建时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startTime;

    @Schema(description = "创建时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endTime;

    public UserPageQuery() {
    }

    public UserPageQuery(String keywords, Integer status, Long deptId, String startTime, String endTime) {
        this.keywords = keywords;
        this.status = status;
        this.deptId = deptId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
