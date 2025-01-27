package com.example.HostelManagement.Notices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "/viewNotices", produces = MediaType.APPLICATION_JSON_VALUE)
    public Notice showNotices()
    {
        List<Notice> notices =  service.showNotices();
        System.out.println(notices.get(0).getContent());
        return notices.get(0);
    }
}