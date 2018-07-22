package com.javaee7.jms.topic;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;

import com.javaee7.jms.topic.destination.JmsResources;

@Named
public class FruitPublisher {

	@Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = JmsResources.FRUIT_TOPIC)
	private Topic topic;
	
	@Inject
	private JMSContext context;
	
	public void publish(final String fruit) {
		context.createProducer().send(topic, fruit);
	}
}
