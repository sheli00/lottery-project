package com.sheli.lottery.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import com.sheli.lottery.infrastructure.po.UserTakeActivityCount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 用户参与次数表Dao
 * @author: sheli
 * @date: 2023/11/8
 * @github: https://github.com/sheli00
 */
@Mapper
public interface IUserTakeActivityCountDao {

    @DBRouter
    UserTakeActivityCount queryUserTakeActivityCount(UserTakeActivityCount userTakeActivityCountReq);

    void insert(UserTakeActivityCount userTakeActivityCount);

    int updateLeftCount(UserTakeActivityCount userTakeLeftCount);

}
