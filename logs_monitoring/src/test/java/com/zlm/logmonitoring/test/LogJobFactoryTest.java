package com.zlm.logmonitoring.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.zlm.logmonitoring.factory.LogJobFactory;
import com.zlm.logmonitoring.io.impl.ClassPathResource;
import com.zlm.logmonitoring.quartz.LogJob;
import com.zlm.logmonitoring.xml.XmlLogDefinitionReader;

public class LogJobFactoryTest {
	@Test
	public void testLogJobFactory() {
		LogJobFactory factory = new LogJobFactory();
		XmlLogDefinitionReader reader = new XmlLogDefinitionReader(factory);
		reader.loadLogDefinition(new ClassPathResource("test.xml"));
		LogJob job = factory.getJob("smartzc");
		assertNotNull(job);
	}
}
