package com.zlm.logmonitoring.factory;

import java.util.HashMap;
import java.util.Map;

import com.zlm.logmonitoring.entity.LogDefinition;
import com.zlm.logmonitoring.quartz.LogJob;

public class LogJobFactory implements LogDefinitionRegisty {

	private Map<String, LogDefinition> logDefinitionMap = new HashMap<>(); 
		

	public LogJob getJob(String logName) {
		
		if (! logDefinitionMap.containsKey(logName)) {
			return null;
		}
		return new LogJob(logName, logDefinitionMap.get(logName));
	}
	
	@Override
	public LogDefinition getLogDefintion(String name) {
		
		return logDefinitionMap.containsKey("name") ? logDefinitionMap.get(name) : null;
	}

	@Override
	public void registerLogDefinition(LogDefinition logDefinition) {
		
		this.logDefinitionMap.put(logDefinition.getLogName(), logDefinition);
	}
	
}
