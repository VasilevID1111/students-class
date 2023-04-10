package com.example.studentclass.shedule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shedule")
public class SheduleController {
    @GetMapping("/")
    public String shedule() {
        return "shedule";
    }
}
