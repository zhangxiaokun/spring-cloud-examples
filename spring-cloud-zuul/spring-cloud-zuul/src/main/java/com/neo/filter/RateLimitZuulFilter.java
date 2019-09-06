package com.neo.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangxk
 * @description
 * @date 2019/9/6
 */
@Component
public class RateLimitZuulFilter extends ZuulFilter{
    private static final Logger logger = LoggerFactory.getLogger(RateLimitZuulFilter.class);

    // 初始化，放入1000令牌，时间窗口为1s
    private final RateLimiter rateLimiter = RateLimiter.create(1000);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        if(!rateLimiter.tryAcquire()){
            response.setContentType(MediaType.TEXT_PLAIN_VALUE);
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            ctx.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
            try {
                response.getWriter().write("too many requests");
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            ctx.setResponseStatusCode(200);
            logger.info("OK!!!");
        }
        return null;
    }
}
