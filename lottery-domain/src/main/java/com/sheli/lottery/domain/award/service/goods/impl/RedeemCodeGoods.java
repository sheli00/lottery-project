package com.sheli.lottery.domain.award.service.goods.impl;

import com.sheli.lottery.common.Constants;
import com.sheli.lottery.domain.award.model.req.GoodsReq;
import com.sheli.lottery.domain.award.model.res.DistributionRes;
import com.sheli.lottery.domain.award.service.goods.DistributionBase;
import com.sheli.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: sheli
 * @date: 2023/11/14
 * @github: https://github.com/sheli00
 */
@Component
public class RedeemCodeGoods extends DistributionBase implements IDistributionGoods {
    @Override
    public DistributionRes doDistribution(GoodsReq req) {

        // 模拟调用兑换码
        logger.info("模拟调用兑换码 uId：{} awardContent：{}", req.getuId(), req.getAwardContent());

        // 更新用户领奖结果
        super.updateUserAwardState(req.getuId(), req.getOrderId(), req.getAwardId(), Constants.GrantState.COMPLETE.getCode());

        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }

}
