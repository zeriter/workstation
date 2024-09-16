package com.workstation.modules.quartz.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 任务表单对象
 * @date 2024/9/16 13:23 周一
 */
@Schema(description = "任务表单对象")
public class QuartzForm {

    @Schema(description = "用于子任务唯一标识")
    private Long id;

    @Schema(description = "定时器名称")
    private String jobName;

    @Schema(description = "Bean名称")
    private String beanName;

    @Schema(description = "方法名称")
    private String methodName;

    @Schema(description = "参数")
    private String params;

    @Schema(description = "cron表达式")
    private String cronExpression;

    @Schema(description = "状态，暂时或启动")
    private Boolean isPause = false;

    @Schema(description = "负责人")
    private String personInCharge;

    @Schema(description = "报警邮箱")
    private String email;

    @Schema(description = "子任务")
    private String subTask;

    @Schema(description = "失败后暂停")
    private Boolean pauseAfterFailure;

    @Schema(description = "备注")
    private String description;

    public QuartzForm(Long id, String jobName, String beanName, String methodName, String params, String cronExpression, Boolean isPause, String personInCharge, String email, String subTask, Boolean pauseAfterFailure, String description) {
        this.id = id;
        this.jobName = jobName;
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
        this.cronExpression = cronExpression;
        this.isPause = isPause;
        this.personInCharge = personInCharge;
        this.email = email;
        this.subTask = subTask;
        this.pauseAfterFailure = pauseAfterFailure;
        this.description = description;
    }

    public QuartzForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
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

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Boolean getPause() {
        return isPause;
    }

    public void setPause(Boolean pause) {
        isPause = pause;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
