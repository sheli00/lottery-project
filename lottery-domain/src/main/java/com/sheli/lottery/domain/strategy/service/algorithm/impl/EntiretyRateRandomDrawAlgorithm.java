package com.sheli.lottery.domain.strategy.service.algorithm.impl;

import com.sheli.lottery.domain.strategy.model.vo.AwardRateVO;
import com.sheli.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 必中奖策略，排除掉已经中奖的概率，重新计算中奖范围
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
@Component("entiretyRateRandomDrawAlgorithm")
public class EntiretyRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {

        // 统计总共概率和
        BigDecimal differenceDenominator = BigDecimal.ZERO;

        // 排除不在抽奖范围内的奖品
        List<AwardRateVO> differenceAwardRateList = new ArrayList<>();
        List<AwardRateVO> awardRateIntervalValList = awardRateInfoMap.get(strategyId);
        for (AwardRateVO awardRateInfo : awardRateIntervalValList) {
            String awardId = awardRateInfo.getAwardId();
            if (excludeAwardIds.contains(awardId)) {
                continue;
            }
            differenceAwardRateList.add(awardRateInfo);
            differenceDenominator = differenceDenominator.add(awardRateInfo.getAwardRate());
        }

        if (differenceAwardRateList.size() == 0) {
            return null;
        }

        if (differenceAwardRateList.size() == 1) {
            return differenceAwardRateList.get(0).getAwardId();
        }

        // 获取随机值
        int randomVal = this.generateSecureRandomIntCode(100);

        // 获取奖品
        String awardId = null;
        int cursorVal = 0;
        for (AwardRateVO awardRateInfo : differenceAwardRateList) {
            int rateVal = awardRateInfo.getAwardRate().divide(differenceDenominator, 2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();
            if (randomVal <= (cursorVal + randomVal)) {
                awardId = awardRateInfo.getAwardId();
                break;
            }
            cursorVal += rateVal;
        }

        return awardId;
    }

}
