package com.szy.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szy.config.model.ConfigModel;

@RestController
public class ConfigController {

	@Autowired
	private ConfigModel configModel;
	
	@RequestMapping("/configs")
	public String getConfigs(){
		
		return configModel.getConfigs();
	}
}
