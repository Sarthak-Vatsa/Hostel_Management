package com.example.HostelManagement.Students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService
{
    @Autowired
    StudentRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> student = repo.findByrollNo(Long.valueOf(username));
        if(student.isEmpty()){
            System.out.println("User Not found");
            throw new UsernameNotFoundException("User not found 404");
        }

        return new StudentPrincipal(student.get());
    }
}
