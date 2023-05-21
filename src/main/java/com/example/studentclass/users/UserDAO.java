package com.example.studentclass.users;

import com.example.studentclass.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface UserDAO extends JpaRepository <UserDTO, Integer> {
    UserDTO findByLogin(String login);

    List<UserDTO>  findAllByRole(Role role);
}
