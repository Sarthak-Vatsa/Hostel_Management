package com.example.HostelManagement.Students;

import com.example.HostelManagement.Notices.Notice;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController
{
    @Autowired
    private StudentService service;

    // get mappings for /signin, /signup and /home -> will be handled by react since they are just pages to show

    @GetMapping("/profile")
    public Student showProfile(HttpSession session)
    {
        Long rollNo = (Long) session.getAttribute("rollNo");
        return service.getStudentDetails(rollNo);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Student stu)
    {
        boolean exists = service.registerStudent(stu);

        if(exists) {
            return ResponseEntity.ok("User already exists \n Move to Login");
        }
        else{
            return ResponseEntity.ok("Sign up successful! \n Now login");
        }
    }

//    @PostMapping("/signin")
//    public ResponseEntity<String> signin( @RequestBody Student stu, HttpSession session)
//    {
//        boolean exists = service.authenticateStudent(stu.getRollNo(), stu.getPassword());
//        if(exists){
//            //store the rollNo in the current session since we will be requiring it to fetch complaints for logged in user
//            session.setAttribute("rollNo", stu.getRollNo());
//            return ResponseEntity.ok("Login Successful!");
//        }
//        else{
//            return ResponseEntity.status(200).body("Invalid Credentials");
//        }
//    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate();
        //response.sendRedirect("/signup");
        return ResponseEntity.ok("Logged Out Successfully");
    }
}
