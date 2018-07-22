package com.javaee7.jms.topic.destination;

import javax.jms.JMSConnectionFactoryDefinition;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;

@JMSDestinationDefinitions({
	@JMSDestinationDefinition(
			name = JmsResources.FRUIT_TOPIC, 
			interfaceName = "javax.jms.Topic", 
			destinationName = "fruitTopic", 
			description = "Topic for fruit service"),
	@JMSDestinationDefinition(
			name = JmsResources.VEGETABLE_TOPIC, 
			interfaceName = "javax.jms.Topic", 
			destinationName = "vegetableTopic", 
			description = "Topic for vegetable service"),
})
@JMSConnectionFactoryDefinition( // <1> WildFly appears to require user and password to be set for connection factories
		name = JmsResources.CONNECTION_FACTORY,
		resourceAdapter = "jmsra",
		clientId = "batchJob", // <2> It is not allowed to call +setClientId+ on +Connection+ or +JMSContext+ in Java EE container.
		description = "Connection factory with clientId of the durable subscription")
public class JmsResources {
	 public static final String SUBSCRIPTION = "shop"; 
	public static final String CONNECTION_FACTORY = "java:app/factory";
	public static final String FRUIT_TOPIC = "java:global/jms/topic/fruitTopic";
	public static final String VEGETABLE_TOPIC = "java:global/jms/topic/vegetableTopic";
}
