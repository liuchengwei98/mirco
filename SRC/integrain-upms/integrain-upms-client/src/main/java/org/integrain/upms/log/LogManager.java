package org.integrain.upms.log;

import org.integrain.upms.log.mdc.filter.HttpRequestMDCFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogManager {
	
	/**
     * 日志链:MDC机制
     * @return
     */
    @Bean
    public FilterRegistrationBean mdcFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpRequestMDCFilter());
        registration.addUrlPatterns("/*");
        registration.setName("mdcFilter");
        registration.setOrder(1);
        return registration;
    }

		
}
