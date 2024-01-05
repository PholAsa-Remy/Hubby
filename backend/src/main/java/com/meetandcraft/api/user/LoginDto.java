package com.meetandcraft.api.user;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}
