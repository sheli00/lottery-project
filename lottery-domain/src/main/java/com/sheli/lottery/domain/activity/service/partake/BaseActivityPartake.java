package com.sheli.lottery.domain.activity.service.partake;

import com.sheli.lottery.common.Constants;
import com.sheli.lottery.common.Result;
import com.sheli.lottery.domain.activity.model.req.PartakeReq;
import com.sheli.lottery.domain.activity.model.res.PartakeResult;
import com.sheli.lottery.domain.activity.model.vo.ActivityBillVO;
import com.sheli.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.sheli.lottery.domain.support.ids.IIdGenerator;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 活动领取模板抽象类
 * @author: sheli
 * @date: 2023/11/7
 * @github: https://github.com/sheli00
 */
public abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake{

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    // 实现活动领取 TODO
    @Override
    public PartakeResult doPartake(PartakeReq req) {

        // 查询是否存在未执行抽奖领取活动单
        UserTakeActivityVO userTakeActivityVO = this.queryNoConsumedTakeActivityOrder(req.getActivityId(), req.getuId());
        if (null != userTakeActivityVO) {
            return buildPartakeResult(userTakeActivityVO.getStrategyId(), userTakeActivityVO.getTakeId());
        }
        // 查询活动账单
        ActivityBillVO activityBillVO = super.queryActivityBill(req);
        System.out.println(activityBillVO.toString());

        // 活动信息校验处理（库存、状态等）
        Result checkResult = this.checkActivityBill(req, activityBillVO);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())) {
            return new PartakeResult(checkResult.getCode(), checkResult.getInfo());
        }

        // 校验完成，可以领取
        // 扣减活动库存【目前直接操作表，之后优化为Redis】
        Result subActivityResult = this.subtractionActivityStock(req);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())) {
            return new PartakeResult(subActivityResult.getCode(), subActivityResult.getInfo());
        }

        // 插入领取活动信息
        Long takeId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        Result grabResult = this.grabActivity(req, activityBillVO, takeId);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(grabResult.getCode())) {
            return new PartakeResult(grabResult.getCode(), grabResult.getInfo());
        }

        return buildPartakeResult(activityBillVO.getStrategyId(), takeId);

    }

    /**
     * 领取活动
     * @param partake 参与活动请求
     * @param bill 活动账单
     * @param takeId 领取活动id
     * @return 领取结果
     */
    protected abstract Result grabActivity(PartakeReq partake, ActivityBillVO bill, Long takeId);

    protected abstract Result subtractionActivityStock(PartakeReq req);

    /**
     * 活动信息校验处理
     * @param partake 参与活动请求
     * @param bill 活动账单
     * @return
     */
    protected abstract Result checkActivityBill(PartakeReq partake, ActivityBillVO bill);

    /**
     * 封装结果
     * @param strategyId 策略ID
     * @param takeId 领取ID
     * @return 封装结果
     */
    private PartakeResult buildPartakeResult(Long strategyId, Long takeId) {
        PartakeResult partakeResult = new PartakeResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
        partakeResult.setStrategyId(strategyId);
        partakeResult.setTakeId(takeId);
        return partakeResult;
    }

    protected abstract UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId);

}
