package com.sheli.lottery.domain.strategy.service.draw.impl;

import com.alibaba.fastjson.JSON;
import com.sheli.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.sheli.lottery.domain.strategy.service.draw.AbstractDraw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 抽奖过程实现类
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
@Service("drawExec")
public class DrawImpl extends AbstractDraw {

    private Logger logger = LoggerFactory.getLogger(DrawImpl.class);

    /**
     * 获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等（后续暂时没实现）
     * @param strategyId 策略ID
     * @return 排除的奖品ID集合
     */
    @Override
    protected List<String> queryExcludeAwardIds(Long strategyId) {
        List<String> awardList = this.queryNoStockStrategyAwardList(strategyId);
        logger.info("执行抽奖策略 strategyId：{}，无库存排除奖品列表ID集合 awardList：{}", strategyId, JSON.toJSONString(awardList));
        return awardList;
    }

    /**
     * 执行抽奖算法
     * @param strategyId      策略ID
     * @param drawAlgorithm   抽奖算法模型
     * @param excludeAwardIds 排除的抽奖ID集合
     * @return 中奖奖品ID
     */
    @Override
    protected String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds) {
        String awardId = drawAlgorithm.randomDraw(strategyId, excludeAwardIds);

        if (null == awardId) {
            return null;
        }

        boolean deductStatus = this.decuctStock(strategyId, awardId);

        return deductStatus ? awardId : null;
    }

}
