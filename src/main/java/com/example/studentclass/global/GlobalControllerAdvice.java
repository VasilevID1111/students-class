package com.example.studentclass.global;

import com.example.studentclass.enums.Role;
import com.example.studentclass.users.UserDTO;
import com.example.studentclass.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final UserService userService;
    @ModelAttribute("globalUserName")
    public String globalUserName(Authentication authentication){
        if (authentication != null) {
            UserDTO user = userService.getUserByAuthentication(authentication);
            return user.getFio();
        }
        else {
            return "";
        }
    }

    @ModelAttribute("globalRole")
    public String globalRole(Authentication authentication){
        if (authentication != null) {
            UserDTO user = userService.getUserByAuthentication(authentication);
            return user.getRole().getAuthority().toString();
        } else {
            return "";
        }
    }
}
