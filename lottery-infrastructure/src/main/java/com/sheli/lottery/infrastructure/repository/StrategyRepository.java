package com.sheli.lottery.infrastructure.repository;

import com.sheli.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.sheli.lottery.domain.strategy.model.vo.AwardBriefVO;
import com.sheli.lottery.domain.strategy.model.vo.StrategyBriefVO;
import com.sheli.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;
import com.sheli.lottery.domain.strategy.repository.IStrategyRepository;
import com.sheli.lottery.infrastructure.dao.IAwardDao;
import com.sheli.lottery.infrastructure.dao.IStrategyDao;
import com.sheli.lottery.infrastructure.dao.IStrategyDetailDao;
import com.sheli.lottery.infrastructure.po.Award;
import com.sheli.lottery.infrastructure.po.Strategy;
import com.sheli.lottery.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 策略表仓库
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
@Repository
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        // 查询策略
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        // 查询策略具体奖品
        List<StrategyDetail> strategyDetailList = strategyDetailDao.queryStrategyDetailList(strategyId);
        StrategyBriefVO strategyBriefVO = new StrategyBriefVO();
        BeanUtils.copyProperties(strategy, strategyBriefVO);

        List<StrategyDetailBriefVO> strategyDetailBriefVOList = new ArrayList<>();
        for (StrategyDetail strategyDetail : strategyDetailList) {
            StrategyDetailBriefVO strategyDetailBriefVO = new StrategyDetailBriefVO();
            BeanUtils.copyProperties(strategyDetail, strategyDetailBriefVO);
            strategyDetailBriefVOList.add(strategyDetailBriefVO);
        }

        return new StrategyRich(strategyId, strategyBriefVO, strategyDetailBriefVOList);
    }

    @Override
    public List<String> queryNoStockStrategyAwardList(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwardList(strategyId);
    }

    @Override
    public boolean deductStock(Long strategyId, String awardId) {
        StrategyDetail req = new StrategyDetail();
        req.setStrategyId(strategyId);
        req.setAwardId(awardId);
        int count = strategyDetailDao.deductStock(req);
        return count == 1;
    }

    @Override
    public AwardBriefVO queryAwardInfo(String awardId) {

        Award award = awardDao.queryAwardInfo(awardId);
        AwardBriefVO awardBriefVO = new AwardBriefVO();
        BeanUtils.copyProperties(award, awardBriefVO);
        return awardBriefVO;

    }

}
