package com.Tosin.Spring.Sec.controller;

import com.Tosin.Spring.Sec.dto.AuthResponse;
import com.Tosin.Spring.Sec.dto.LoginDto;
import com.Tosin.Spring.Sec.dto.RegisterDto;
import com.Tosin.Spring.Sec.entity.Role;
import com.Tosin.Spring.Sec.entity.User;
import com.Tosin.Spring.Sec.repository.RoleRepository;
import com.Tosin.Spring.Sec.repository.UserRepository;
import com.Tosin.Spring.Sec.service.AuthService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/auth")

public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
//    Logger logger = (Logger) LoggerFactory.getLogger(Logger.class);
    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto){
        if (userRepository.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user =User.builder()
                .firstName(registerDto.getFirstName())
                .otherName(registerDto.getOtherName())
                .lastName(registerDto.getLastName())
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();
        Role roles = roleRepository.findByRoleName("ROLE_USER").orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
//    @PostMapping("signin")
//    public LoginDto login(@RequestBody LoginDto loginDto){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return LoginDto.builder()
//                .usernameOrEmail(loginDto.getUsernameOrEmail())
//                .build();
//    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }






}
