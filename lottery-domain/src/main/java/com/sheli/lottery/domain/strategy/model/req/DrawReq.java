package com.sheli.lottery.domain.strategy.model.req;

/**
 * @description: 抽奖请求对象
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
public class DrawReq {

    private String uId;

    private Long strategyId;

    private String uuid;

    public DrawReq() {
    }

    public DrawReq(String uId, Long strategyId) {
        this.uId = uId;
        this.strategyId = strategyId;
    }

    public DrawReq(String uId, Long strategyId, String uuid) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.uuid = uuid;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
