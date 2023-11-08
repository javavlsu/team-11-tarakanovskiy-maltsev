package com.questions_platform.backend.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    STUDENT, TEACHER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
