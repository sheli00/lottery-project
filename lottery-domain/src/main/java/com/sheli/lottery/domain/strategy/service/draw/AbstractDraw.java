package com.sheli.lottery.domain.strategy.service.draw;

import com.sheli.lottery.common.Constants;
import com.sheli.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.sheli.lottery.domain.strategy.model.req.DrawReq;
import com.sheli.lottery.domain.strategy.model.res.DrawResult;
import com.sheli.lottery.domain.strategy.model.vo.*;
import com.sheli.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * @description: 定义抽象抽奖过程，模版样式
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
public abstract class AbstractDraw extends DrawStrategySupport implements IDrawExec{

    private Logger logger = LoggerFactory.getLogger(AbstractDraw.class);

    @Override
    public DrawResult doDrawExec(DrawReq req) {
        // 获取抽奖策略
        StrategyRich strategyRich = super.queryStrategyRich(req.getStrategyId());
        StrategyBriefVO strategy = strategyRich.getStrategy();

        // 校验抽奖策略是否已经初始化到内存
        this.checkAndInitRateData(req.getStrategyId(), strategy.getStrategyMode(), strategyRich.getStrategyDetailList());

        // 获取不在抽奖范围内的列表
        List<String> excludeAwardIds = this.queryExcludeAwardIds(req.getStrategyId());

        // 执行抽奖算法 TODO
        String awardId = this.drawAlgorithm(req.getStrategyId(), drawAlgorithmGroup.get(strategy.getStrategyMode()), excludeAwardIds);

        // 包装中奖结果 TODO
        return buildDrawResult(req.getuId(), req.getStrategyId(), awardId, strategy);
    }

    private DrawResult buildDrawResult(String uId, Long strategyId, String awardId, StrategyBriefVO strategy) {
        if (null == awardId) {
            logger.info("执行策略抽奖完成【未中奖】，用户：{} 策略ID：{}", uId, strategyId);
            return new DrawResult(uId, strategyId, Constants.DrawState.FAIL.getCode());
        }

        AwardBriefVO award = super.queryAwardInfoByAwardId(awardId);
        DrawAwardVO drawAwardInfo = new DrawAwardVO(uId, award.getAwardId(), award.getAwardType(), award.getAwardName(), award.getAwardContent());
        drawAwardInfo.setStrategyMode(strategy.getStrategyMode());
        drawAwardInfo.setGrantType(strategy.getGrantType());
        drawAwardInfo.setGrantDate(strategy.getGrantDate());
        logger.info("执行策略抽奖完成【已中奖】，用户：{} 策略ID：{} 奖品ID：{} 奖品名称：{}", uId, strategyId, awardId, award.getAwardName());

        return new DrawResult(uId, strategyId, Constants.DrawState.SUCCESS.getCode(), drawAwardInfo);

    }

    protected abstract List<String> queryExcludeAwardIds(Long strategyId);

    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds);

    private void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetailBriefVO> strategyDetailList) {
        // 获取抽奖服务算法
        IDrawAlgorithm algorithm = drawAlgorithmGroup.get(strategyMode);

        // 判断已处理过的strategy
        if (algorithm.isExists(strategyId)) {
            return;
        }

        // 解析并初始化中奖概率到散列表
        List<AwardRateVO> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetailBriefVO strategyDetail : strategyDetailList) {
            awardRateInfoList.add(new AwardRateVO(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }

        algorithm.initRateTuple(strategyId, strategyMode, awardRateInfoList);

    }

}
