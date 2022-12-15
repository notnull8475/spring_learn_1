package ru.gb.SpringOne.api.auth;

import lombok.Getter;

@Getter
public class AuthRequest {
    private String username;
    private String password;
}
