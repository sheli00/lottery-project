package com.sheli.lottery.application.mq.producer;

import com.alibaba.fastjson.JSON;
import com.sheli.lottery.domain.activity.model.vo.InvoiceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * @description: kafka生产者
 * @author: sheli
 * @date: 2023/11/14
 * @github: https://github.com/sheli00
 */
@Component
public class KafkaProducer {

    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    public static final String TOPIC_INVOICE = "lottery_invoice";

    public static final String TOPIC_GROUP = "test-consumer-group";

//    public void send(Object obj) {
//
//        String obj2String = JSON.toJSONString(obj);
//        logger.info("准备发送消息为：{}", obj2String);
//
//        // 发送消息
//        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_TEST, obj);
//        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                logger.info(TOPIC_TEST + " - 生产者 发送消息失败：" + throwable.getMessage());
//            }
//
//            @Override
//            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
//                logger.info(TOPIC_TEST + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
//            }
//        });
//
//
//    }

    public ListenableFuture<SendResult<String, Object>> sendLotteryInvoice(InvoiceVO invoice) {
        String objJson = JSON.toJSONString(invoice);
        logger.info("发送MQ消息 topic：{} bizId：{} message：{}", TOPIC_INVOICE, invoice.getuId(), objJson);
        return kafkaTemplate.send(TOPIC_INVOICE, objJson);
    }
}
