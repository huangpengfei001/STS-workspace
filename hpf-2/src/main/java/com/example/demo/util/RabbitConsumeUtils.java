package com.example.demo.util;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumeUtils {

	@RabbitListener(queues = "directQueue")
	public void xx(Object obj) {
		System.out.println("consume2: " + obj);
	}

	@RabbitListener(queues = "topicQueue2")
	public void xx1(Object obj) {
		System.out.println("consume2 topic2: " + obj);
	}

//	@RabbitListener(queues = "fanoutQueue1")
//	public void xx2(Object obj) {
//		System.out.println("consume2 fanoutQueue: " + obj);
//	}

}
