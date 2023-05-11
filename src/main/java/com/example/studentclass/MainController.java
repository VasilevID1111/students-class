package com.example.studentclass;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainController {
    @GetMapping(value = "/")
    public String computers() {
        return "redirect:/computers";
    }
}
