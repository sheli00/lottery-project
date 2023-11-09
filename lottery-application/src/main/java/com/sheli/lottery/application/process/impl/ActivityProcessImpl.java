package com.sheli.lottery.application.process.impl;

import com.sheli.lottery.application.process.IActivityProcess;
import com.sheli.lottery.application.process.req.DrawProcessReq;
import com.sheli.lottery.application.process.res.DrawProcessResult;
import com.sheli.lottery.common.Constants;
import com.sheli.lottery.domain.activity.model.req.PartakeReq;
import com.sheli.lottery.domain.activity.model.res.PartakeResult;
import com.sheli.lottery.domain.activity.service.partake.IActivityPartake;
import com.sheli.lottery.domain.strategy.model.req.DrawReq;
import com.sheli.lottery.domain.strategy.model.res.DrawResult;
import com.sheli.lottery.domain.strategy.service.draw.IDrawExec;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 活动抽奖流程编排
 * @author: sheli
 * @date: 2023/11/7
 * @github: https://github.com/sheli00
 */
@Service
public class ActivityProcessImpl implements IActivityProcess {

    @Resource
    private IActivityPartake activityPartake;

    @Resource
    private IDrawExec drawExec;

    @Override
    public DrawProcessResult doDrawProcess(DrawProcessReq req) {
        // 领取活动
        PartakeResult partakeResult = activityPartake.doPartake(new PartakeReq(req.getuId(), req.getActivityId()));
        System.out.println(partakeResult.toString());
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(partakeResult.getCode())) {
            return new DrawProcessResult(partakeResult.getCode(), partakeResult.getInfo());
        }
        Long strategyId = partakeResult.getStrategyId();
        Long takeId = partakeResult.getTakeId();

        // 执行抽奖 TODO
        DrawResult drawResult = drawExec.doDrawExec(new DrawReq(req.getuId(), strategyId));
        System.out.println(drawResult.toString());

        // 结果落库 TODO
        // 发送MQ TODO
        // 返回结果 TODO
        return new DrawProcessResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }
}
