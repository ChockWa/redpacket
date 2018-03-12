package com.hdh.redpacket.game.model;

import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.util.Date;

@TableName("sys_game_play_detail")
public class GamePlayDetail {

    // 自增id
    private Long id;

    // 场次编号
    private String playNo;

    // 用户id
    private String userId;

    // 钻石数
    private BigDecimal diamond;

    // 投入次数
    private int num;

    // 创建时间
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayNo() {
        return playNo;
    }

    public void setPlayNo(String playNo) {
        this.playNo = playNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getDiamond() {
        return diamond;
    }

    public void setDiamond(BigDecimal diamond) {
        this.diamond = diamond;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
