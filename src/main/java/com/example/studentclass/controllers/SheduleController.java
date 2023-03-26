package com.example.studentclass.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SheduleController {
    @GetMapping("/")
    public String shedule() {
        return "shedule";
    }
}
