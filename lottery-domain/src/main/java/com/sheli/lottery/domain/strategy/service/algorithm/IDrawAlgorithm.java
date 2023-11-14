package com.sheli.lottery.domain.strategy.service.algorithm;

import com.sheli.lottery.domain.strategy.model.vo.AwardRateVO;

import java.util.List;

/**
 * @description: 抽奖算法接口
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
public interface IDrawAlgorithm {

    /**
     * 判断初始化
     * @param strategyId 策略ID
     * @return 判断结果
     */
    boolean isExists(Long strategyId);

    /**
     * 程序启动时初始化概率元组，使用过程中不允许修改元组数据
     * @param strategyId 策略id
     * @param strategyMode 策略模式
     * @param awardRateInfoList 奖品概率配置集合
     */
    void initRateTuple(Long strategyId, Integer strategyMode, List<AwardRateVO> awardRateInfoList);

    /**
     * 生成随机数，索引到对应的奖品信息返回结果
     * @param strategyId        策略ID
     * @param excludeAwardIds   排除掉已经不能作为抽奖的奖品ID，留给风控和空库存使用
     * @return                  中奖结果
     */
    String randomDraw(Long strategyId, List<String> excludeAwardIds);

}
