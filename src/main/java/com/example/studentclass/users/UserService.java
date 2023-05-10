package com.example.studentclass.users;

import com.example.studentclass.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(UserDTO user){
        if (userDAO.findByLogin(user.getLogin()) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_STUDENT);
        log.info("new user: {}",user.getFio());
        return true;
    }

    public UserDTO getUser(String login) {
        return userDAO.findByLogin(login);
    }

    public List<UserDTO> getUsers() {
        return userDAO.findAll();
    }
}
