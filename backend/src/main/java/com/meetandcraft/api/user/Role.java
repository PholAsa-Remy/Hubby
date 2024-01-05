package com.meetandcraft.api.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Data
    public static class AuthResponseDto {
        private String accessToken;
        private String tokenType = "Bearer ";

        public AuthResponseDto (String accessToken){
            this.accessToken = accessToken;
        }
    }

    @Data
    public static class LoginDto {
        private String username;
        private String password;
    }

    @Data
    public static class RegisterDto {
        private String username;
        private String password;
    }
}
