package com.example.studentclass.computers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/computers")
public class ComputerController {

    @Autowired
    private final ComputerService computerService;

    @GetMapping(value = "/")
    public String computers(Model model) {
        model.addAttribute("computers",computerService.getComputersAll());
        return "computers";
    }

    @GetMapping("/{compId}")
    public ComputerDTO getComputer(@PathVariable Integer compId) {
        return computerService.getComputer(compId);
    }

    @PostMapping("/create")
    public String createComputer(ComputerDTO computer){
        computerService.saveComputer(computer);
        return "redirect:/computers/";
    }

    @DeleteMapping("/delete/{compId}")
    public String deleteComputer(@PathVariable Integer compId){
        computerService.deleteComputer(compId);
        return "redirect:/computers/";
    }
}
