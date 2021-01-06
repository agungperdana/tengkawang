package com.kratonsolution.tengkawang.admsserver.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kratonsolution.tengkawang.backend.command.Command;
import com.kratonsolution.tengkawang.backoffice.config.DeviceOption;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Configuration
public class CacheConfig {
	
	@Bean
	public Cache<String, DeviceOption> deviceOption() {
		
		return CacheBuilder.newBuilder().maximumSize(1000).build();
	}
	
	@Bean
	public Cache<String, Command> commandCache() {
		
		return CacheBuilder.newBuilder().maximumSize(1000).build();
	}
	
	@Bean
	public Cache<String, Long> attendanceLogCache() {
		return CacheBuilder.newBuilder().maximumSize(50).build();
	}
	
	@Bean
	public Cache<LocalDate, Integer> codeGen() {
		return CacheBuilder.newBuilder().maximumSize(50).build();
	}
}
