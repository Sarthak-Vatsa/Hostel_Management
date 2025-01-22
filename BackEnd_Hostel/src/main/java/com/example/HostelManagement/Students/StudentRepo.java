package com.example.HostelManagement.Students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>
{
    Optional<Student> findByrollNo(Long rollNo);

    Student findByName(String name);
}
