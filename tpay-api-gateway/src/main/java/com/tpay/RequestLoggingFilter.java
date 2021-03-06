package com.tpay;

import javax.servlet.http.HttpServletRequest;

import java.util.Collections;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestLoggingFilter extends ZuulFilter {

    private static final Logger LOG = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        LOG.info("{} request to {}", request.getMethod(), request.getRequestURL().toString());
        LOG.info("Headers {}", Collections.list(ctx.getRequest().getHeaderNames()));
        return null;
    }
}
