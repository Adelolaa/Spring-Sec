package com.Tosin.Spring.Sec.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter


public class RegisterDto {
    private  String firstName;
    private String lastName;
    private String otherName;
    private String username;
    private String email;
    private String password;
}
