package com.example.HostelManagement.Admin;

import com.example.HostelManagement.Notices.Notice;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @PostMapping("/signin")
//    public ResponseEntity<String> signin(@RequestBody Admin admin)
//    {
//        boolean exists = service.authenticateAdmin(admin.getEmail(), admin.getPassword());
//        if(exists){
//            return ResponseEntity.ok("Admin Login Successful!");
//        }
//        else{
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
//        }
//    }

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
}
