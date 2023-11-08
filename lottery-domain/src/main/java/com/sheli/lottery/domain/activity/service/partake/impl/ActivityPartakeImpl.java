package com.sheli.lottery.domain.activity.service.partake.impl;

import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import com.sheli.lottery.common.Constants;
import com.sheli.lottery.common.Result;
import com.sheli.lottery.domain.activity.model.req.PartakeReq;
import com.sheli.lottery.domain.activity.model.vo.ActivityBillVO;
import com.sheli.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.sheli.lottery.domain.activity.repository.IUserTakeActivityRepository;
import com.sheli.lottery.domain.activity.service.partake.BaseActivityPartake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * @description: 活动参与功能实现
 * @author: sheli
 * @date: 2023/11/8
 * @github: https://github.com/sheli00
 */
@Service
public class ActivityPartakeImpl extends BaseActivityPartake {

    private Logger logger = LoggerFactory.getLogger(ActivityPartakeImpl.class);

    @Resource
    private IUserTakeActivityRepository userTakeActivityRepository;


    @Resource
    private IDBRouterStrategy dbRouter;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Override
    protected UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        return userTakeActivityRepository.queryNoConsumedTakeActivityOrder(activityId, uId);
    }

    @Override
    protected Result checkActivityBill(PartakeReq partake, ActivityBillVO bill) {
        // 活动状态
        if (!Constants.ActivityState.DOING.getCode().equals(bill.getState())) {
            logger.warn("活动当前状态非可用 state: {}", bill.getState());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动当前状态非可用");
        }
        // 活动日期
        if (bill.getBeginDateTime().after(partake.getPartakeDate()) || bill.getEndDateTime().before(partake.getPartakeDate())) {
            logger.warn("活动时间范围非可用 beginDateTime：{} endDateTime：{}", bill.getBeginDateTime(), bill.getEndDateTime());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动时间范围非可用");
        }
        // 活动库存
        if (bill.getStockSurplusCount() <= 0) {
            logger.warn("活动剩余库存非可用 stockSurplusCount：{}", bill.getStockSurplusCount());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动剩余库存不足");
        }
        // 个人剩余可领取次数
        if (null != bill.getUserTakeLeftCount() && bill.getUserTakeLeftCount() <= 0) {
            logger.warn("个人领取次数非可用 userTakeLeftCount：{}", bill.getUserTakeLeftCount());
            return Result.buildResult(Constants.ResponseCode.UN_ERROR, "个人领取次数不足");
        }
        return Result.buildSuccessResult();
    }

    @Override
    protected Result subtractionActivityStock(PartakeReq req) {
        int count = activityRepository.subtractionActivityStock(req.getActivityId());
        // update失败
        if (0 == count) {
            logger.error("扣减活动库存失败 activityId：{}", req.getActivityId());
            return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
        }
        return Result.buildSuccessResult();
    }

    @Override
    protected Result grabActivity(PartakeReq partake, ActivityBillVO bill, Long takeId) {
        try {
            dbRouter.doRouter(partake.getuId());
            return transactionTemplate.execute(status -> {
                    try {
                        // 扣减个人已参与次数
                        int updateCount = userTakeActivityRepository
                                .subtractionLeftCount(
                                        bill.getActivityId(),
                                        bill.getActivityName(),
                                        bill.getTakeCount(),
                                        bill.getUserTakeLeftCount(),
                                        partake.getuId()
                                );
                        if (0 == updateCount) {
                            status.setRollbackOnly();
                            logger.error("领取活动，扣减个人已参与次数失败 activityId：{} uId：{}", partake.getActivityId(), partake.getuId());
                            return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
                        }

                        // 写入领取活动记录
                        userTakeActivityRepository.takeActivity(
                                bill.getActivityId(),
                                bill.getActivityName(),
                                bill.getStrategyId(),
                                bill.getTakeCount(),
                                bill.getUserTakeLeftCount(),
                                partake.getuId(),
                                partake.getPartakeDate(),
                                takeId
                        );
                    } catch (DuplicateKeyException e) {
                        status.setRollbackOnly();
                        logger.error("领取活动，唯一索引冲突 activityId：{} uId：{}", partake.getActivityId(), partake.getuId());
                        return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                    }
                    return Result.buildSuccessResult();
                });
        } finally {
            dbRouter.clear();
        }
    }


}
