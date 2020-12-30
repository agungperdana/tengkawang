package com.kratonsolution.belian.tengkawang.integration.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kratonsolution.belian.tengkawang.integration.command.Command;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Configuration
public class CacheConfig {

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
	
	@Bean
	public Cache<String, DeviceOption> deviceOption() {
		
		return CacheBuilder.newBuilder().maximumSize(1000).build();
	}
}
