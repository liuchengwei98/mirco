package org.integrain.upms.feign.config;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignConfig implements RequestInterceptor {

	private final Logger logger = LoggerFactory.getLogger(FeignConfig.class);
	
    @Override
    public void apply(RequestTemplate requestTemplate) {
    	//此处header中的key都必须是小写；control层取时大小写都可以
    	String auditUuid = getHeaders(getHttpServletRequest()).get("audituuid");
    	System.out.println("CAS client -- FeignConfig==AUDITUUID=" + auditUuid);
        requestTemplate.header("AUDITUUID", auditUuid);
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            System.out.println("FeignConfig==key=" + key + "==value==" + value);
            map.put(key, value);
        }
        return map;
    }
}