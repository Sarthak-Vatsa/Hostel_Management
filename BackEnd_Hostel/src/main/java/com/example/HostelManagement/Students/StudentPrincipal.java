package com.example.HostelManagement.Students;

import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class StudentPrincipal implements UserDetails
{
    private Student student;

    StudentPrincipal(Student stu)
    {
        System.out.println(stu.getPassword() + "hhhhhhhhh");
        student = stu;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("STUDENT"));
    }

    @Override
    public String getPassword() {
        System.out.println(student.getPassword());
        return student.getPassword();
    }

    @Override
    public String getUsername() {
        System.out.println("In Principal");
        return String.valueOf(student.getRollNo());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
