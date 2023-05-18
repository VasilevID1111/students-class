package com.example.studentclass.reports;

import com.example.studentclass.computers.ComputerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Date;

@Controller
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAuthority('ROLE_WORKER')")
public class ReportController {
    @Autowired
    private final ReportService reportService;
    private final ComputerService computerService;
    @GetMapping("/report")
    public String report(Model model) {
        model.addAttribute("computers", computerService.getComputersAllDesc());
        model.addAttribute("visits",reportService.getOneMonthShedule());
        return "report";
    }
    @PostMapping("/report")
    public String getReport(@RequestParam("login") String login,
                            @RequestParam("compId") Integer compId,
                            @RequestParam("dateFrom") String dateFrom,
                            @RequestParam("dateTo") String dateTo,
                            Model model) throws ParseException {
        model.addAttribute("computers", computerService.getComputersAllDesc());
        model.addAttribute("visits",reportService.getSheduleWithSettings(login,compId,dateFrom,dateTo));
        return "report";
    }
}
