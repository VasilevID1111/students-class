package com.example.studentclass.shedule;

import com.example.studentclass.computers.ComputerDAO;
import com.example.studentclass.computers.ComputerService;
import com.example.studentclass.users.UserDAO;
import com.example.studentclass.users.UserService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/shedule")
public class SheduleController {
    private final SheduleService sheduleService;
    private final UserService userService;
    private final ComputerService computerService;
    @GetMapping("/")
    public String shedule() {
        return "shedule";
    }

    @PostMapping("/{compId}")
    public String visit(@PathVariable Integer compId, @RequestBody SheduleDTO visit) throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user_id= ((UserDetails) authentication.getPrincipal()).getUsername();
        visit.setUser(userService.getUser(user_id));
        visit.setComputer(computerService.getComputer(compId));
        sheduleService.saveShedule(visit);
        return "shedule";
    }
}
