package com.hdh.redpacket.game.model;

import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.util.Date;

@TableName("sys_game_play")
public class GamePlay {

    // 自增id
    private Long id;

    // 场次编号
    private String playNo;

    // 本场次总钻石
    private BigDecimal totalDiamond;

    // 胜出玩家
    private String winUserId;

    // 本场次胜出倍数
    private BigDecimal times;

    // 本场次状态1-未开始2-投入中3-等待开jiang4-已结束
    private Integer status;

    // 创建时间
    private Date creatTime;

    // 投入结束时间
    private Date overInputTime;

    // 本场结束时间
    private Date overTime;

    // 胜出金额
    private BigDecimal winAmount;

    public BigDecimal getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(BigDecimal winAmount) {
        this.winAmount = winAmount;
    }

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

    public BigDecimal getTotalDiamond() {
        return totalDiamond;
    }

    public void setTotalDiamond(BigDecimal totalDiamond) {
        this.totalDiamond = totalDiamond;
    }

    public String getWinUserId() {
        return winUserId;
    }

    public void setWinUserId(String winUserId) {
        this.winUserId = winUserId;
    }

    public BigDecimal getTimes() {
        return times;
    }

    public void setTimes(BigDecimal times) {
        this.times = times;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getOverInputTime() {
        return overInputTime;
    }

    public void setOverInputTime(Date overInputTime) {
        this.overInputTime = overInputTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }
}
