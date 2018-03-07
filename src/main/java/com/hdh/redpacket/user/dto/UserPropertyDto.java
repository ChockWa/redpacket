package com.hdh.redpacket.user.dto;

import com.hdh.redpacket.user.model.UserProperty;

public class UserPropertyDto extends UserProperty{

    private String inviteCode;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
}
