package com.hp.warriors.config.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class MsgReturnCallback implements RabbitTemplate.ReturnCallback {

    /**
     * 启动消息失败返回，比如路由不到队列时触发回调
     *
     * @param message
     * @param i
     * @param s
     * @param s1
     * @param s2
     */
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("消息主体 message : " + message);
        System.out.println("消息主体 replyCode : " + i);
        System.out.println("描述 replyText：" + s);
        System.out.println("消息使用的交换器 exchange : " + s1);
        System.out.println("消息使用的路由键 routing : " + s2);
    }
}
