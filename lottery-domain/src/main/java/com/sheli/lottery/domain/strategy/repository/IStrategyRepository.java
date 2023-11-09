package com.sheli.lottery.domain.strategy.repository;

import com.sheli.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.sheli.lottery.domain.strategy.model.vo.AwardBriefVO;

import java.util.List;

/**
 * @description: 策略表仓库接口
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
public interface IStrategyRepository {

    StrategyRich queryStrategyRich(Long strategyId);

    List<String> queryNoStockStrategyAwardList(Long strategyId);

    boolean deductStock(Long strategyId, String awardId);

    AwardBriefVO queryAwardInfo(String awardId);

}
