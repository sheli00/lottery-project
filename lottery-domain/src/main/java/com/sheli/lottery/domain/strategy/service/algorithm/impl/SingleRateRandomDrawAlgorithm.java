package com.sheli.lottery.domain.strategy.service.algorithm.impl;

import com.sheli.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        return null;
    }
}
