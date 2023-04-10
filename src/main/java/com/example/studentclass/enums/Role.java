package com.example.studentclass.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_STUDENT, ROLE_WORKER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
