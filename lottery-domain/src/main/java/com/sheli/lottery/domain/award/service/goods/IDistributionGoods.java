package com.sheli.lottery.domain.award.service.goods;

import com.sheli.lottery.domain.award.model.req.GoodsReq;
import com.sheli.lottery.domain.award.model.res.DistributionRes;

/**
 * @description: 抽象除配送货物接口
 * @author: sheli
 * @date: 2023/11/14
 * @github: https://github.com/sheli00
 */
public interface IDistributionGoods {

    DistributionRes doDistribution(GoodsReq goodsRes);

}
