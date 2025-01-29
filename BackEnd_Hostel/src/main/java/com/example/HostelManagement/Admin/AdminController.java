package com.example.HostelManagement.Admin;

import com.example.HostelManagement.Notices.Notice;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    private AdminService service;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Admin admin)
    {
        boolean exists = service.registerAdmin(admin);
        if(exists){
            return ResponseEntity.ok("Admin Already exists! Move to Login");
        }
        else{
            return ResponseEntity.ok("Admin registered successfully!!");
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signin(@RequestBody Admin admin, HttpSession session)
    {
        Admin adminFromDB = service.authenticateAdmin(admin.getEmail(), admin.getPassword());
        if(adminFromDB != null){
            session.setAttribute("email", admin.getEmail());
            return ResponseEntity.ok(Map.of(
                    "message", "Login Successful!",
                    "email", admin.getEmail(),  // Convert to String if needed
                    "role", adminFromDB.getRole()
            ));
        }
        else{
            return ResponseEntity.ok(Map.of("message", "Invalid Credentials"));
        }
    }

    @PostMapping("/addNotice")
    public ResponseEntity<String> addNotice(@RequestBody Notice notice)
    {
        service.addNotice(notice);
        return ResponseEntity.ok("Notice added successfully!");
    }

    @DeleteMapping("/deleteComplaint/{complaintId}")
    public ResponseEntity<String> deleteComplaint(@PathVariable Long complaintId)
    {
        service.deleteComplaint(complaintId);
        return ResponseEntity.ok("Complaint: " + complaintId + " resolved");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) throws IOException {
        session.invalidate();
        return ResponseEntity.ok("Logged Out Successfully");
    }
}
