package com.example.studentclass.computers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/computers")
public class ComputerController {

    private final ComputerService computerService;

    @GetMapping(value = "/")
    public String computers(Model model) {
        model.addAttribute("computers",computerService.getComputersAllDesc());
        return "computers";
    }

    @GetMapping("/{compId}")
    public String getComputer(@PathVariable Integer compId, Model model) {
        model.addAttribute("computer", computerService.getComputer(compId));
        return "shedule";
    }

    @PostMapping("/create")
    public String createComputer(ComputerDTO computer){
        computerService.saveComputer(computer);
        return "redirect:/computers/";
    }
    @GetMapping ("/delete/{compId}")
    public String deleteComputer(@PathVariable Integer compId){
        computerService.deleteComputer(compId);
        return "redirect:/computers/";
    }

}
