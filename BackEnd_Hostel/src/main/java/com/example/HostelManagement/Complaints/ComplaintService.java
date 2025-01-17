package com.example.HostelManagement.Complaints;

import com.example.HostelManagement.Students.Student;
import com.example.HostelManagement.Students.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService
{
    @Autowired
    StudentRepo sturepo;
    @Autowired
    ComplaintRepo repo;

    public void addComplaint(Long rollNo, Complaint complaint)
    {
        Optional<Student> stu =  sturepo.findByrollNo(rollNo);

        Student toAdd = stu.get();
        complaint.setStudent(toAdd);
        complaint.setDate(LocalDate.now().toString());
        complaint.setTime(LocalTime.now().toString());
        repo.save(complaint);
    }

    public List<Complaint> viewAllComplaints(Long rollNo)
    {
        return repo.findByStudentRollNo(rollNo);
    }
}
