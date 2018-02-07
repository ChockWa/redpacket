package com.hdh.redpacket.user.dto;

public class RegisterDto {

    // 邮箱
    private String email;

    // 用户名
    private String name;

    //  密码
    private String password;

    // 图形验证码
    private String verifyCode;

    // 性别
    private Integer gender;

    // 电话
    private String tel;

    // 图形验证码的绑定码
    private String bindKey;

    // 区
    private Integer platform;

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getBindKey() {
        return bindKey;
    }

    public void setBindKey(String bindKey) {
        this.bindKey = bindKey;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
