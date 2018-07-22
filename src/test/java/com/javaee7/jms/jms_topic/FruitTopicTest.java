package com.javaee7.jms.jms_topic;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.javaee7.jms.topic.FruitPublisher;
import com.javaee7.jms.topic.FruitSubscription;
import com.javaee7.jms.topic.destination.JmsResources;

@RunWith(Arquillian.class)
public class FruitTopicTest {

	/*@Inject
	private FruitPublisher fruitPublisher;*/
	
	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap.create(WebArchive.class)
				/*.addClasses(FruitPublisher.class,
						FruitSubscription.class,
						JmsResources.class)*/
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");  
				
	}
	
	@Test
	public void shouldSubscriberGetMessage() {
	/*	fruitPublisher.publish("BANANA");
		fruitPublisher.publish("APPLE");
		fruitPublisher.publish("ANANAS");
		fruitPublisher.publish("STRAWBERRY");
		fruitPublisher.publish("WATERMELON");*/
	}
	
	

}
