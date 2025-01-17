package com.example.HostelManagement.Admin;

import com.example.HostelManagement.Complaints.ComplaintRepo;
import com.example.HostelManagement.Notices.Notice;
import com.example.HostelManagement.Notices.NoticeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepo repo;

    @Autowired
    private NoticeRepo noticeRepo;

    @Autowired
    private ComplaintRepo complaintRepo;

    private String hashPassword(String password){
        // Simple hash based on shifting and XORing byte values
        long hash = 0;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            hash ^= ((long) c << (i % 64)); // Shift and XOR character codes
        }

        // Convert long hash to hexadecimal string for easy reading
        return Long.toHexString(hash);
    }

    public boolean registerAdmin(Admin admin)
    {
        Optional<Admin> obj = repo.findByEmail(admin.getEmail());
        if(obj.isPresent()){
            return true;
        }

        admin.setPassword(hashPassword(admin.getPassword()));
        repo.save(admin);
        return false;
    }

    public boolean authenticateAdmin(String email, String password)
    {
        Optional<Admin> obj = repo.findByEmail(email);
        String hashedPass = hashPassword(password);

        return obj.isPresent() && hashedPass.equals(obj.get().getPassword());
    }

    public void addNotice(Notice notice)
    {
        notice.setDate(LocalDate.now().toString());
        notice.setTime(LocalTime.now().toString());
        noticeRepo.save(notice);
    }

    public void deleteComplaint(Long complaintId)
    {
        complaintRepo.deleteById(complaintId);
    }
}
