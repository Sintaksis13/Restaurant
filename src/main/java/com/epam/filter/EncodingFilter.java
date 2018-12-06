package com.epam.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = "requestEncoding", value = "UTF-8")})
public final class EncodingFilter implements Filter {
    private static final String ENCODING_DEFAULT = "UTF-8";
    private static final String ENCODING_INIT_PARAM_NAME = "requestEncoding";
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private String encoding;

    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter(ENCODING_INIT_PARAM_NAME);
        if (encoding == null) {
            encoding = ENCODING_DEFAULT;
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (null == servletRequest.getCharacterEncoding()) {
            servletRequest.setCharacterEncoding(encoding);
        }

        servletResponse.setContentType(CONTENT_TYPE);
        servletRequest.setCharacterEncoding(ENCODING_DEFAULT);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        throw new UnsupportedOperationException();
    }
}
