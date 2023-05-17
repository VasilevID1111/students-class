package com.example.studentclass.check;

import com.example.studentclass.shedule.SheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAuthority('ROLE_WORKER')")
public class CheckController {
    @Autowired
    final private CheckService checkService;

    @GetMapping("/check")
    public String check(Model model) {
        Date today = new Date();
        List<String> undefinedStatuses = Arrays.asList("sign", "undefined");
        List<String> definedStatuses = Arrays.asList("attended","absent");
        model.addAttribute("visits_undefined",
                checkService.getShedulesByDateAndStatuses(today,undefinedStatuses));
        model.addAttribute("visits_defined",
                checkService.getShedulesByDateAndStatuses(today,definedStatuses));
        return "check";
    }

    @GetMapping("/check/{status}/{visit_id}")
    public String deactivateUser(@PathVariable String status,@PathVariable Integer visit_id) {
        checkService.changeStatus(visit_id, status);
        return "redirect:/check";
    }
}
