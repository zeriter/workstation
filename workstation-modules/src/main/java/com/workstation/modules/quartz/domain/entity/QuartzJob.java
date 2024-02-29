package com.workstation.modules.quartz.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.workstation.modules.system.domain.entity.BaseEntity;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description @TODO
 * @date 2024/2/29 10:33 星期四
 **/
@TableName(value = "quartz_job")
public class QuartzJob extends BaseEntity {
    public static final String JOB_KEY = "JOB_KEY";
    @TableId(value = "job_id", type = IdType.AUTO)
    private Long id;
    @TableField(exist = false)
    private String uuid;
    private String beanName;
    private String cronExpression;
    private Boolean isPause;
    private String jobName;
    private String methodName;
    private String params;
    private String description;
    private String personInCharge;
    private String email;
    private String subTask;
    private Boolean pauseAfterFailure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Boolean getIsPause() {
        return isPause;
    }

    public void setIsPause(Boolean isPause) {
        this.isPause = isPause;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubTask() {
        return subTask;
    }

    public void setSubTask(String subTask) {
        this.subTask = subTask;
    }

    public Boolean getPauseAfterFailure() {
        return pauseAfterFailure;
    }

    public void setPauseAfterFailure(Boolean pauseAfterFailure) {
        this.pauseAfterFailure = pauseAfterFailure;
    }
}
