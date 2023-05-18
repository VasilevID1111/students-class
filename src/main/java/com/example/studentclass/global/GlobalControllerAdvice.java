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
    private String globalUserName;
    private String globalRole;
    @ModelAttribute("globalUserName")
    public String globalUserName(Authentication authentication){
        if (authentication == null) {
            globalUserName = null;
            return "";
        }
        if (globalUserName != null) {
            return globalUserName;
        }
        UserDTO user = userService.getUserByAuthentication(authentication);
        globalUserName = user.getFio();
        return globalUserName;
    }

    @ModelAttribute("globalRole")
    public String globalRole(Authentication authentication){
        if (authentication == null) {
            globalRole = null;
            return "";
        }
        if (globalRole != null) {
            return globalRole;
        }
        UserDTO user = userService.getUserByAuthentication(authentication);
        globalRole = user.getRole().getAuthority().toString();
        return globalRole;
    }
}
