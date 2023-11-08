package com.sheli.lottery.domain.support.ids.policy;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.sheli.lottery.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;
import cn.hutool.core.net.NetUtil;
import javax.annotation.PostConstruct;

/**
 * @description: 雪花算法生成id
 * @author: sheli
 * @date: 2023/11/8
 * @github: https://github.com/sheli00
 */
@Component
public class SnowFlake implements IIdGenerator {

    private Snowflake snowFlake;

    @PostConstruct
    public void init() {
        long workerId;
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            workerId = NetUtil.getLocalhostStr().hashCode();
        }

        workerId = workerId >> 16 & 31;

        long dataCenterId = 1L;
        snowFlake = IdUtil.createSnowflake(workerId, dataCenterId);
    }

    @Override
    public synchronized long nextId() {
        return snowFlake.nextId();
    }
}
