package com.hdh.redpacket.system.model;

import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

@TableName("sys_access_token")
public class AccessToken {
	// id
	private Long id;

	// 用户id
	private String userId;

	// 会话key
	private String accessToken;

	// 用户ID
	private String sessionKey;
	
	// 过期时间
	private Date expireTime;

	// 续期时间
	private Date renewTime;

	// 创建时间
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getRenewTime() {
		return renewTime;
	}

	public void setRenewTime(Date renewTime) {
		this.renewTime = renewTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}