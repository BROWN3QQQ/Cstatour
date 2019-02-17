package com.brown3qqq.cstatour.interceptor;

import com.brown3qqq.cstatour.dao.Impl.TicketsRepositoryimpl;
import com.brown3qqq.cstatour.dao.Impl.UserRepositoryimpl;
import com.brown3qqq.cstatour.pojo.Ticket;
import com.brown3qqq.cstatour.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    TicketsRepositoryimpl ticketsRepositoryimpl;
    @Autowired
    UserRepositoryimpl userRepositoryimpl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String tickets = null;
        if (request.getCookies() != null) {

            for (Cookie cookie : request.getCookies()) {
                if (!cookie.getName().equals("ticket")) {
//                    response.setHeader("content-type", "text/html;charset=UTF-8");
//                    OutputStream outputStream = response.getOutputStream();
//                    outputStream.write("有cookie，字段没有ticket不允许通过".getBytes("UTF-8"));
                    return false;

                }else {
                    tickets = cookie.getValue();
                }
            }
        } else {
//            response.setHeader("content-type", "text/html;charset=UTF-8");
//            OutputStream outputStream = response.getOutputStream();
//            outputStream.write("没有cookie不允许通过，跳转到登录页面".getBytes("UTF-8"));
            return false;
        }

        if (tickets != null) {
            Ticket ticket = ticketsRepositoryimpl.get(tickets);
            if (ticket == null){
//                response.setHeader("content-type", "text/html;charset=UTF-8");
//                OutputStream outputStream = response.getOutputStream();
//                outputStream.write("ticket对象为null,".getBytes("UTF-8"));
                return false;
            }else {
                return true;
            }

        }

        // User user = userRepositoryimpl.getUser(ticket.getId());


        return false;
        }






    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
