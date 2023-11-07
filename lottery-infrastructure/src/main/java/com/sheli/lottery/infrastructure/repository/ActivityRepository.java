package com.sheli.lottery.infrastructure.repository;

import com.sheli.lottery.domain.activity.model.req.PartakeReq;
import com.sheli.lottery.domain.activity.model.vo.ActivityBillVO;
import com.sheli.lottery.domain.activity.repository.IActivityRepository;
import com.sheli.lottery.infrastructure.dao.IActivityDao;
import com.sheli.lottery.infrastructure.po.Activity;

import javax.annotation.Resource;

/**
 * @description: 活动仓库服务实现类
 * @author: sheli
 * @date: 2023/11/7
 * @github: https://github.com/sheli00
 */
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IActivityDao activityDao;

    @Override
    public ActivityBillVO queryActivityBill(PartakeReq req) {

        // 查询活动信息
        Activity activity = activityDao.queryActivityById(req.getActivityId());

        // 查询领取次数 TODO
        // 封装结果信息 TODO
        return null;
    }
}
