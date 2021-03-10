package com.hp.warriors.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.warriors.config.rabbit.MsgConfirmCallBack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/rabbit")
public class RabbitMQController {

    @Autowired
    @Qualifier("newMQRentbackConnection")
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MsgConfirmCallBack msgConfirmCallBack;


    @GetMapping("/testRabbit")
    public void testRabbit(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "evaluation");

        String todoExchange = "EX_test.exchange";
        String routingKey = "audit.house_info.online.update";
        try {
            log.info("通知paladin获取房评,routingKey:{},jsonObject:{}", routingKey, jsonObject.toString());
            rabbitTemplate.convertAndSend(todoExchange, routingKey, jsonObject.toString());
        } catch (Exception e) {
            log.error("通知paladin获取房评出现异常,routingKey:{},jsonObject:{}", routingKey, jsonObject.toString(), e);
        }
    }

    @GetMapping("/testRabbitDirect")
    public void testRabbitDirect() {
        /**
         * 声明消息 (消息体, 消息属性)
         */
        MessageProperties messageProperties = new MessageProperties();
        //设置消息是否持久化。Persistent表示持久化，Non-persistent表示不持久化
        messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
        messageProperties.setContentType("UTF-8");

        Message message = new Message("hello,rabbit_direct！".getBytes(), messageProperties);

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

        msgConfirmCallBack.send_callback("queue_direct",message,correlationData);
    }

}
