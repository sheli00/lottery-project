package com.sheli.lottery.application.mq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @description: Kafka消费者
 * @author: sheli
 * @date: 2023/11/14
 * @github: https://github.com/sheli00
 */
@Component
public class KafkaConsumer {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

//    @KafkaListener(topics = KafkaProducer.TOPIC_TEST, groupId = KafkaProducer.TOPIC_GROUP)
//    public void topicTest(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
//
//        Optional<?> message = Optional.ofNullable(record.value());
//        if (message.isPresent()) {
//            Object msg = message.get();
//            logger.info("topic_test 消费了： Topic:" + topic + ",Message:" + msg);
//            ack.acknowledge();
//        }
//
//    }



}
