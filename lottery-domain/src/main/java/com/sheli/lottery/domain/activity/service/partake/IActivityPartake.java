package com.sheli.lottery.domain.activity.service.partake;

import com.sheli.lottery.common.Result;
import com.sheli.lottery.domain.activity.model.req.PartakeReq;
import com.sheli.lottery.domain.activity.model.res.PartakeResult;
import com.sheli.lottery.domain.activity.model.vo.DrawOrderVO;

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

    /**
     *  保存奖品单
     * @param drawOrder 奖品单
     * @return          保存结果
     */
    Result recordDrawOrder(DrawOrderVO drawOrder);

    void updateInvoiceMqState(String getuId, Long orderId, Integer code);

}
