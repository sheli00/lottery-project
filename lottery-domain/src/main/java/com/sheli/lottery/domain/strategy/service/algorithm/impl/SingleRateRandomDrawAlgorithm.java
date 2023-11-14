package com.sheli.lottery.domain.strategy.service.algorithm.impl;

import com.sheli.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 单项随机概率抽奖，抽到排除掉的则未中奖
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {

        // 获取元组
        String[] rateTuple = super.rateTupleMap.get(strategyId);
        assert rateTuple != null;

        int randomVal = this.generateSecureRandomIntCode(100);
        int idx = super.hashIdx(randomVal);

        String awardId = rateTuple[idx];

        if (excludeAwardIds.contains(awardId)) {
            return null;
        }

        return awardId;

    }
}
