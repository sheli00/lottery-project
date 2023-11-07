package com.sheli.lottery.application.process.impl;

import com.sheli.lottery.application.process.IActivityProcess;
import com.sheli.lottery.application.process.req.DrawProcessReq;
import com.sheli.lottery.application.process.res.DrawProcessResult;
import com.sheli.lottery.common.Constants;
import org.springframework.stereotype.Service;

/**
 * @description: 活动抽奖流程编排
 * @author: sheli
 * @date: 2023/11/7
 * @github: https://github.com/sheli00
 */
@Service
public class ActivityProcessImpl implements IActivityProcess {
    @Override
    public DrawProcessResult doDrawProcess(DrawProcessReq req) {
        // 领取活动 TODO
        // 执行抽奖 TODO
        // 结果落库 TODO
        // 发送MQ TODO
        // 返回结果 TODO
        return new DrawProcessResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }
}