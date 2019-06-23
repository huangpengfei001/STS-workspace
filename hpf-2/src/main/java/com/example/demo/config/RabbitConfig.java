package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	/**
	 * @return direct模式
	 */
	@Bean
	public Queue directQueue() {
		return new Queue("directQueue");
	}

	@Bean
	public TopicExchange getExchange() {
		return new TopicExchange("hpf_topic_exchang");
	}

	@Bean
	public Binding bingBinding(TopicExchange exchange, Queue queue) {
		return BindingBuilder.bind(queue).to(exchange).with("topic.message");
	}

}
