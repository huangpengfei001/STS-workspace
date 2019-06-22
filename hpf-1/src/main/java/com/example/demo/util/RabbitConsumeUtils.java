package com.example.demo.util;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.rabbitmq.client.Channel;

//@Component
public class RabbitConsumeUtils {

	@RabbitListener(queues = "directQueue")
	public void xx(Object obj) {
		System.out.println("consume1: " + obj);
	}

	@RabbitListener(queues = "topicQueue1")
	public void xx1(Message msg, Channel channel) throws IOException {
		channel.basicAck(msg.getMessageProperties().getDeliveryTag(), true);
		System.out.println("consume1: topic1" + new String(msg.getBody(), "UTF-8"));
	}

//	@RabbitListener(queues = "fanoutQueue1")
//	public void xx2(Object obj) {
//		System.out.println("consume1 fanoutQueue: " + obj);
//	}
}
