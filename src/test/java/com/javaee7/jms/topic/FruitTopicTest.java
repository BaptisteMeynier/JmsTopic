package com.javaee7.jms.topic;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.maven.wagon.Streams;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.as.arquillian.api.ServerSetup;
import org.jboss.as.arquillian.api.ServerSetupTask;
import org.jboss.as.arquillian.container.ManagementClient;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.javaee7.jms.topic.FruitPublisher;
import com.javaee7.jms.topic.FruitSubscription;
import com.javaee7.jms.topic.destination.JmsResources;

/**
 * https://www.jtips.info/index.php?title=WildFly/JMS
 * @author baptiste
 *
 */
@RunWith(Arquillian.class)
@ServerSetup(PlayCli.class)
public class FruitTopicTest {

	@Inject
	private FruitPublisher fruitPublisher;
	
	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap.create(WebArchive.class)
				.addClasses(FruitPublisher.class,
						FruitSubscription.class,
						JmsResources.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");  
				
	}
	
	static class InitStandalone extends PlayCli  {

		@Override
		public void setup(ManagementClient arg0, String arg1) throws Exception {
			List<String> cliCommands= Stream.of(
					"/subsystem=messaging/hornetq-server=default/security-setting=\"jms.topic.fruitTopic\":add",
					"/subsystem=messaging/hornetq-server=default/security-setting=\"jms.topic.fruitTopic\"/role=swuser:add(send=true, consume=true)"
					).collect(Collectors.toList());
			run(arg0,cliCommands);
		}

	}
	
	@Test
	public void shouldSubscriberGetMessage() {
		fruitPublisher.publish("BANANA");
		fruitPublisher.publish("APPLE");
		fruitPublisher.publish("ANANAS");
		fruitPublisher.publish("STRAWBERRY");
		fruitPublisher.publish("WATERMELON");
	}
	
	

}
