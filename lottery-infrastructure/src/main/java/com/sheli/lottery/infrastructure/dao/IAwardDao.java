package com.sheli.lottery.infrastructure.dao;

import com.sheli.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: sheli
 * @date: 2023/11/9
 * @github: https://github.com/sheli00
 */
@Mapper
public interface IAwardDao {
    Award queryAwardInfo(String awardId);

}
