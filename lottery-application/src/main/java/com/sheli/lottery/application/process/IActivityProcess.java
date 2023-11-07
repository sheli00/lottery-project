package com.sheli.lottery.application.process;

import com.sheli.lottery.application.process.req.DrawProcessReq;
import com.sheli.lottery.application.process.res.DrawProcessResult;

/**
 * @description: 活动抽奖流程编排接口
 * @author: sheli
 * @date: 2023/11/7
 * @github: https://github.com/sheli00
 */

public interface IActivityProcess {

    /**
     * 执行抽奖流程
     * @param req 抽奖流程
     * @return 抽奖结果
     */
    DrawProcessResult doDrawProcess(DrawProcessReq req);

}
