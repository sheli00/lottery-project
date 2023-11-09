package com.sheli.lottery.domain.strategy.service.draw;

import com.sheli.lottery.domain.strategy.model.req.DrawReq;
import com.sheli.lottery.domain.strategy.model.res.DrawResult;

/**
 * @description: 抽奖执行接口
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
public interface IDrawExec {
    /**
     * 抽奖方法
     * @param req 抽奖参数：用户ID、策略ID
     * @return 中奖结果
     */
    DrawResult doDrawExec(DrawReq req);

}
