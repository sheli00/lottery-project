package com.sheli.lottery.domain.activity.repository;

import com.sheli.lottery.domain.activity.model.req.PartakeReq;
import com.sheli.lottery.domain.activity.model.vo.ActivityBillVO;

/**
 * @description: 活动仓库服务（活动表、奖品表等）
 * @author: sheli
 * @date: 2023/11/7
 * @github: https://github.com/sheli00
 */
public interface IActivityRepository {

    /**
     * 查询活动账单信息（库存、状态等）
     * @param req
     * @return 活动账单
     */
    ActivityBillVO queryActivityBill(PartakeReq req);

}
