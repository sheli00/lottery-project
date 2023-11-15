package com.sheli.lottery.domain.award.repository;

/**
 * @description: 奖品表仓储服务接口
 * @author: sheli
 * @date: 2023/11/15
 * @github: https://github.com/sheli00
 */
public interface IOrderRepository {

    /**
     * 更新奖品发放状态
     * @param uId           用户ID
     * @param orderId       订单ID
     * @param awardId       奖品ID
     * @param grantState    奖品状态
     */
    void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState);

}
