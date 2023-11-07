package com.sheli.lottery.domain.activity.service.partake;

import com.sheli.lottery.domain.activity.model.req.PartakeReq;
import com.sheli.lottery.domain.activity.model.res.PartakeResult;

/**
 * @description: 抽奖活动参与接口
 * @author: sheli
 * @date: 2023/11/7
 * @github: https://github.com/sheli00
 */
public interface IActivityPartake {

    /**
     * 参与活动
     * @param req 入参
     * @return 领取结果
     */
    PartakeResult doPartake(PartakeReq req);

}
