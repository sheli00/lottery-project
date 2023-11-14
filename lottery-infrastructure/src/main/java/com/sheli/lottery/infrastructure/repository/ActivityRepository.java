package com.sheli.lottery.infrastructure.repository;

import com.sheli.lottery.domain.activity.model.req.PartakeReq;
import com.sheli.lottery.domain.activity.model.vo.ActivityBillVO;
import com.sheli.lottery.domain.activity.repository.IActivityRepository;
import com.sheli.lottery.infrastructure.dao.IActivityDao;
import com.sheli.lottery.infrastructure.dao.IUserTakeActivityCountDao;
import com.sheli.lottery.infrastructure.po.Activity;
import com.sheli.lottery.infrastructure.po.UserTakeActivityCount;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @description: 活动仓库服务实现类
 * @author: sheli
 * @date: 2023/11/7
 * @github: https://github.com/sheli00
 */
@Repository
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IActivityDao activityDao;

    @Resource
    private IUserTakeActivityCountDao userTakeActivityCountDao;

    @Override
    public ActivityBillVO queryActivityBill(PartakeReq req) {

        // 查询活动信息
        Activity activity = activityDao.queryActivityById(req.getActivityId());

        // 查询领取次数
        UserTakeActivityCount userTakeActivityCountReq = new UserTakeActivityCount();
        userTakeActivityCountReq.setuId(req.getuId());
        userTakeActivityCountReq.setActivityId(req.getActivityId());
        UserTakeActivityCount userTakeActivityCount = userTakeActivityCountDao.queryUserTakeActivityCount(userTakeActivityCountReq);

        // 封装结果信息
        ActivityBillVO activityBillVO = new ActivityBillVO();
        activityBillVO.setuId(req.getuId());
        activityBillVO.setActivityId(req.getActivityId());
        activityBillVO.setActivityName(activity.getActivityName());
        activityBillVO.setBeginDateTime(activity.getBeginDateTime());
        activityBillVO.setEndDateTime(activity.getEndDateTime());
        activityBillVO.setTakeCount(activity.getTakeCount());
        activityBillVO.setStockSurplusCount(activity.getStockSurplusCount());
        activityBillVO.setStrategyId(activity.getStrategyId());
        activityBillVO.setState(activity.getState());
        activityBillVO.setUserTakeLeftCount(null == userTakeActivityCount ? null : userTakeActivityCount.getLeftCount());
        return activityBillVO;

    }

    @Override
    public int subtractionActivityStock(Long activityId) {
        return activityDao.subtractionActivityStock(activityId);
    }


}
