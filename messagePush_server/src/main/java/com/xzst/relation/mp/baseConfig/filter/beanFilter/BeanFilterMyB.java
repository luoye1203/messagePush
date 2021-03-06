package com.xzst.relation.mp.baseConfig.filter.beanFilter;


import com.alibaba.fastjson.JSON;
import com.xzst.relation.mp.model.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BeanFilterMyB implements Filter {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info(this.getClass().getName()+" 过滤器开始起作用");
        String token = request.getParameter("token");
        if (StringUtils.isNoneBlank(token)) {
            chain.doFilter(request, response);
        } else {
            BaseResponse baseresponse = BaseResponse.buildResponse().setCode(HttpStatus.UNAUTHORIZED.value()).setMessage("登录认证失败").build();
            this.sendResponse((HttpServletResponse) response, baseresponse, HttpStatus.UNAUTHORIZED.value());
        }

    }

    @Override
    public void destroy() {

    }


    //过滤后，返回非法状态
    private void sendResponse(HttpServletResponse response, BaseResponse data, int status) {

        String content = JSON.toJSONString(data);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
