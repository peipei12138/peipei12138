package com.ccun.shapi.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "shapiFilter", urlPatterns = {"/shapi/*"})
public class ShapiFilter implements Filter {

    @Resource
    com.ccun.shapi.service.UserService userService;

//    private static final Logger LOGGER = LoggerFactory.getLogger(ShapiFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ResponseWrapper wrapper = new ResponseWrapper(response);

        String token = request.getParameter("token");
        if(userService.selectUserByToken(token) != null) {
            filterChain.doFilter(request, response);
        }else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", "当前登入状态已失效，请重新登入！");
            jsonObject.put("code", 500);
            response.setContentType("application/json;charset=utf-8");
            response.setContentLength(JSON.toJSONBytes(jsonObject).length);
            response.getOutputStream().write(JSON.toJSONBytes(jsonObject));
        }
//        try {
//            filterChain.doFilter(request,wrapper);
//
//            String respStr= new String(wrapper.toByteArray(), response.getCharacterEncoding());
//            Object parse = JSON.parse(respStr);
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("status",0);
//            jsonObject.put("data",parse);
//            LOGGER.info("response is ============{}",jsonObject);
//            response.setContentType("application/json;charset=utf-8");
//            //将buffer重置，因为我们要重新写入流进去
//            response.resetBuffer();
//            response.setContentLength(JSON.toJSONBytes(jsonObject).length);
//            response.getOutputStream().write(JSON.toJSONBytes(jsonObject));
//        } catch (Exception e) {
//            LOGGER.error("数据包装器执行出错....{}", e);
//        }
//        if(userService.selectUserByToken(token) != null) {
//            //执行
//            filterChain.doFilter(servletRequest, servletResponse);
//        }//else {
////            response.
////        }
////        System.out.println("response");
    }

    @Override
    public void destroy() {

    }
}
