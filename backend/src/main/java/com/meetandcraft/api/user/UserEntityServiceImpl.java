package com.meetandcraft.api.user;

import com.meetandcraft.api.exceptions.UsernameAlreadyTakenException;
import com.meetandcraft.api.security.JWT.JWTGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserEntityServiceImpl implements UserEntityService {
    private AuthenticationManager authenticationManager;
    private UserEntityRepository userEntityRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    public UserEntityServiceImpl(
            AuthenticationManager authenticationManager,
            UserEntityRepository userEntityRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JWTGenerator jwtGenerator
    ) {
        this.authenticationManager = authenticationManager;
        this.userEntityRepository = userEntityRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }


    @Override
    public AuthResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new AuthResponseDto(token);
    }

    @Override
    public UserEntityDto register(RegisterDto registerDto) {
        if (userEntityRepository.existsByUsername(registerDto.getUsername())){
            throw new UsernameAlreadyTakenException("Username already taken");
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));
        UserEntity savedUser = userEntityRepository.save(user);
        return UserEntityMapper.mapToDto(savedUser);
    }

    @Override
    public UserEntityDto getUserByUsername(String username) {
        UserEntity user = userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("This user couldn't be found"));
        UserEntityDto userEntityDto = UserEntityMapper.mapToDto(user);
        return userEntityDto;
    }
}
