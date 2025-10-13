package br.com.fiap.minfin.CamelDemo.config;

import org.apache.camel.spi.UuidGenerator;
import org.apache.camel.support.DefaultUuidGenerator;
import org.apache.camel.support.OffUuidGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig {

	@Bean
	public UuidGenerator uuidGenerator() {
		return new DefaultUuidGenerator();
	}
	
	
	public UuidGenerator offUuidGenerator() {
		return new OffUuidGenerator();
	}

}
