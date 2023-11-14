package com.sheli.lottery.infrastructure.dao;

import com.sheli.lottery.infrastructure.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 策略表【strategy】DAO
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
@Mapper
public interface IStrategyDao {

    Strategy queryStrategy(Long strategyId);

}
