package com.example.HostelManagement.Notices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepo extends JpaRepository<Notice, Long>
{
    List<Notice> findAllByOrderByDateDescTimeDesc();

    //Other way
    //@Query("SELECT n FROM Notice n ORDER BY n.date DESC, n.time DESC")
    //List<Notice> getNoticesInDescendingOrder();
}
