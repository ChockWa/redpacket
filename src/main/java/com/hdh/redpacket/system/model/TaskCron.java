package com.hdh.redpacket.system.model;


import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @author 
 */
@TableName("sys_task_cron")
public class TaskCron {
    private Integer id;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 任务执行周期
     */
    private String cron;

    /**
     * 状态1-正常
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}