package com.microservices.zuul.server.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostElapsedTimeFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(PreElapsedTimeFilter.class); 
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info("Entering to post filter");
		
		Long initTime = (Long) request.getAttribute("initTime");
		Long endTime = System.currentTimeMillis();
		Long totalTime = endTime - initTime;		
		
		log.info(String.format("Elapsed time in millis %s ms.", totalTime));
		
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	
}
