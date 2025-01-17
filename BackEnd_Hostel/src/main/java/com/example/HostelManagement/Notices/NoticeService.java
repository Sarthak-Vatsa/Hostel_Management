package com.example.HostelManagement.Notices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService
{
    @Autowired
    NoticeRepo repo;

    public List<Notice> showNotices()
    {
        return repo.findAllByOrderByDateDescTimeDesc();
    }
}
