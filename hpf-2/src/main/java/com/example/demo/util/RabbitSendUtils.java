package com.example.demo.util;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitSendUtils {

	@Autowired
	private AmqpTemplate template;

	public void send(Object msg) {
		template.convertAndSend("directQueue", msg);
		System.out.println("发送成功");
	}

}
