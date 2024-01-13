package com.meetandcraft.api.user;

import org.springframework.stereotype.Service;

@Service
public interface UserEntityService {
    AuthResponseDto login (LoginDto loginDto);
    UserEntityDto register (RegisterDto registerDto);
}
