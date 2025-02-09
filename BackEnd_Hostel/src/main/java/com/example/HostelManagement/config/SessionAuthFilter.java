package com.example.HostelManagement.config;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionAuthFilter implements Filter
{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI();
        System.out.println(url);
        if(url.equals("/students/signin") || url.equals("/students/signup") || url.equals("/admin/signup") || url.equals("/admin/signin")){
            filterChain.doFilter(request, response);
            //System.out.println("Hello doston");
            return;
        }

        HttpSession session = request.getSession(false);

        if(session != null && (session.getAttribute("rollNo") != null || session.getAttribute("email") != null) ){
            filterChain.doFilter(request, response);
        }
        else{
            System.out.println("inside else");
            response.sendError(401);
        }
    }
}
