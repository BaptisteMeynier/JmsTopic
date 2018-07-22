package com.javaee7.jms.topic;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Topic;

import com.javaee7.jms.topic.destination.JmsResources;

@Singleton
@Startup
public class FruitSubscription {

	@Resource(lookup = JmsResources.FRUIT_TOPIC)
	Topic topic;

	@Resource(lookup = JmsResources.CONNECTION_FACTORY)
	ConnectionFactory factory;

	/**
	 * We create the subscription at soonest possible time after deployment so we
	 * wouldn't miss any message
	 */
	@PostConstruct
	void createSubscription() {
		try (JMSContext jms = factory.createContext()) { // <1> This is factory with clientId specified
			JMSConsumer consumer = jms.createDurableConsumer(topic, JmsResources.SUBSCRIPTION); // <2> creates durable subscription on the topic
			System.out.println("MESSAGE>>>"+consumer.receive());
			consumer.close();
		}
	}

}
