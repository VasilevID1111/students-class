package com.example.studentclass.shedule;

import com.example.studentclass.computers.ComputerDAO;
import com.example.studentclass.computers.ComputerService;
import com.example.studentclass.users.UserDAO;
import com.example.studentclass.users.UserDTO;
import com.example.studentclass.users.UserService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Collections;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SheduleController {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private final SheduleService sheduleService;
    private final UserService userService;
    private final ComputerService computerService;
    @GetMapping("/shedule")
    public String shedule() {
        return "shedule";
    }

    @GetMapping("/visits")
    public String getPlannedVisits(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login= ((UserDetails) authentication.getPrincipal()).getUsername();
        UserDTO user = userService.getUser(login);
        Integer user_id = user.getId();
        List<SheduleDTO> schedules = sheduleService.getVisitsByUserIdAfterDate(user_id, new Date());
        Collections.sort(schedules, Comparator.comparing(SheduleDTO::getDate));
        model.addAttribute("visits",schedules);
        return "visits";
    }

    @GetMapping("/visits/delete/{visitId}")
    public String getPlannedVisits(@PathVariable Integer visitId) {
        sheduleService.deleteSheduleById(visitId);
        return "redirect:/visits/";
    }
    @PostMapping("/shedule/{compId}")
    public String visit(@PathVariable Integer compId,
                        @RequestParam("date") String date,
                        @RequestParam("timed_blocks") String timeBlocks,
                        @RequestParam("status") String status,
                        Model model) throws ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login= ((UserDetails) authentication.getPrincipal()).getUsername();
        UserDTO user = userService.getUser(login);
        SheduleDTO visit = new SheduleDTO();
        visit.setDate(dateFormat.parse(date));
        visit.setTimed_blocks(timeBlocks);
        visit.setStatus(status);
        visit.setUser(user);
        visit.setComputer(computerService.getComputer(compId));
        sheduleService.saveShedule(visit);
        return "redirect:/visits";
    }
}
