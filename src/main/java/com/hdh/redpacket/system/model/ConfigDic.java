package com.hdh.redpacket.system.model;

import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

@TableName("sys_dict")
public class ConfigDic {

    // 自增id
    private Long id;

    // 字典类型
    private Integer type;

    // 字典类型名称
    private String name;

    // 字典编码
    private String code;

    // 字典值
    private String value;

    // 描述
    private String remark;

    // 状态1-正常
    private Integer status;

    // 创建时间
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
