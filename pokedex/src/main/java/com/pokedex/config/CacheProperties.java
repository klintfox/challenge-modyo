package com.pokedex.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class CacheProperties {

	private String [] hostname;

	public String[] getHostname() {
		return hostname;
	}

	public void setHostname(String[] hostname) {
		this.hostname = hostname;
	}
	
	
}
