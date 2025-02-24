package com.example.HostelManagement.Students;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        System.out.println("sign up called");
        System.out.println(stu.getRollNo());
        boolean exists = service.registerStudent(stu);

        if(exists) {
            return ResponseEntity.ok("User already exists \n Move to Login");
        }
        else{
            return ResponseEntity.ok("Sign up successful! \n Now login");
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> signin(@RequestBody Student stu, HttpSession session)
    {
        System.out.println("signin called");
        
        System.out.println(stu.getRollNo());
        
        Student student = service.authenticateStudent(stu.getRollNo(), stu.getPassword());
        if(student != null){
            //store the rollNo in the current session since we will be requiring it to fetch complaints for logged in user
            session.setAttribute("rollNo", stu.getRollNo());
            return ResponseEntity.ok(Map.of(
                    "message", "Login Successful!",
                    "student", student
            ));
        }
        else{
            return ResponseEntity.status(401).body(Map.of(
                    "message", "Invalid Credentials"
            ));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpSession session) {
        System.out.println("Logout Reached");
        System.out.println(session.getMaxInactiveInterval());
        session.invalidate(); // Invalidate session
        return ResponseEntity.ok(Map.of(
                "message", "Logged out Successfully"
        ));
    }
}
