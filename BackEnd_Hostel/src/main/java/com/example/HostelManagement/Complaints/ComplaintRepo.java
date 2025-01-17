package com.example.HostelManagement.Complaints;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepo extends JpaRepository<Complaint, Long>
{
    //method to get all complaints by rollNo
    List<Complaint> findByStudentRollNo(Long rollNo);
}
