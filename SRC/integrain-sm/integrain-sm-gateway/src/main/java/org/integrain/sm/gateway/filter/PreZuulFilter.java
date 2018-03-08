package org.integrain.sm.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.integrain.pub.util.uuid.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
@Component
public class PreZuulFilter extends ZuulFilter{
	 private static final Logger logger = LoggerFactory.getLogger(PreZuulFilter.class);
	@Override
	public Object run() {
		
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		String auditUUID = request.getHeader("AUDITUUID");
		if(auditUUID == null || "".equals(auditUUID)) {
			//生成日志串
			auditUUID = UuidUtil.getUUID();
			logger.info(auditUUID + "-日志链生成");

			//放入request的header中，以在整个请求中传递
			RequestContext.getCurrentContext().addZuulRequestHeader("AUDITUUID", auditUUID);
		}
		//放入Filter作用域
		request.setAttribute("AUDITUUID",auditUUID);
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;//执行顺序,数字越大,执行越靠后
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
