package com.sheli.lottery.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import com.sheli.lottery.infrastructure.po.UserTakeActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 用户活动领取表DAO
 * @author: sheli
 * @date: 2023/11/8
 * @github: https://github.com/sheli00
 */

@Mapper
public interface IUserTakeActivityDao {

    @DBRouter
    UserTakeActivity queryNoConsumedTakeActivityOrder(UserTakeActivity userTakeActivity);

//    @DBRouter(key = "uId")
    void insert(UserTakeActivity userTakeActivity);

    int lockTackActivity(UserTakeActivity userTakeActivity);

}
