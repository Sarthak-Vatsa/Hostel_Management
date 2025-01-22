package com.example.HostelManagement.config;

import com.example.HostelManagement.Students.Student;
import com.example.HostelManagement.Students.StudentPrincipal;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        // Retrieve the logged-in user details
        StudentPrincipal user = (StudentPrincipal) authentication.getPrincipal(); // `User` is Spring Security's default user object
        HttpSession session = request.getSession();

        // Set custom session attributes
        session.setAttribute("rollNo", user.getUsername());

        // Redirect to a default URL after login
        //response.sendRedirect("/home");
    }
}
