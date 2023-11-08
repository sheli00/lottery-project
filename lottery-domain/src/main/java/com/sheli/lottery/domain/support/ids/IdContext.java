package com.sheli.lottery.domain.support.ids;

import com.sheli.lottery.common.Constants;
import com.sheli.lottery.domain.support.ids.policy.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Id策略模式上下文配置
 * @author: sheli
 * @date: 2023/11/8
 * @github: https://github.com/sheli00
 */
@Configuration
public class IdContext {

    @Bean
    public Map<Constants.Ids, IIdGenerator> idGenerator(SnowFlake snowFlake) {
        Map<Constants.Ids, IIdGenerator> idGeneratorMap = new HashMap<>(8);
        idGeneratorMap.put(Constants.Ids.SnowFlake, snowFlake);
        return idGeneratorMap;
    }

}
