package com.hussain.config;

//public class ProjectConfig {
//
//}



import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
public class ProjectConfig {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
	@Bean
	public AuditorAware<Integer> auditAware() {
		return new AuditAwareConfig();
	}

}