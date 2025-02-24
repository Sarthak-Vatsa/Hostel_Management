package com.example.HostelManagement.Students;
import com.example.HostelManagement.Payments.Payment;
import com.example.HostelManagement.Complaints.Complaint;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor

@Entity
public class Student
{
    @Id
    private Long rollNo;

    private String name;
    private String branch;
//    private String sem;
//    private String hostelName;
//
//    //login info
//    private String email;
    private String password;
    private String role;

//payment id not required -> since then we might have multivalued attributes in our table
//    @OneToOne
//    @JoinColumn(name = "payment_id") // Creates a foreign key column 'payment_id' in 'students' table
//    private Payment payment;
//
    //complaint id not required -> since then we might have multi valued attributes in our table
//    @OneToOne
//    @JoinColumn(name = "complaint_id") // Creates a foreign key column 'complaint_id' in 'students' table
//    private Complaint complaint;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getRollNo() {
        return rollNo;
    }

    public void setRollNo(Long rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
