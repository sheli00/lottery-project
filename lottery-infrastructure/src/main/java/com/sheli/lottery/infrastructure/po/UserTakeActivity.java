package com.sheli.lottery.infrastructure.po;

import java.util.Date;

/**
 * @description: 用户领取活动表
 * @author: sheli
 * @date: 2023/11/8
 * @github: https://github.com/sheli00
 */

public class UserTakeActivity {

    private Long id;

    private String uId;

    private Long takeId;

    private Long activityId;

    private String activityName;

    private Date takeDate;

    private Integer takeCount;

    private Long strategyId;

    private Integer state;

    private String uuid;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getTakeId() {
        return takeId;
    }

    public void setTakeId(Long takeId) {
        this.takeId = takeId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    public Integer getTakeCount() {
        return takeCount;
    }

    public void setTakeCount(Integer takeCount) {
        this.takeCount = takeCount;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserTakeActivity{" +
                "id=" + id +
                ", uId='" + uId + '\'' +
                ", takeId=" + takeId +
                ", activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", takeDate=" + takeDate +
                ", takeCount=" + takeCount +
                ", strategyId=" + strategyId +
                ", state=" + state +
                ", uuid='" + uuid + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
