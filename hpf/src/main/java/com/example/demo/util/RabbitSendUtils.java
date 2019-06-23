package com.example.demo.util;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class RabbitSendUtils implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

	@Autowired
	private AmqpTemplate template;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void callbackSend(Object msg) {
		rabbitTemplate.setConfirmCallback(this);
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
		System.out.println("callbackSender UUID: " + correlationData.getId());
		this.rabbitTemplate.convertAndSend("hpf_topic_exchang", "topic1.messages", msg, correlationData);
	}

	public void send(Object msg) {
		template.convertAndSend("directQueue", msg);
		System.out.println("发送成功");
	}

	public void send2(Object msg, String routingKey) {
		template.convertAndSend("hpf_topic_exchang", routingKey, msg);
		System.out.println("topic1.msg 发送成功");
	}

	public void send3(Object msg) {
		template.convertAndSend("fanoutQueue1", msg);
		System.out.println("发送成功");
	}

	/**
	 * 消息发送到exchang后的回调 ack=true则发送成功
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println("callbakck confirm: " + correlationData.getId() + "-- " + ack + "---" + cause);
	}

	/**
	 * 消息从exchang发到queue的确认，发送失败则回调
	 */
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		try {
			System.out.println("message: " + new String(message.getBody(), "utf-8") + "replyText= " + replyText
					+ "exchange= " + exchange + "routingKey = " + routingKey);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
