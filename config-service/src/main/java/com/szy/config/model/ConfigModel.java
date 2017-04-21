package com.szy.config.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class ConfigModel {

	@Value("${name}")
	public String name;
	
	public String getConfigs(){
		
		return name;
	}
}
