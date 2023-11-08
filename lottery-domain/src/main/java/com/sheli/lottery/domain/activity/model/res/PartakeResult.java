package com.sheli.lottery.domain.activity.model.res;

import com.sheli.lottery.common.Result;

/**
 * @description: 活动参与结果
 * @author: sheli
 * @date: 2023/11/7
 * @github: https://github.com/sheli00
 */
public class PartakeResult extends Result {

    private Long strategyId;

    private Long takeId;

    public PartakeResult(String code, String info) {
        super(code, info);
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Long getTakeId() {
        return takeId;
    }

    public void setTakeId(Long takeId) {
        this.takeId = takeId;
    }

    @Override
    public String toString() {
        return "PartakeResult{" +
                "strategyId=" + strategyId +
                ", takeId=" + takeId +
                '}';
    }
}
