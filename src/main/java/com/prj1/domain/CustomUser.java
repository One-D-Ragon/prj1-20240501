package com.prj1.domain;

import org.springframework.security.core.userdetails.User;

import java.util.List;

public class CustomUser extends User {
    public CustomUser(Member member) {
        // super("username","password", /*권한*/List.of());
        super(member.getEmail(), member.getPassword(), /*권한*/List.of());
    }
}
