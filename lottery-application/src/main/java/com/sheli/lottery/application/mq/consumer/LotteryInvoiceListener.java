package com.sheli.lottery.application.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.sheli.lottery.common.Constants;
import com.sheli.lottery.domain.activity.model.vo.InvoiceVO;
import com.sheli.lottery.domain.award.model.req.GoodsReq;
import com.sheli.lottery.domain.award.model.res.DistributionRes;
import com.sheli.lottery.domain.award.service.factory.DistributionGoodsFactory;
import com.sheli.lottery.domain.award.service.goods.IDistributionGoods;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @description:
 * @author: sheli
 * @date: 2023/11/14
 * @github: https://github.com/sheli00
 */
@Component
public class LotteryInvoiceListener {

    private Logger logger = LoggerFactory.getLogger(LotteryInvoiceListener.class);

    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;

    @KafkaListener(topics = "lottery_invoice", groupId = "lottery")
    public void onMessage(ConsumerRecord<?,?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional<?> message = Optional.ofNullable(record.value());

        if (!message.isPresent()) {
            return;
        }

        try {

            // 转化对象
            InvoiceVO invoiceVO = JSON.parseObject((String) message.get(), InvoiceVO.class);

            // 获取发奖工厂
            IDistributionGoods distrubutionGoodsService = distributionGoodsFactory.getDistributionGoodsService(invoiceVO.getAwardType());
            DistributionRes distributionRes = distrubutionGoodsService.doDistribution(new GoodsReq(invoiceVO.getuId(), invoiceVO.getOrderId(), invoiceVO.getAwardId(), invoiceVO.getAwardName(), invoiceVO.getAwardContent()));

            Assert.isTrue(Constants.AwardState.SUCCESS.getCode().equals(distributionRes.getCode()), distributionRes.getInfo());

            logger.info("消费MQ消息，完成 topic：{} bizId：{} 发奖结果：{}", topic, invoiceVO.getuId(), JSON.toJSONString(distributionRes));

            ack.acknowledge();

        } catch (Exception e) {
            // 发奖环节失败，消息重试。所有环节，发货、更新库，都需要保证幂等。
            logger.error("消费MQ消息，失败 topic：{} message：{}", topic, message.get());
            throw e;
        }

    }

}
