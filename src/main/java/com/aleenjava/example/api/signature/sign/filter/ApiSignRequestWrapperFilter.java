package com.aleenjava.example.api.signature.sign.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 拦截 HttpServletRequest，包装成 ApiSignRequestWrapper
 *
 * @author cheng
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "apiSignRequestWrapperFilter")
@Order(10000)
public class ApiSignRequestWrapperFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            ApiSignRequestWrapper requestWrapper = new ApiSignRequestWrapper((HttpServletRequest) request);
            chain.doFilter(requestWrapper, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
