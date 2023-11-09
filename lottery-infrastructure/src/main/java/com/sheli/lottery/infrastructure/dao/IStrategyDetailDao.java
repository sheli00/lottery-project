package com.sheli.lottery.infrastructure.dao;

import com.sheli.lottery.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 策略细节DAO
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
@Mapper
public interface IStrategyDetailDao {

    List<StrategyDetail> queryStrategyDetailList(Long strategyId);

    List<String> queryNoStockStrategyAwardList(Long strategyId);

    int deductStock(StrategyDetail req);

}
