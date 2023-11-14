package com.sheli.lottery.domain.activity.repository;

import com.sheli.lottery.domain.activity.model.vo.DrawOrderVO;
import com.sheli.lottery.domain.activity.model.vo.UserTakeActivityVO;

import java.util.Date;

/**
 * @description: 用户参与活动仓库接口
 * @author: sheli
 * @date: 2023/11/8
 * @github: https://github.com/sheli00
 */
public interface IUserTakeActivityRepository {

    /**
     * 查询是否存在未执行抽奖领取活动单
     * @param activityId 活动ID
     * @param uId 用户ID
     * @return
     */
    UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId);

    /**
     * 扣减个人活动参与次数
     * @param activityId 活动id
     * @param activityName 活动名称
     * @param takeCount 活动个人
     * @param userTakeLeftCount 活动个人剩余领取次数
     * @param uId 用户id
     * @return 更新结果
     */
    int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId);

    /**
     * 领取活动
     * @param activityId        活动ID
     * @param activityName      活动名称
     * @param strategyId        抽奖策略ID
     * @param takeCount         活动个人可领取次数
     * @param userTakeLeftCount 活动个人剩余领取次数
     * @param uId               用户ID
     * @param takeDate          领取时间
     * @param takeId            领取ID
     */
    void takeActivity(Long activityId, String activityName, Long strategyId, Integer takeCount, Integer userTakeLeftCount, String uId, Date takeDate, Long takeId);

    /**
     * 锁定活动领取记录
     * @param uId           用户ID
     * @param activityId    活动ID
     * @param takeId        领取ID
     * @return 更新结果
     */
    int lockTackActivity(String uId, Long activityId, Long takeId);

    /**
     * 保存抽奖信息
     * @param drawOrder 中奖单
     */
    void saveUserStrategyExport(DrawOrderVO drawOrder);

}
