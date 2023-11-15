package com.sheli.lottery.domain.award.service.factory;

import com.sheli.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Service;

/**
 * @description: 配送商品简单工厂
 * @author: sheli
 * @date: 2023/11/14
 * @github: https://github.com/sheli00
 */
@Service
public class DistributionGoodsFactory extends GoodsConfig{
    public IDistributionGoods getDistributionGoodsService(Integer awardType) {

        return goodsMap.get(awardType);

    }

}
