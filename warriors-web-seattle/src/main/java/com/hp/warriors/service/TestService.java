//package com.hp.warriors.service;
//
//import com.hp.warriors.Mail;
//import com.hp.warriors.config.RabbitConfig;
//import com.hp.warriors.entity.seattle.MsgLog;
//import com.hp.warriors.mapper.seattle.MsgLogMapper;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TestService {
//    @Autowired
//    private MsgLogMapper msgLogMapper;
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public void send(Mail mail) {
//        String msgId = RandomUtil.UUID32();
//        mail.setMsgId(msgId);
//
//        MsgLog msgLog = new MsgLog(msgId, RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME);
//        msgLogMapper.insert(msgLog);// 消息入库
//
//        CorrelationData correlationData = new CorrelationData(msgId);
//        rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME, mail, correlationData);// 发送消息
//
//    }
//
//}
