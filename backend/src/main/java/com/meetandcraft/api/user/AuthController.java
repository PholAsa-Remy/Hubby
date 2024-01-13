package com.meetandcraft.api.user;

import com.meetandcraft.api.security.JWT.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private UserEntityService userEntityService;

    @Autowired
    public AuthController(
            UserEntityService userEntityService
    ) {
        this.userEntityService = userEntityService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login (@RequestBody LoginDto loginDto) {
        AuthResponseDto authResponseDto = userEntityService.login(loginDto);
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        UserEntityDto userEntityDto = userEntityService.register(registerDto);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
