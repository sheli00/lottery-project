package com.sheli.lottery.infrastructure.repository;

import com.sheli.lottery.common.Constants;
import com.sheli.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.sheli.lottery.domain.activity.repository.IUserTakeActivityRepository;
import com.sheli.lottery.infrastructure.dao.IUserTakeActivityCountDao;
import com.sheli.lottery.infrastructure.dao.IUserTakeActivityDao;
import com.sheli.lottery.infrastructure.po.UserTakeActivity;
import com.sheli.lottery.infrastructure.po.UserTakeActivityCount;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description: 用户参与活动仓库
 * @author: sheli
 * @date: 2023/11/8
 * @github: https://github.com/sheli00
 */
@Repository
public class UserTakeActivityRepository implements IUserTakeActivityRepository {

    @Resource
    private IUserTakeActivityDao userTakeActivityDao;

    @Resource
    private IUserTakeActivityCountDao userTakeActivityCountDao;

    @Override
    public UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        // 查询领取单
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setuId(uId);
        userTakeActivity.setActivityId(activityId);
        UserTakeActivity noConsumedTakeActivityOrder = userTakeActivityDao.queryNoConsumedTakeActivityOrder(userTakeActivity);

        if (null == noConsumedTakeActivityOrder) {
            return null;
        }

        UserTakeActivityVO userTakeActivityVO = new UserTakeActivityVO();
        userTakeActivityVO.setActivityId(noConsumedTakeActivityOrder.getActivityId());
        userTakeActivityVO.setTakeId(noConsumedTakeActivityOrder.getTakeId());
        userTakeActivityVO.setStrategyId(noConsumedTakeActivityOrder.getStrategyId());
        userTakeActivityVO.setState(noConsumedTakeActivityOrder.getState());

        return userTakeActivityVO;
    }

    @Override
    public int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId) {
        UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
        if (null == userTakeLeftCount) {
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            userTakeActivityCount.setTotalCount(takeCount);
            userTakeActivityCount.setLeftCount(takeCount-1);
            userTakeActivityCountDao.insert(userTakeActivityCount);
            return 1;
        } else {
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            return userTakeActivityCountDao.updateLeftCount(userTakeLeftCount);
        }
    }

    @Override
    public void takeActivity(Long activityId, String activityName, Long strategyId, Integer takeCount, Integer userTakeLeftCount, String uId, Date takeDate, Long takeId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setuId(uId);
        userTakeActivity.setTakeId(takeId);
        userTakeActivity.setActivityId(activityId);
        userTakeActivity.setActivityName(activityName);
        userTakeActivity.setTakeDate(takeDate);
        if (null == userTakeLeftCount) {
            userTakeActivity.setTakeCount(1);
        } else {
            userTakeActivity.setTakeCount(takeCount - userTakeLeftCount + 1);
        }
        userTakeActivity.setStrategyId(strategyId);
        userTakeActivity.setState(Constants.TaskState.NO_USED.getCode());
        String uuid = uId + "_" + activityId + "_" + userTakeActivity.getTakeCount();
        userTakeActivity.setUuid(uuid);

        userTakeActivityDao.insert(userTakeActivity);

    }


}
