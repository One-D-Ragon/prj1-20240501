package com.prj1.domain;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class CustomUser extends User {
    private Member member;

    public CustomUser(Member member) {
        // super("username","password", /*권한*/List.of());
        super(member.getEmail(), member.getPassword(), /*권한*/List.of());
        this.member = member;
    }
}
