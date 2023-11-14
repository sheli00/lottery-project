package com.sheli.lottery.application.process.impl;

import com.sheli.lottery.application.process.IActivityProcess;
import com.sheli.lottery.application.process.req.DrawProcessReq;
import com.sheli.lottery.application.process.res.DrawProcessResult;
import com.sheli.lottery.common.Constants;
import com.sheli.lottery.domain.activity.model.req.PartakeReq;
import com.sheli.lottery.domain.activity.model.res.PartakeResult;
import com.sheli.lottery.domain.activity.model.vo.DrawOrderVO;
import com.sheli.lottery.domain.activity.service.partake.IActivityPartake;
import com.sheli.lottery.domain.strategy.model.req.DrawReq;
import com.sheli.lottery.domain.strategy.model.res.DrawResult;
import com.sheli.lottery.domain.strategy.model.vo.DrawAwardVO;
import com.sheli.lottery.domain.strategy.service.draw.IDrawExec;
import com.sheli.lottery.domain.support.ids.IIdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

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

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

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

        // 执行抽奖
        DrawResult drawResult = drawExec.doDrawExec(new DrawReq(req.getuId(), strategyId));
//        System.out.println(drawResult.toString());
        if (Constants.DrawState.FAIL.getCode().equals(drawResult.getDrawState())) {
            return new DrawProcessResult(Constants.ResponseCode.LOSING_DRAW.getCode(), Constants.ResponseCode.LOSING_DRAW.getInfo());
        }
        DrawAwardVO drawAwardVO = drawResult.getDrawAwardInfo();

        // 结果落库 TODO
        activityPartake.recordDrawOrder(buildDrawOrderVO(req, strategyId, takeId, drawAwardVO));
        // 发送MQ TODO
        // 返回结果
        return new DrawProcessResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }

    private DrawOrderVO buildDrawOrderVO(DrawProcessReq req, Long strategyId, Long takeId, DrawAwardVO drawAwardVO) {
        long orderId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        DrawOrderVO drawOrderVO = new DrawOrderVO();
        drawOrderVO.setuId(req.getuId());
        drawOrderVO.setTakeId(takeId);
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setOrderId(orderId);
        drawOrderVO.setStrategyId(strategyId);
        drawOrderVO.setStrategyMode(drawAwardVO.getStrategyMode());
        drawOrderVO.setGrantType(drawAwardVO.getGrantType());
        drawOrderVO.setGrantDate(drawAwardVO.getGrantDate());
        drawOrderVO.setGrantState(Constants.GrantState.INIT.getCode());
        drawOrderVO.setAwardId(drawAwardVO.getAwardId());
        drawOrderVO.setAwardType(drawAwardVO.getAwardType());
        drawOrderVO.setAwardName(drawAwardVO.getAwardName());
        drawOrderVO.setAwardContent(drawAwardVO.getAwardContent());
        return drawOrderVO;
    }
}
