package com.hdh.redpacket.user.model;

import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

@TableName("sys_user")
public class User {

    private Long id;

    private String name;

    private String password;

    private String salt;

    private Date registerTime;

    private Integer gender;

    private String nickname;

    private String email;

    private String tel;

    // 邀请人id
    private Long inviteBy;

    // 状态1-正常2-禁用
    private Integer state;

    public Long getInviteBy() {
        return inviteBy;
    }

    public void setInviteBy(Long inviteBy) {
        this.inviteBy = inviteBy;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
