package com.sheli.lottery.infrastructure.repository;

import com.sheli.lottery.domain.award.repository.IOrderRepository;
import com.sheli.lottery.infrastructure.dao.IUserStrategyExportDao;
import com.sheli.lottery.infrastructure.po.UserStrategyExport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @description: 奖品表仓储服务
 * @author: sheli
 * @date: 2023/11/15
 * @github: https://github.com/sheli00
 */
@Repository
public class OrderRepository implements IOrderRepository {

    @Resource
    private IUserStrategyExportDao userStrategyExportDao;

    @Override
    public void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState) {

        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setuId(uId);
        userStrategyExport.setOrderId(orderId);
        userStrategyExport.setAwardId(awardId);
        userStrategyExport.setGrantState(grantState);
        userStrategyExportDao.updateUserAwardState(userStrategyExport);

    }

}
