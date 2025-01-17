package com.example.HostelManagement.Complaints;
import com.example.HostelManagement.Students.Student;

import com.example.HostelManagement.Students.StudentRepo;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class Complaint
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    //private String status; //Pending or Resolved

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String date;
    private String time;

    private Long mobNo;

    public Long getMobNo() {
        return mobNo;
    }

    public void setMobNo(Long mobNo) {
        this.mobNo = mobNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }

    public void setType(String type) {
        this.type = type;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
