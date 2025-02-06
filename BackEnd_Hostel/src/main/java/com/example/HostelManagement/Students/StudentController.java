package com.example.HostelManagement.Students;

import com.example.HostelManagement.Notices.Notice;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
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
    public ResponseEntity<Map<String, String>> signin( @RequestBody Student stu, HttpSession session)
    {
        System.out.println("signin called");
        
        System.out.println(stu.getRollNo());
        
        Student student = service.authenticateStudent(stu.getRollNo(), stu.getPassword());
        if(student != null){
            //store the rollNo in the current session since we will be requiring it to fetch complaints for logged in user
            session.setAttribute("rollNo", stu.getRollNo());
            return ResponseEntity.ok(Map.of(
                    "message", "Login Successful!",
                    "rollNo", String.valueOf(stu.getRollNo()),  // Convert to String if needed
                    "role", student.getRole()
            ));
        }
        else{
            return ResponseEntity.ok(Map.of("message", "Invalid Credentials"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Invalidate session
        return ResponseEntity.ok("Logged out successfully!");
    }
}
