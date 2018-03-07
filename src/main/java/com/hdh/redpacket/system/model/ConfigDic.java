package com.hdh.redpacket.system.model;

import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

@TableName("sys_dict")
public class ConfigDic {

    // 自增id
    private Long id;

    // 字典类型名称
    private String dicName;

    // 字典编码
    private String dicCode;

    // 字典名称
    private String dicLabel;

    // 字典值
    private String dicValue;

    // 备注
    private String remark;

    // 状态1-正常
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicLabel() {
        return dicLabel;
    }

    public void setDicLabel(String dicLabel) {
        this.dicLabel = dicLabel;
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
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
}
