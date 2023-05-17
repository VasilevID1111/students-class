package com.example.studentclass.global;

import com.example.studentclass.users.UserDTO;
import com.example.studentclass.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class MainController {
    @Autowired
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication,Model model) {
        model.addAttribute("email",userService.getUserByAuthentication(authentication).getEmail());
        return "profile";
    }

    @PostMapping("/profile/save")
    public String profileSave(Authentication authentication,
                              @RequestBody ProfileData profileData,
                              RedirectAttributes redirectAttributes) {
        if (userService.checkPassword(authentication,profileData.getOldPassword())){
            userService.changePasswordAndEmailByAuth(authentication,profileData.getPassword(),profileData.getEmail());
        } else {
            throw new IllegalArgumentException();
        }
        return "redirect:/profile";
    }

    @GetMapping(value = "")
    public String main(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"))) {
                return "redirect:/computers";
            } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_WORKER"))) {
                return "redirect:/check";
            }
        }
        return "redirect:/login";
    }
}
