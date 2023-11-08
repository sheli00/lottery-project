package com.sheli.lottery.domain.activity.model.vo;

/**
 * @description: 用户领取活动记录
 * @author: sheli
 * @date: 2023/11/8
 * @github: https://github.com/sheli00
 */
public class UserTakeActivityVO {

    private Long activityId;

    private Long takeId;

    private Long strategyId;

    private Integer state;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getTakeId() {
        return takeId;
    }

    public void setTakeId(Long takeId) {
        this.takeId = takeId;
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

}
