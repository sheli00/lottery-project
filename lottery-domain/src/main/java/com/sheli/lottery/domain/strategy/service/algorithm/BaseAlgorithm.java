package com.sheli.lottery.domain.strategy.service.algorithm;

import com.sheli.lottery.common.Constants;
import com.sheli.lottery.domain.strategy.model.vo.AwardRateVO;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 共用的算法逻辑抽象类
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm{

    private final int HASH_INCREMENT = 0x61c88647;
    protected Map<Long, List<AwardRateVO>> awardRateInfoMap = new ConcurrentHashMap<>();

    protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();

    private final int RATE_TUPLE_LENGTH = 128;

    @Override
    public boolean isExists(Long strategyId) {
        return awardRateInfoMap.containsKey(strategyId);
    }

    @Override
    public synchronized void initRateTuple(Long strategyId, Integer strategyMode, List<AwardRateVO> awardRateInfoList) {

        // 保持概率信息
        awardRateInfoMap.put(strategyId, awardRateInfoList);

        // 非单项概率，不必存入缓存
        if (!Constants.StrategyMode.SINGLE.getCode().equals(strategyMode)) {
            return;
        }

        String[] rateTuple = rateTupleMap.computeIfAbsent(strategyId, k -> new String[RATE_TUPLE_LENGTH]);

        int cursorVal = 0;
        for (AwardRateVO awardRateInfo : awardRateInfoList) {
            int rateVal = awardRateInfo.getAwardRate().multiply(new BigDecimal(100)).intValue();
            // 循环填充概率范围
            for (int i = cursorVal + 1; i <= (rateVal + cursorVal); i++) {
                rateTuple[hashIdx(i)] = awardRateInfo.getAwardId();
            }

            cursorVal += rateVal;
        }
    }

    /**
     * 斐波那契散列法，计算哈希索引下标值
     * @param val 值
     * @return 索引
     */
    protected int hashIdx(int val) {
        
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH - 1);
    }

    /**
     * 生成百位随机抽奖码
     * @param bound 范围
     * @return 随机值
     */
    protected int generateSecureRandomIntCode(int bound) {
        return new SecureRandom().nextInt(bound) + 1;
    }


}
