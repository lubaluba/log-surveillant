package com.zlm.logmonitoring.factory;

import com.zlm.logmonitoring.entity.LogDefinition;

public interface LogDefinitionRegisty {
	
	LogDefinition getLogDefintion(String name);
	
	void registerLogDefinition(LogDefinition logDefinition);

}
