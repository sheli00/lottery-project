package com.sheli.lottery.domain.strategy.service.draw;

import com.sheli.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.sheli.lottery.domain.strategy.model.vo.AwardBriefVO;
import com.sheli.lottery.domain.strategy.repository.IStrategyRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 抽奖策略数据支撑，一些通用的数据服务
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
public class DrawStrategySupport extends DrawConfig{

    @Resource
    protected IStrategyRepository strategyRepository;

    /**
     * 查询策略配置信息
     * @param strategyId 策略ID
     * @return 策略配置信息
     */
    protected StrategyRich queryStrategyRich(Long strategyId) {
        return strategyRepository.queryStrategyRich(strategyId);
    }
    /**
     * 获取奖品库存为空的awardId
     * @param strategyId 策略ID
     * @return 排除的奖品ID集合
     */
    protected List<String> queryNoStockStrategyAwardList(Long strategyId) {
        List<String> awardList = strategyRepository.queryNoStockStrategyAwardList(strategyId);
        return awardList;
    }

    protected boolean decuctStock(Long strategyId, String awardId) {
        return strategyRepository.deductStock(strategyId, awardId);
    }

    protected AwardBriefVO queryAwardInfoByAwardId(String awardId) {
        return strategyRepository.queryAwardInfo(awardId);
    }
}
