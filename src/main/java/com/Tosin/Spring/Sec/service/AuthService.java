package com.Tosin.Spring.Sec.service;

import com.Tosin.Spring.Sec.dto.AuthResponse;
import com.Tosin.Spring.Sec.dto.LoginDto;
import org.springframework.context.annotation.Configuration;


public interface AuthService {
    AuthResponse login(LoginDto loginDto);

}
