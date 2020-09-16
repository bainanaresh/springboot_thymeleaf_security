package com.baina.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
public class MailConfig {
	
	@Bean
	@Primary
	public FreeMarkerConfigurationFactoryBean getFactoryBean() {
		FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean=new FreeMarkerConfigurationFactoryBean();
		freeMarkerConfigurationFactoryBean.setTemplateLoaderPath("classpath:/templates");
		return freeMarkerConfigurationFactoryBean;
		
	}

}
