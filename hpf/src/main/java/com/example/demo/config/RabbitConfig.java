package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

//@Configuration
public class RabbitConfig {

	/**
	 * @return direct模式
	 */
	@Bean
	public Queue directQueue() {
		return new Queue("directQueue");
	}

	@Bean
	public Queue fanoutQueue1() {
		return new Queue("fanoutQueue1");
	}

	@Bean
	public Queue fanoutQueue2() {
		return new Queue("fanoutQueue2");
	}

	@Bean
	public Queue topicQueue1() {
		return new Queue("topicQueue1");
	}

	@Bean
	public Queue topicQueue2() {
		return new Queue("topicQueue2");
	}

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange("hpf_topic_exchang");
	}

	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange("hpf_fanout_exchang");
	}

	@Bean
	public Binding bingBinding1(TopicExchange topicExchange, Queue topicQueue1) {
		return BindingBuilder.bind(topicQueue1).to(topicExchange).with("topic1.*");
	}

	@Bean
	public Binding bingBinding2(TopicExchange topicExchange, Queue topicQueue2) {
		return BindingBuilder.bind(topicQueue2).to(topicExchange).with("topic2.*");
	}

	@Bean
	public Binding bingBinding3(FanoutExchange fanoutExchange, Queue fanoutQueue1) {
		return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
	}

	@Bean
	public Binding bingBinding4(FanoutExchange fanoutExchange, Queue fanoutQueue2) {
		return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
	}

}
