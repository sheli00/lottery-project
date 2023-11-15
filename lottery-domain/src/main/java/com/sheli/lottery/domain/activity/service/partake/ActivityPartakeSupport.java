package com.sheli.lottery.domain.activity.service.partake;

import com.sheli.lottery.domain.activity.model.req.PartakeReq;
import com.sheli.lottery.domain.activity.model.vo.ActivityBillVO;
import com.sheli.lottery.domain.activity.repository.IActivityRepository;
import com.sheli.lottery.domain.activity.repository.IUserTakeActivityRepository;

import javax.annotation.Resource;

/**
 * @description: 一些通用的数据服务
 * @author: sheli
 * @date: 2023/11/7
 * @github: https://github.com/sheli00
 */
public class ActivityPartakeSupport {

    @Resource
    protected IActivityRepository activityRepository;

    @Resource
    protected IUserTakeActivityRepository userTakeActivityRepository;

    protected ActivityBillVO queryActivityBill(PartakeReq req) {
        return activityRepository.queryActivityBill(req);
    }

    public void updateInvoiceMqState(String getuId, Long orderId, Integer code) {
        userTakeActivityRepository.updateInvoiceMqState(getuId, orderId, code);
    }

}
