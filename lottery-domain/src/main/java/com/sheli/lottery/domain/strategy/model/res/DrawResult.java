package com.sheli.lottery.domain.strategy.model.res;

import com.sheli.lottery.common.Constants;
import com.sheli.lottery.domain.strategy.model.vo.DrawAwardVO;

/**
 * @description: 抽奖结果
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
public class DrawResult {

    private String uId;

    private Long strategyId;

    private Integer drawState = Constants.DrawState.FAIL.getCode();

    private DrawAwardVO drawAwardInfo;

    public DrawResult() {
    }

    public DrawResult(String uId, Long strategyId, Integer drawState) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.drawState = drawState;
    }

    public DrawResult(String uId, Long strategyId, Integer drawState, DrawAwardVO drawAwardInfo) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.drawState = drawState;
        this.drawAwardInfo = drawAwardInfo;
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

    public Integer getDrawState() {
        return drawState;
    }

    public void setDrawState(Integer drawState) {
        this.drawState = drawState;
    }

    public DrawAwardVO getDrawAwardInfo() {
        return drawAwardInfo;
    }

    public void setDrawAwardInfo(DrawAwardVO drawAwardInfo) {
        this.drawAwardInfo = drawAwardInfo;
    }

    @Override
    public String toString() {
        return "DrawResult{" +
                "uId='" + uId + '\'' +
                ", strategyId=" + strategyId +
                ", drawState=" + drawState +
                ", drawAwardInfo=" + drawAwardInfo +
                '}';
    }

}
