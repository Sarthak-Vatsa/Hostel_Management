package com.example.HostelManagement.Notices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class NoticeController
{
    @Autowired
    private NoticeService service;

    @GetMapping("/viewNotices")
    public List<Notice> showNotices()
    {
        return service.showNotices();
    }
}