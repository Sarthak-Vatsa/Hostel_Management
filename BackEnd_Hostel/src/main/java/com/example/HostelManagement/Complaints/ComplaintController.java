package com.example.HostelManagement.Complaints;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class ComplaintController
{
    @Autowired
    ComplaintService service;

    @PostMapping("/registerComplaint")
    public ResponseEntity<Map<String, String>> registerComplaint(@RequestBody Complaint complaint, HttpSession session)
    {
        Long rollNo = (Long) session.getAttribute("rollNo");

        service.addComplaint(rollNo, complaint);
        return ResponseEntity.ok(
                Map.of(
                        "message", "Complaint Registered Successfully"
                )
        );
    }

    @GetMapping("/viewComplaints")
    public List<Complaint> viewComplaints(HttpSession session)
    {
        Long rollNo = (Long) session.getAttribute("rollNo");
        List<Complaint> complaints = service.viewAllComplaints(rollNo);
        return complaints;
    }
}
