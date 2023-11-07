package com.sheli.lottery.infrastructure.dao;

import com.sheli.lottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 活动基础信息表DAO
 * @author: sheli
 * @date: 2023/11/7
 * @github: https://github.com/sheli00
 */
@Mapper
public interface IActivityDao {

    /**
     * 根据活动id查询活动信息
     * @param activityId 活动id
     * @return 活动信息
     */
    Activity queryActivityById(Long activityId);

}
