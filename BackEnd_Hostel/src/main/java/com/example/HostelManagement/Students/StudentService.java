package com.example.HostelManagement.Students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService
{
    @Autowired
    private StudentRepo repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

//    private String hashPassword(String password){
//        // Simple hash based on shifting and XORing byte values
//        long hash = 0;
//
//        for (int i = 0; i < password.length(); i++) {
//            char c = password.charAt(i);
//            hash ^= ((long) c << (i % 64)); // Shift and XOR character codes
//        }
//
//        // Convert long hash to hexadecimal string for easy reading
//        return Long.toHexString(hash);
//    }

    public boolean registerStudent(Student stu)
    {
        Long roll = stu.getRollNo();
        Optional<Student> obj = repo.findByrollNo(roll);

        if( obj.isPresent() ){
            return true;
        }

        stu.setPassword(encoder.encode(stu.getPassword()));
        repo.save(stu);

        return false;
    }

    public Student authenticateStudent(Long rollNo, String password)
    {
        Optional<Student> obj = repo.findByrollNo(rollNo);
        //String hashedPass = encoder.encode(password); -> wrong as bcrypt generates new salt everytime - even for the same password
        if( obj.isPresent() && encoder.matches(password, obj.get().getPassword()) ){
            return obj.get();
        }

        return null;
    }

    public Student getStudentDetails(Long rollNo)
    {
        Optional<Student> user = repo.findByrollNo(rollNo);
        if(user.isEmpty()){
            return null;
        }

        return user.get();
    }
}
