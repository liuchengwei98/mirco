package org.integrain.upms.client.authentication;

import org.integrain.upms.client.authentication.config.AuthenticationConfig;
import org.integrain.upms.client.authentication.filter.ResourceAuthenticationFilter;
import org.integrain.upms.log.LogManager;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class AuthenticationManager extends LogManager {
	
	@Autowired
	AuthenticationConfig casConfig;
	
	private static boolean casEnabled = true;

	/** 
	 * 用于实现单点登出功能 
	 */
	@Bean
	public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() { 
		ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listener = new ServletListenerRegistrationBean<SingleSignOutHttpSessionListener>(); 
		listener.setEnabled(casEnabled); 
		listener.setListener(new SingleSignOutHttpSessionListener()); 
		listener.setOrder(1); 
		return listener; 
	} 
	
	/** 
	 * 该过滤器用于实现单点登出功能，单点退出配置，一定要放在其他filter之前 
	 */
	@Bean
	public FilterRegistrationBean singleSignOutFilter() { 
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean(); 
		filterRegistration.setFilter(new SingleSignOutFilter()); 
		filterRegistration.setEnabled(casEnabled);
		if(casConfig.getSignOutFilters().size()>0) { 
			filterRegistration.setUrlPatterns(casConfig.getSignOutFilters()); 
		}else {
			filterRegistration.addUrlPatterns("/*");
		}
		filterRegistration.addInitParameter("casServerUrlPrefix", casConfig.getCasServerUrlPrefix()); 
		filterRegistration.addInitParameter("serverName", casConfig.getServerName()); 
		filterRegistration.setOrder(3); 
		return filterRegistration; 
	} 
		
	/** 
	 * 该过滤器负责用户的认证工作 
	 */
	@Bean
	public FilterRegistrationBean authenticationFilter() { 
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean(); 
		filterRegistration.setFilter(new AuthenticationFilter()); 
		filterRegistration.setEnabled(casEnabled); 
		if(casConfig.getAuthFilters().size()>0) { 
			filterRegistration.setUrlPatterns(casConfig.getAuthFilters()); 
		}else {
			filterRegistration.addUrlPatterns("/*");
		}
		//casServerLoginUrl:cas服务的登陆url 
		filterRegistration.addInitParameter("casServerLoginUrl", casConfig.getCasServerLoginUrl()); 
		//本项目登录ip+port 
		filterRegistration.addInitParameter("serverName", casConfig.getServerName()); 
		filterRegistration.addInitParameter("useSession", casConfig.isUseSession()?"true":"false"); 
		filterRegistration.addInitParameter("redirectAfterValidation", casConfig.isRedirectAfterValidation()?"true":"false"); 
		filterRegistration.setOrder(4); 
		return filterRegistration; 
	} 
		
	/** 
	 * 该过滤器负责对Ticket的校验工作 
	 */
	@Bean
	public FilterRegistrationBean cas30ProxyReceivingTicketValidationFilter() { 
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean(); 
		Cas30ProxyReceivingTicketValidationFilter cas30ProxyReceivingTicketValidationFilter = new Cas30ProxyReceivingTicketValidationFilter(); 
		//cas20ProxyReceivingTicketValidationFilter.setTicketValidator(cas20ServiceTicketValidator()); 
		cas30ProxyReceivingTicketValidationFilter.setServerName(casConfig.getServerName()); 
		filterRegistration.setFilter(cas30ProxyReceivingTicketValidationFilter); 
		filterRegistration.setEnabled(casEnabled); 
		if(casConfig.getValidateFilters().size()>0) { 
			filterRegistration.setUrlPatterns(casConfig.getValidateFilters()); 
		}else {
			filterRegistration.addUrlPatterns("/*");
		}
		filterRegistration.addInitParameter("casServerUrlPrefix", casConfig.getCasServerUrlPrefix()); 
		filterRegistration.addInitParameter("serverName", casConfig.getServerName()); 
		filterRegistration.setOrder(5); 
		return filterRegistration; 
	} 
		
		
	/** 
	 * 该过滤器对HttpServletRequest请求包装， 可通过HttpServletRequest的getRemoteUser()方法获得登录用户的登录名 
	 * 
	 */
	@Bean
	public FilterRegistrationBean httpServletRequestWrapperFilter() { 
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean(); 
		filterRegistration.setFilter(new HttpServletRequestWrapperFilter()); 
		filterRegistration.setEnabled(casEnabled); 
		if(casConfig.getRequestWrapperFilters().size()>0) { 
			filterRegistration.setUrlPatterns(casConfig.getRequestWrapperFilters()); 
		}else {
			filterRegistration.addUrlPatterns("/*");
		}
		filterRegistration.setOrder(6); 
		return filterRegistration; 
	} 
	
	/** 
	 * 该过滤器使得可以通过org.jasig.cas.client.util.AssertionHolder来获取用户的登录名。 
	 比如AssertionHolder.getAssertion().getPrincipal().getName()。 
	 这个类把Assertion信息放在ThreadLocal变量中，这样应用程序不在web层也能够获取到当前登录信息 
	 */
	@Bean
	public FilterRegistrationBean assertionThreadLocalFilter() { 
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean(); 
		filterRegistration.setFilter(new AssertionThreadLocalFilter()); 
		filterRegistration.setEnabled(casEnabled); 
		if(casConfig.getAssertionFilters().size()>0) { 
			filterRegistration.setUrlPatterns(casConfig.getAssertionFilters()); 
		}else {
			filterRegistration.addUrlPatterns("/*");
		}
		filterRegistration.setOrder(7); 
		return filterRegistration; 
	}
	
	@Bean
	public FilterRegistrationBean resourceAuthenticationFilter() { 
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean(); 
		filterRegistration.setFilter(new ResourceAuthenticationFilter()); 
		filterRegistration.setEnabled(casEnabled); 
		if(casConfig.getAssertionFilters().size()>0) { 
			filterRegistration.setUrlPatterns(casConfig.getAssertionFilters()); 
		}else {
			filterRegistration.addUrlPatterns("/*");
		}
		filterRegistration.setOrder(8); 
		return filterRegistration; 
	}
	
	@Bean
	public FilterRegistrationBean corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
//	    config.addAllowedOrigin("null");
	    config.addAllowedOrigin("*");
	    source.registerCorsConfiguration("/**", config); //CORS 配置对所有接口都有效
	    
	    FilterRegistrationBean filterRegistration = new FilterRegistrationBean(new CorsFilter(source));
	    filterRegistration.setOrder(2);
	    return filterRegistration;
	}

		
}
