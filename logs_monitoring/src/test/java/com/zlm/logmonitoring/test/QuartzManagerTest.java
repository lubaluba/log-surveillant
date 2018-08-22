package com.zlm.logmonitoring.test;

import org.junit.Test;

import com.zlm.logmonitoring.factory.LogJobFactory;
import com.zlm.logmonitoring.io.impl.ClassPathResource;
import com.zlm.logmonitoring.quartz.LogJob;
import com.zlm.logmonitoring.quartz.QuartzManager;
import com.zlm.logmonitoring.xml.XmlLogDefinitionReader;

public class QuartzManagerTest {
	
	@Test
	public void testQuartzManager() {
		LogJobFactory factory = new LogJobFactory();
		XmlLogDefinitionReader reader = new XmlLogDefinitionReader(factory);
		reader.loadLogDefinition(new ClassPathResource("test.xml"));
		LogJob job = factory.getJob("smartzc");
		QuartzManager manager = new QuartzManager();
		manager.addJob(job.getLogName(), "group1", "trigger1", "triggerGroup1", job.getClass(), "0/30 * * * * ? ");
		manager.startJobs();
	}
}
